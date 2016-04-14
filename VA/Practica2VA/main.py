import cv2
import numpy as np
import os

#Carga de imagenes de entrenamiento
# training = []
# path = 'C:\Users\pdred\Desktop\VA\Practica2/training_ocr/training_ocr'
# for (dirpath, dirname, filename) in os.walk(path):
#     for file in filename:
#         pathFile = path +'/'+file
#         I = cv2.imread(pathFile,0)
#         training.append(I)

# Carga de imagenes de test
testing = []
path = 'C:\Users\pdred\Desktop\VA\Practica2/testing_ocr/testing_ocr'
for (dirpath, dirname, filename) in os.walk(path):
    for file in filename:
        pathFile = path +'/'+file
        I = cv2.imread(pathFile,0)
        testing.append(I)

        # Deteccion frontal del coche
        cascada = cv2.CascadeClassifier('C:\Users\pdred\Desktop\VA\Practica 1\haar\haar/matriculas.xml', )
        rectangulos = cascada.detectMultiScale(I, minNeighbors=2, scaleFactor=1.4)
        for rect in rectangulos:
            x, y, w, h = rect
            print x, y, w, h
            cv2.rectangle(I, (x, y + h), (x + w, y), (0, 255, 0))
            newImg = I[y:y+h,x:x+w]
            cv2.imshow("Prueba", newImg)
            cv2.waitKey()
