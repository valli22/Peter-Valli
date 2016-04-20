import cv2
import numpy as np
import os
import localizacionDigitos as ld

#Carga de imagenes de entrenamiento
training = []
path = 'C:\Users\pdred\Desktop\VA\Practica2/training_ocr/training_ocr'
for (dirpath, dirname, filename) in os.walk(path):
    for file in filename:
        pathFile = path +'/'+file
        I = cv2.imread(pathFile,0)
        training.append(I)
        # thresh = ld.LocalizacionDigitos(I)
        # thresh.caracter()

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