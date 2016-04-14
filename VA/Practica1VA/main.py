import cv2
import numpy as np
import entrada_salida as io
import centro_coche as cc
import cascadeClassifier as ccl
import videoCapture as vc


opcion = raw_input('Que accion desea realizar? (1=Centro de coche/2=Rectangulo cascade/3=Decteccion en video) ->')
if opcion == '1':

    procesador = cc.Centro_coche()

    path = raw_input('Escriba el directorio de donde desee coger las imagenes de entrenamiento: ')
    training = io.Entrada_salida(path).datos_Path()
    procesador.entrenamiento(training)

    path = raw_input('Escriba el directorio de donde desee coger las imagenes a procesar: ')
    testing = io.Entrada_salida(path).datos_Path()
    imgs = procesador.procesamiento(testing)

    for x,img in enumerate(imgs):
        cv2.imshow('Imagen '+str(x), img)
        cv2.waitKey()

elif opcion=='2':
    pathXML = raw_input('Introduzca el path donde se encuentre el haar con el que quiera detectar el coche: ')
    cascade = ccl.CascadeClassifier(pathXML)
    path = raw_input('Escriba el directorio de donde desee coger las imagenes a procesar: ')
    testing = io.Entrada_salida(path).datos_Path()
    imgs = cascade.procesamiento(testing,(100,100))
    for x,img in enumerate(imgs):
        cv2.imshow('Imagen '+str(x), img)
        cv2.waitKey()

elif opcion=='3':
    videoCapture = vc.VideoCapture()
    opcion = raw_input('Procesar mediante cascade(1) o mediante busqueda del centro del coche(2): ')
    if opcion=='1':
        path = raw_input('Introduzca el path donde se encuentre el video que quiera procesar: ')
        pathXML = raw_input('Introduzca el path donde se encuentre el haar con el que quiera detectar el coche: ')
        videoCapture.procesamientoCascade(path,pathXML)
    elif opcion=='2':
        path = raw_input('Introduzca el path donde se encuentre el video que quiera procesar: ')
        pathTrain = raw_input('Escriba el directorio de donde desee coger las imagenes de entrenamiento: ')
        training = io.Entrada_salida(pathTrain).datos_Path()
        videoCapture.procesamientoCentro(path,training)

    else:
        print 'Opcion de tipo de procesado incorrecta. 1 o 2'
else:
    print 'Opcion incorrecta -> elija 1,2 o 3'

