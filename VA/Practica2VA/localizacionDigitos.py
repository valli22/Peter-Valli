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

        newContornos = []
        while len(contornosValidos)!=0:
            min = 99999
            for i in contornosValidos:
                if (i[1][0]<min):
                    min = i[1][0]
                    indice = i
            contornosValidos.remove(indice)
            newContornos.append(indice)

        caracteres =[]
        for ctn in newContornos:
            pto1 = (ctn[1][0],ctn[1][1])
            pto2 = (ctn[1][0]+ctn[1][2],ctn[1][1]+ctn[1][3])
            newImg = self.threshold[pto1[1]:pto2[1],pto1[0]:pto2[0]]
            newImg = cv2.resize(newImg,(10,10),interpolation=cv2.INTER_LINEAR)
            M = []
            for x in newImg:
                for y in x:
                    M.append(y)
            caracteres.append(np.asarray(M,dtype=np.float32))
            # cv2.rectangle(self.original,pto1,pto2,(255,255,255),1)
        return caracteres

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
        # Recorte de la imagen del caracter
        if(len(ars)!=0):
            pto1 = (ars[0][1][0],ars[0][1][1])
            pto2 = (ars[0][1][0]+ars[0][1][2],ars[0][1][1]+ars[0][1][3])
            newImg = I[pto1[1]:pto2[1],pto1[0]:pto2[0]]
        else:
            newImg = self.threshold.copy()
        # Redimensionamos la imagen del caracter y devolvemos su matriz como un vector de caracteristicas
        newImg = cv2.resize(newImg,(10,10),interpolation=cv2.INTER_LINEAR)
        M = []

        for x in newImg:
            for y in x:
                M.append(y)
        return M
