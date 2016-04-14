import cv2
import os
class Entrada_salida:
    path= ''
    def __init__(self,path):
        self.path = path

    def datos_Path(self):
        array=[]
        for (dirpath, dirname, filename) in os.walk(self.path):
            for file in filename:
                pathFile = self.path +'/'+file
                I = cv2.imread(pathFile,0)
                array.append(I)
        return array