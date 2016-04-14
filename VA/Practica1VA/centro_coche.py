import cv2
import numpy as np

class Centro_coche:


    def __init__(self):
        FLANN_INDEX_LSH = 0
        index_params = dict(algorithm=FLANN_INDEX_LSH, table_numer=6, key_size=12, multi_prove_level=1)
        search_params = dict(checks=100)
        self.flann = cv2.FlannBasedMatcher(indexParams=index_params, searchParams=search_params)
        self.keypoints = []
        self.descriptores = []
        self.vectoresKp = []
        self.orb = cv2.ORB(100, 1.3, 4)

    def entrenamiento(self,imgs):
        '''
        :param imgs: array de imagenes de entrenamiento
        '''
        for img in imgs:
            kp, des = self.orb.detectAndCompute(img, None)
            for x, keypoint in enumerate(kp):
                self.keypoints.append(keypoint)
                self.vectoresKp.append((110 - keypoint.pt[1], 225 - keypoint.pt[0]))
            self.descriptores.append(des)
            self.flann.add(np.asarray(des, np.float32))

        self.flann.train()

    def procesamiento(self,imgs):
        '''
        :param imgs: array de imagenes a procesar
        :return: el array de imagenes de entrada con un circulo pintado en el centro de cada coche detectado
        '''
        for img in imgs:
            vectorVotacion = np.zeros((img.shape[0] / 10, img.shape[1] / 10), dtype=np.int)

            # Primero sacar los descriptores de las imagenes de test
            kp, des = self.orb.detectAndCompute(img, None)

            # Indices de los descriptores
            indices = self.flann.knnMatch(np.asarray(des, np.float32), k=6)

            # Por cada keypoint realizamos la votacion de donde se cree que esta el centro
            for x, keypoint in enumerate(kp):
                for indice in indices[x]:
                    col = int(
                        self.vectoresKp[indice.trainIdx][0] * (keypoint.size / self.keypoints[indice.trainIdx].size) + keypoint.pt[0])
                    row = int(
                        self.vectoresKp[indice.trainIdx][1] * (keypoint.size / self.keypoints[indice.trainIdx].size) + keypoint.pt[1])

                    col = np.ceil(col / 10)
                    row = np.ceil(row / 10)

                    if col >= 0 and col < img.shape[1] / 10:
                        if row >= 0 and row < img.shape[0] / 10:
                            vectorVotacion[row][col] += 1

            # Detectamos el punto max de la matriz de votacion
            punto = 0
            locPunto = (0, 0)
            for x, fila in enumerate(vectorVotacion):
                for y in range(0, len(fila)):
                    if fila[y] >= punto:
                        punto = fila[y]
                        locPunto = (x, y)

            # Dibujamos el centro del coche en la imagen
            cv2.circle(img,(locPunto[1]*10,locPunto[0]*10),10,(0,255,0))
        return imgs
