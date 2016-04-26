import cv2
import numpy as np
class LocalizacionDigitos:
    def __init__(self,img):
        blur = cv2.GaussianBlur(img,(3,3),0)
        self.original = img
        # Opciones para umbralizado
        # cv2.threshold(blur,127,255,cv2.THRESH_BINARY) Con valores elegidos por nosotros
        # cv2.threshold(blur,0,255,cv2.THRESH_BINARY+cv2.THRESH_OTSU) Mediante el metodo OTSU -> utilizado
        # cv2.adaptiveThreshold(img,255,cv2.ADAPTIVE_THRESH_GAUSSIAN_C,cv2.THRESH_BINARY,11,2) Adaptativo gausiano
        ret, self.threshold = cv2.threshold(blur,0,255,cv2.THRESH_BINARY+cv2.THRESH_OTSU)


    def letrasImagen(self):
        # De manera predefinida utiliza el mode = CV_RETR_LIST que devuelve todos los contornos en una lista
        I = self.threshold.copy()
        contornos, hierarchy = cv2.findContours(I,1,method=cv2.cv.CV_CHAIN_APPROX_NONE)

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

        for ctn in contornosValidos:
            pto1 = (ctn[1][0],ctn[1][1])
            pto2 = (ctn[1][0]+ctn[1][2],ctn[1][1]+ctn[1][3])
            cv2.rectangle(self.original,pto1,pto2,(255,255,255),1)
        cv2.imshow('prueba',self.original)
        cv2.waitKey()

    def caracter(self):
        I = self.threshold.copy()
        newImg = np.zeros((100,100),dtype=np.uint8)
        newImg.fill(255)
        for i in range(0,I.shape[0]):
            for j in range(0,I.shape[1]):
                newImg[i+25,j+25]=I[i,j]
        I = newImg.copy()
        # De manera predefinida utiliza el mode = CV_RETR_LIST que devuelve todos los contornos en una lista
        contornos, hierarchy = cv2.findContours(newImg,1 ,method=cv2.cv.CV_CHAIN_APPROX_NONE)
        ars =[]
        for ctn in contornos:
            x,y,w,h= cv2.boundingRect(ctn)
            ar =(w)*(h)
            if(self.threshold.shape[0]*0.25 < h) and (h>w):
                ars.append((ar,[x,y,w,h]))
        ars.sort()
        ars.reverse()
        if(len(ars)!=0):
            pto1 = (ars[0][1][0],ars[0][1][1])
            pto2 = (ars[0][1][0]+ars[0][1][2],ars[0][1][1]+ars[0][1][3])
            newImg = I[pto1[1]:pto2[1],pto1[0]:pto2[0]]
        else:
            newImg = self.threshold.copy()
        # cv2.imshow('prueba',newImg)
        # cv2.waitKey()

        newImg = cv2.resize(newImg,(10,10),interpolation=cv2.INTER_LINEAR)
        M = []

        for x in newImg:
            for y in x:
                M.append(y)
        return M

    def boundingBox(self,img):
        ladoSuperior= -1
        ladoInferior= -1
        ladoIzquierdo = -1
        ladoDerecho = -1
        for i in range(0,img.shape[0]):
            for x in range(0,img.shape[1]):
                if img[i][x] == 0:
                    ladoSuperior = i
                    break
            if(ladoSuperior!=-1):
                break
        for i in range(0,img.shape[1]):
            for x in range(0,img.shape[0]):
                if img[x][i] == 0:
                    ladoIzquierdo = i
                    break
            if(ladoIzquierdo!=-1):
                break
        for i in range(img.shape[0]-1,-1,-1):
            for x in range(img.shape[1]-1,-1,-1):
                if img[i][x] == 0:
                    ladoInferior = i
                    break
            if(ladoInferior!=-1):
                break
        for i in range(img.shape[1]-1,-1,-1):
            for x in range(img.shape[0]-1,-1,-1):
                if img[x][i] == 0:
                    ladoDerecho = i
                    break
            if(ladoDerecho!=-1):
                break
        return (ladoSuperior,ladoIzquierdo,ladoDerecho,ladoInferior)