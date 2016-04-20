import cv2
import numpy as np
import os
from sklearn.discriminant_analysis import LinearDiscriminantAnalysis as LDA
import localizacionDigitos as ld

#Carga de imagenes de entrenamiento
training = []
C = []
E = []
path = 'C:\Users\pdred\Desktop\VA\Practica2/training_ocr/training_ocr'
for (dirpath, dirname, filename) in os.walk(path):
    for file in filename:
        pathFile = path +'/'+file
        I = cv2.imread(pathFile,0)
        training.append(I)
        thresh = ld.LocalizacionDigitos(I)
        M =thresh.caracter()
        C.append(M)

        E.append(file.split('_')[0])

sklearn_lda = LDA(n_components=2)
CR = sklearn_lda.fit_transform(C,E)

print CR

# Carga de imagenes de test
testing = []
path = 'C:\Users\pdred\Desktop\VA\Practica2/testing_ocr/testing_ocr'
for (dirpath, dirname, filename) in os.walk(path):
    for p,file in enumerate(filename):
        pathFile = path +'/'+file
        I = cv2.imread(pathFile,0)
        testing.append(I)

        # Deteccion frontal del coche
        cascada = cv2.CascadeClassifier('C:\Users\pdred\Desktop\VA\Practica1\haar\haar/matriculas.xml', )
        rectangulos = cascada.detectMultiScale(I, minNeighbors=2, scaleFactor=1.4)

        for rect in rectangulos:
            x, y, w, h = rect
            newImg = I[y:y+h,x:x+w]
            thresh = ld.LocalizacionDigitos(newImg)
            thresh.letrasImagen()