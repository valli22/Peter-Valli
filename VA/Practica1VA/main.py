import cv2
import numpy as np

training = []
# Lectura de imagenes
for i in range(1, 49):
    dir = "C:\Users\pdred\Desktop\VA\Practica 1/training/training/frontal_" + str(i) + ".jpg"
    I = cv2.imread(dir, 0)
    training.append(I)

# video = cv2.VideoCapture('C:\Users\pdred\Desktop\VA\Practica 1\Videos\Videos/video2.wmv')
# while not video.isOpened():
#     video = cv2.VideoCapture('C:\Users\pdred\Desktop\VA\Practica 1\Videos\Videos/video2.wmv')
#     cv2.waitKey(1000)
#     print "esperando.."
#
# pos_frame = video.get(cv2.cv.CV_CAP_PROP_POS_FRAMES)
# while True:
#     flag,frame = video.read()
#     if flag:
#         cv2.imshow("Video",frame)
#         pos_frame= video.get(cv2.cv.CV_CAP_PROP_POS_FRAMES)
#         print str(pos_frame)+" frames"
#     else:
#         video.set(cv2.cv.CV_CAP_PROP_POS_FRAMES, pos_frame-1)
#         print "frame is not ready"
#         cv2.waitKey(1000)
#     if cv2.waitKey(10) == 27:
#         break
#     if video.get(cv2.cv.CV_CAP_PROP_POS_FRAMES)== video.get(cv2.cv.CV_CAP_PROP_FRAME_COUNT):
#         break


# Entrenamiento del clasificador por flann con lsh
FLANN_INDEX_LSH = 0
index_params = dict(algorithm=FLANN_INDEX_LSH, table_numer=6, key_size=12, multi_prove_level=1)
search_params = dict(checks=100)
flann = cv2.FlannBasedMatcher(indexParams=index_params, searchParams=search_params)

kps = []
descriptores = []
vectoresVotacion = []
for im1 in training:
    orb = cv2.ORB(100, 1.3, 4)
    kp, des = orb.detectAndCompute(im1, None)

    for x, keypoint in enumerate(kp):
        kps.append(keypoint)
        vectoresVotacion.append((110 - keypoint.pt[1], 225 - keypoint.pt[0]))
    descriptores.append(des)
    flann.add(np.asarray(des, np.float32))

flann.train()

# Procesamiento de imagen


test = []
for i in range(1, 10):
    dir = "C:\Users\pdred\Desktop\VA\Practica 1/testing/testing/test" + str(i) + ".jpg"
    I = cv2.imread(dir, 0)
    test.append(I)

kpsTest = []
descriptoresTest = []

for im1 in test:
    vectorVotacion = np.zeros((im1.shape[0] / 10, im1.shape[1] / 10), dtype=np.int)

    # Primero sacar los descriptores de las imagenes de test
    kp, des = orb.detectAndCompute(im1, None)

    # Indices de los descriptores
    indices = flann.knnMatch(np.asarray(des, np.float32), k=6)

    # Por cada keypoint realizamos la votacion de donde se cree que esta el centro
    for x, keypoint in enumerate(kp):
        for indice in indices[x]:
            col = int(
                vectoresVotacion[indice.trainIdx][0] * (keypoint.size / kps[indice.trainIdx].size) + keypoint.pt[0])
            row = int(
                vectoresVotacion[indice.trainIdx][1] * (keypoint.size / kps[indice.trainIdx].size) + keypoint.pt[1])

            col = np.ceil(col / 10)
            row = np.ceil(row / 10)

            if col >= 0 and col < im1.shape[1] / 10:
                if row >= 0 and row < im1.shape[0] / 10:
                    vectorVotacion[row][col] += 1

    # Detectamos el punto max de la matriz de votacion
    punto = 0
    locPunto = (0, 0)
    for x, fila in enumerate(vectorVotacion):
        for y in range(0, len(fila)):
            if fila[y] >= punto:
                punto = fila[y]
                locPunto = (x, y)

    # Deteccion y dibujado de rectangulo alrededor del frontal del coche
    cascada = cv2.CascadeClassifier('C:\Users\pdred\Desktop\VA\Practica 1\haar\haar/coches.xml', )
    rectangulos = cascada.detectMultiScale(im1, minNeighbors=2, scaleFactor=1.4, minSize=(100, 100))
    for rect in rectangulos:
        x, y, w, h = rect
        print x, y, w, h
        cv2.rectangle(im1, (x, y + h), (x + w, y), (0, 255, 0))
    cv2.imshow("Prueba", im1)
    cv2.waitKey()

#Hacer funcion  para coger las imagenes y que te devuelva un array de estas en blanco y negro