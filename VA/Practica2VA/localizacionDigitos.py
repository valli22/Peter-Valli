import cv2
import numpy as np
class LocalizacionDigitos:
    def __init__(self,img):
        blur = cv2.GaussianBlur(img,(3,3),0)

        # Opciones para umbralizado
        # cv2.threshold(blur,127,255,cv2.THRESH_BINARY) Con valores elegidos por nosotros
        # cv2.threshold(blur,0,255,cv2.THRESH_BINARY+cv2.THRESH_OTSU) Mediante el metodo OTSU -> utilizado
        # cv2.adaptiveThreshold(img,255,cv2.ADAPTIVE_THRESH_GAUSSIAN_C,cv2.THRESH_BINARY,11,2) Adaptativo gausiano
        ret,self.threshold = cv2.threshold(blur,0,255,cv2.THRESH_BINARY+cv2.THRESH_OTSU)




    def letrasImagen(self):
        # De manera predefinida utiliza el mode = CV_RETR_LIST que devuelve todos los contornos en una lista
        contornos, hierarchy = cv2.findContours(self.threshold,1,method=cv2.cv.CV_CHAIN_APPROX_NONE)
        I = self.threshold
        contornosValidos=[]
        #cv2.drawContours(I,contornos,-1,(255,255,255),1)
        ars = []
        for ctn in contornos:
            x,y,w,h= cv2.boundingRect(ctn)
            ar =(w)*(h)
            if(self.threshold.shape[0]*0.25 < h) and (h>w):

                ars.append((ar,[x,y,w,h]))

        ars.sort()
        ars.reverse()
        for x,ctnaux in enumerate(ars):
            if(x==8):
                break
            contornosValidos.append(ctnaux)


        # contornosValidos.append(ars[len(ars)/2])
        # for i in range(1,5):
        #     contornosValidos.append(ars[(len(ars)/2)+i])
        #     contornosValidos.append(ars[(len(ars)/2)-i])

        for ctn in contornosValidos:
            pto1 = (ctn[1][0],ctn[1][1])
            pto2 = (ctn[1][0]+ctn[1][2],ctn[1][1]+ctn[1][3])
            cv2.rectangle(I,pto1,pto2,(255,255,255),1)
        cv2.imshow('prueba',I)
        cv2.waitKey()

    def caracter(self):
        # De manera predefinida utiliza el mode = CV_RETR_LIST que devuelve todos los contornos en una lista
        contornos, hierarchy = cv2.findContours(self.threshold,1,method=cv2.cv.CV_CHAIN_APPROX_NONE)
        I = self.threshold
        contornosValidos=[]
        #cv2.drawContours(I,contornos,-1,(255,255,255),1)
        ars = []
        for ctn in contornos:
            x,y,w,h= cv2.boundingRect(ctn)
            ar =(w)*(h)
            if(self.threshold.shape[0]*0.25 < h) and (h>w):

                ars.append((ar,[x,y,w,h]))

        ars.sort()
        ars.reverse()
        for x,ctnaux in enumerate(ars):
            if(x==1):
                break
            contornosValidos.append(ctnaux)


        # contornosValidos.append(ars[len(ars)/2])
        # for i in range(1,5):
        #     contornosValidos.append(ars[(len(ars)/2)+i])
        #     contornosValidos.append(ars[(len(ars)/2)-i])

        for ctn in contornosValidos:
            pto1 = (ctn[1][0],ctn[1][1])
            pto2 = (ctn[1][0]+ctn[1][2],ctn[1][1]+ctn[1][3])
            # cv2.rectangle(I,pto1,pto2,(255,255,255),1)
            I = I[pto1[1]:pto2[1],pto1[0]:pto2[0]]
            # cv2.imshow('prueba',I)
            # cv2.waitKey()
        I = cv2.resize(I,(10,10),interpolation=cv2.INTER_LINEAR)
        M = []
        for x in I:
            for y in x:
                M.append(y)
        return M

