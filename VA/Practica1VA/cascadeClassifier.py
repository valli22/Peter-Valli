# coding=utf-8
import cv2

class CascadeClassifier:
    def __init__(self,path):
        self.path = path
    def procesamiento(self,imgs,minSize):
        '''
        :param imgs: array de imagenes a procesar
        :return: array de imagenes de entrada con un rectangular pintado alrededor de cada coche detectado y de
        un tamaño minimo de 100*100 px
        '''
        for img in imgs:
            cascada = cv2.CascadeClassifier(self.path)
            rectangulos = cascada.detectMultiScale(img, minNeighbors=2, scaleFactor=1.4, minSize=minSize)
            for rect in rectangulos:
                x, y, w, h = rect
                cv2.rectangle(img, (x, y + h), (x + w, y), (0, 255, 0))
        return imgs


