import cv2
import cascadeClassifier as ccl
import centro_coche as cc

class VideoCapture:

    def procesamientoCascade(self,path,pathXML):
        video = cv2.VideoCapture(path)
        cascade = ccl.CascadeClassifier(pathXML)
        while not video.isOpened():
            video = cv2.VideoCapture(path)
            cv2.waitKey(1000)
            print "cargando video..."

        pos_frame = video.get(cv2.cv.CV_CAP_PROP_POS_FRAMES)
        while True:
            flag,frame = video.read()
            if flag:
                frame = cascade.procesamiento([frame],(15,15))
                cv2.imshow("Video",frame[0])
                pos_frame= video.get(cv2.cv.CV_CAP_PROP_POS_FRAMES)
            else:
                video.set(cv2.cv.CV_CAP_PROP_POS_FRAMES, pos_frame-1)
                print "frame is not ready"
                cv2.waitKey(1000)
            if cv2.waitKey(10) == 27:
                break
            if video.get(cv2.cv.CV_CAP_PROP_POS_FRAMES)== video.get(cv2.cv.CV_CAP_PROP_FRAME_COUNT):
                break

    def procesamientoCentro(self,path,imgs):
        video = cv2.VideoCapture(path)
        centroCoche = cc.Centro_coche()
        centroCoche.entrenamiento(imgs)
        while not video.isOpened():
            video = cv2.VideoCapture(path)
            cv2.waitKey(1000)
            print "cargando video..."

        pos_frame = video.get(cv2.cv.CV_CAP_PROP_POS_FRAMES)
        while True:
            flag,frame = video.read()
            if flag:
                frame = centroCoche.procesamiento([frame])
                cv2.imshow("Video",frame[0])
                pos_frame= video.get(cv2.cv.CV_CAP_PROP_POS_FRAMES)
            else:
                video.set(cv2.cv.CV_CAP_PROP_POS_FRAMES, pos_frame-1)
                print "frame is not ready"
                cv2.waitKey(1000)
            if cv2.waitKey(10) == 27:
                break
            if video.get(cv2.cv.CV_CAP_PROP_POS_FRAMES)== video.get(cv2.cv.CV_CAP_PROP_FRAME_COUNT):
                break