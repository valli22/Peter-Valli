import cv2
import numpy as np
import os
from sklearn.discriminant_analysis import LinearDiscriminantAnalysis as LDA
import localizacionDigitos as ld

#Carga de imagenes de entrenamiento
training = []
C = []
E = []
cont = 0

# Creamos el array de caracteristicas y el array de etiquetas a partir de las imagenes de entrenamiento proporcionadas
path = raw_input('Introduzca la direccion donde se encuentran las imagenes para entrenar:')
for (dirpath, dirname, filename) in os.walk(path):
    for file in filename:
        pathFile = path +'/'+file
        I = cv2.imread(pathFile,0)
        training.append(I)
        thresh = ld.LocalizacionDigitos(I)
        M = thresh.caracter()
        C.append(M)

        label = file.split('_')[0]
        if len(label)!= 1:
            E.append(10000)

        else:
            E.append(ord(label))

# Inicializamos LDA y reducimos las dimensiones de C
sklearn_lda = LDA()
E = np.asarray(E, dtype=np.float32)
sklearn_lda.fit(C,E)
CR = sklearn_lda.transform(C)
CR = np.asarray(CR, dtype=np.float32)

# Inicializamos el clasificador y lo entrenamos

# EM de precision
# em = cv2.EM()
# em.train(CR)

# NormalBayes 79.2% de precision
# normal = cv2.NormalBayesClassifier()
# normal.train(CR,E)

# KNearestNeighbour k=1 -> 76.4 de precision |  k=3 -> 79.8 de precision | k=5 -> 81.4 de precision | k=7 -> 80.5 de precision
knn = cv2.KNearest()
knn.train(CR, E)

# Carga de imagenes de test
testing = []

path = raw_input('Introduzca el path donde se encuentren los archivos para realizar las pruebas:')
nombre = path.split('\\')[-1]
fileopen = open(nombre+'.txt','w')

pathHaar = raw_input('Introduzca la direccion del clasificador de matriculas:')
for (dirpath, dirname, filename) in os.walk(path):
        for p,file in enumerate(filename):
        pathFile = path +'/'+file
        I = cv2.imread(pathFile,0)
        testing.append(I)
        
        # Inicializamos un clasificador con uno ya entrenado para detectar matriculas
        cascada = cv2.CascadeClassifier(pathHaar)
        # Deteccion de las matriculas de la imagen
        rectangulos = cascada.detectMultiScale(I, minNeighbors=2, scaleFactor=1.4)

        for rect in rectangulos:
            x, y, w, h = rect
            newImg = I[y:y+h,x:x+w]
            thresh = ld.LocalizacionDigitos(newImg)
            caracteres = thresh.letrasImagen()
            matricula = ""
            for des in caracteres:

                # Reducimos las dimensiones del vector de caracteristicas de la imagen
                newDes = sklearn_lda.transform([des])

                # Buscamos el descriptor mas parecido
                # retvals,results = normal.predict(np.asarray(newDes,np.float32))

                # retvals, results = em.predict(np.asarray(newDes,np.float32))

                ret,results,neighbours,dist = knn.find_nearest(np.asarray(newDes,np.float32),k=5)

                # En caso de que reconozca un valor ESP su valor sera 10000 y por lo tanto lo ignoramos
                if results[0][0]!=10000:
                    matricula+=chr(results[0][0])

            string = 'Nombre imagen: '+file+' Coordenadas centro matricula x: '+str(w/2)+' y: '+str(h/2)+' Matricula: '+matricula+' Largo matricula / 2: '+str(w/2)+'\n'
            fileopen.write(string)
fileopen.close()