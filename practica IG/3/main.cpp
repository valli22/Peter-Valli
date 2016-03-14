#include <GL/glew.h>
#include <GL/freeglut.h>
#include <stdio.h>
#include "RgbImage.h"

#define SCREEN_SIZE     500, 500
#define SCREEN_POSITION  50,  50

#define BG_COLOR 0.0, 0.0, 0.0, 0.0

void initFunc();
void destroyFunc();
void funReshape(int w, int h);
void funDisplay();
void drawLights();
void drawRoom();
void drawCandle();
void drawObject();
void drawSun();
void drawMoon();
void drawEarth();
void drawBackground();
void funSpecialKeyboard(int key, int x, int y);
void funMouse(int button, int state, int x, int y);
void funKeyboard(unsigned char key, int x, int y);
void funIdle();

int tarea=3;

// Control de animaciones
GLfloat angX = 0.0, angY = 0.0, zoom  = 1.0;
GLfloat moveX = 0.0, moveZ = 0.0;
GLfloat year = 0.0, month = 0.0, day=0.0;
bool encendido = false;

// Zoom
GLsizei current_w, current_h;

// Posicion de la luz
GLfloat PL2[] = {2.0, 3.0, -5.0, 1.0 };
GLfloat PL0[] = {0.0,0.0,-15.0};
GLfloat PL1[] = {0.0,0.0,0.0};
GLfloat intensity=0.8;
GLfloat ambiental=0.7;

// Se reservan NT nombres para las texturas
#define NT 4
GLuint  textureName[NT];

int main(int argc, char** argv) {
    
    glutInit(&argc,argv);
    glutInitDisplayMode(GLUT_DOUBLE | GLUT_RGB | GLUT_DEPTH);

    glutInitWindowSize(SCREEN_SIZE);
    glutInitWindowPosition(SCREEN_POSITION);
    glutCreateWindow("Tema 7.5: Texturas");

    initFunc();

    glutReshapeFunc(funReshape);
    glutDisplayFunc(funDisplay);
    glutSpecialFunc(funSpecialKeyboard);
    glutKeyboardFunc(funKeyboard);
    glutMouseFunc(funMouse);
    glutIdleFunc(funIdle);
            
    glutMainLoop();
    
    destroyFunc();

    return(0);
}

void initFunc() {
    
 // Inicializamos GLEW
    GLenum err = glewInit();
    if(GLEW_OK != err) {
        printf("Error: %s\n",glewGetErrorString(err));
    }
    printf("Using GLEW %s\n", glewGetString(GLEW_VERSION));
    printf("This system supports OpenGL Version %s.\n",glGetString(GL_VERSION));
    
 // Habilitamos z-buffer
    glEnable(GL_DEPTH_TEST);

 // Modelo de Iluminación
    glEnable(GL_LIGHTING);
    if(tarea==1){
        GLfloat IA[]  = { 0.7, 0.7 ,0.7, 1.0 };
        glLightModelfv(GL_LIGHT_MODEL_AMBIENT, IA);
    }else{
        glLightModeli(GL_LIGHT_MODEL_TWO_SIDE, GL_TRUE);
        glLightModeli(GL_LIGHT_MODEL_COLOR_CONTROL, GL_SEPARATE_SPECULAR_COLOR);
        GLfloat Ia0[] = { 0.2, 0.2, 0.2, 1.0 };
        GLfloat Id0[] = { 2.0, 2.0, 2.0, 1.0 };
        GLfloat Is0[] = { 0.3, 0.3, 0.3, 1.0 };
        glLightfv(GL_LIGHT0, GL_AMBIENT , Ia0);
        glLightfv(GL_LIGHT0, GL_DIFFUSE , Id0);
        glLightfv(GL_LIGHT0, GL_SPECULAR, Is0);
        glEnable(GL_LIGHT0);
        
    }
    
 // Parámetros de la Luz 1 (posicional=candle)
    
    
 // Modelo de Sombreado
    glShadeModel(GL_SMOOTH);
    glEnable(GL_NORMALIZE);
    
 // Texturas
    glEnable(GL_TEXTURE_2D);
    glGenTextures(NT,textureName);
 
    const char *filename[NT] = { "common/img/imgSun.bmp",
                                 "common/img/imgPlanet1.bmp",
                                 "common/img/imgMoon.bmp",
                                 "common/img/imgSpace"};
    
    for(unsigned i=0; i<NT; i++) {
        
     // Cargamos la textura
        glBindTexture(GL_TEXTURE_2D, textureName[i]);
        RgbImage texture(filename[i]);
        //glTexImage2D(GL_TEXTURE_2D, 0, GL_RGB, texture.GetNumCols(), texture.GetNumRows(), 0, GL_RGB, GL_UNSIGNED_BYTE, texture.ImageData());
        gluBuild2DMipmaps(GL_TEXTURE_2D, 3, texture.GetNumCols(), texture.GetNumRows(), GL_RGB, GL_UNSIGNED_BYTE, texture.ImageData());
        
     // Configuramos la textura
        glHint(GL_PERSPECTIVE_CORRECTION_HINT, GL_NICEST);
        glTexEnvf(GL_TEXTURE_ENV, GL_TEXTURE_ENV_MODE, GL_MODULATE);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_REPEAT);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_REPEAT);
        glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
        //glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
        
    }
    
}

void destroyFunc() {
    
    glDeleteTextures(NT,textureName);
    
}

void funReshape(int w, int h) {
    
 // Guardamos la anchura y altura actuales en un par de variables globales
    current_w = w;
    current_h = h;
    
 // Se configura el Viewport
    glViewport(0, 0, w, h);

 // Se configura la cámara    
    glMatrixMode(GL_PROJECTION);
    glLoadIdentity();
    
 // Proyeccion en Perspectiva
    GLfloat aspectRatio = (GLfloat)w/(GLfloat)h;    
    GLfloat fov  = 75.0*zoom, nplane = 1.0, fplane = 25.0;
    gluPerspective(fov, aspectRatio, nplane, fplane);
    
}

void funDisplay(void) {
    
 // Se borra el buffer de color y el z-buffer
    glClearColor(BG_COLOR);
    glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

 // Cargamos la identidad
    glMatrixMode(GL_MODELVIEW);
    glLoadIdentity();

 // Posicionamos la cámara
    GLfloat pos[3]    = {0.0,  2.0,  0.0};
    GLfloat lookat[3] = {0.0,  2.0, -5.0};
    GLfloat up[3]     = {0.0,  1.0,  0.0};
    gluLookAt(    pos[0],    pos[1],    pos[2],
               lookat[0], lookat[1], lookat[2],
                   up[0],     up[1],     up[2]);
    
 // Dibujamos la escena 
    if(tarea==1){
        GLfloat Ia2[] = { 0.2, 0.2, 0.2, 1.0 };
        GLfloat Id2[] = { intensity, intensity, intensity, 1.0 };
        GLfloat Is2[] = { 0.7, 0.7, 0.7, 1.0 };
        glLightfv(GL_LIGHT2, GL_AMBIENT , Ia2);
        glLightfv(GL_LIGHT2, GL_DIFFUSE , Id2);
        glLightfv(GL_LIGHT2, GL_SPECULAR, Is2);
        glLightf (GL_LIGHT2, GL_CONSTANT_ATTENUATION , 0.90);
        glLightf (GL_LIGHT2, GL_LINEAR_ATTENUATION   , 0.05);
        glLightf (GL_LIGHT2, GL_QUADRATIC_ATTENUATION, 0.01);
        glEnable(GL_LIGHT2);

        glPushMatrix();
            glTranslatef(moveX,0.0,moveZ);
            glLightfv(GL_LIGHT2, GL_POSITION, PL2);
            drawCandle();
        glPopMatrix();
        drawRoom();
        drawObject();
    }else{
        GLfloat IA[]  = { ambiental, ambiental ,ambiental, 1.0 };
        glLightModelfv(GL_LIGHT_MODEL_AMBIENT, IA);
        GLfloat Ia1[] = { 0.2, 0.2, 0.2, 1.0 };
        GLfloat Id1[] = { 24.0, 24.0, 24.0, 1.0 };
        GLfloat Is1[] = { 0.3, 0.3, 0.3, 1.0 };
        glLightfv(GL_LIGHT1, GL_AMBIENT , Ia1);
        glLightfv(GL_LIGHT1, GL_DIFFUSE , Id1);
        glLightfv(GL_LIGHT1, GL_SPECULAR, Is1);
        
        glLightfv(GL_LIGHT0,GL_POSITION,PL0);
        
        
            glPushMatrix();
                glTranslatef(0.0,0.0,-20.0);
                drawSun();
            glPopMatrix();
            glPushMatrix();
                glTranslatef(0.0,0.0,-20.0);
                glRotatef(year,0.0,1.0,0.0);
                glTranslatef(4.0,0.0,0.0);            
                glRotatef(day,0.0,1.0,0.0);
                drawEarth();
            glPopMatrix();
            glPushMatrix();
                glTranslatef(0.0,0.0,-20.0);
                glRotatef(year,0.0,1.0,0.0);
                glTranslatef(4.0,0.0,0.0);
                glRotatef(month,0.0,1.0,0.0);
                glTranslatef(1.5,0.0,0.0);
                glLightfv(GL_LIGHT1,GL_POSITION,PL1);
                drawMoon();
            glPopMatrix();
            drawBackground();
    }
    
    glutSwapBuffers();
    
}
void drawCandle(){
    
    // Definimos el material del Objeto
    GLfloat Ka[] = { 0.8, 0.8, 0.2, 1.0 };
    GLfloat Kd[] = { 0.7, 0.7, 0.3, 1.0 };
    GLfloat Ks[] = { 0.0, 0.0, 0.0, 1.0 };
    glMaterialfv(GL_FRONT, GL_AMBIENT  , Ka);
    glMaterialfv(GL_FRONT, GL_DIFFUSE  , Kd);
    glMaterialfv(GL_FRONT, GL_SPECULAR , Ks);
    glMaterialf (GL_FRONT, GL_SHININESS, 50.0);
    
    glPushMatrix();
        glTranslatef(2.0, 1.0, -5.0);
        glRotatef(-90, 1.0, 0.0, 0.0);
        glutSolidCone(0.5,2.0,20.0,20.0);
    glPopMatrix();
    
}

void drawRoom() {

 // Definimos el material de a habitación
    GLfloat Ka[] = { 0.2, 0.2, 0.2, 1.0 };
    GLfloat Kd[] = { 0.7, 0.7, 0.7, 1.0 };
    GLfloat Ks[] = { 0.8, 0.8, 0.8, 1.0 };
    glMaterialfv(GL_FRONT, GL_AMBIENT  , Ka);
    glMaterialfv(GL_FRONT, GL_DIFFUSE  , Kd);
    glMaterialfv(GL_FRONT, GL_SPECULAR , Ks);
    glMaterialf (GL_FRONT, GL_SHININESS, 50.0);
            
 // Suelo: Con coordenadas de textura entre 0 y 1
    glBegin(GL_QUADS);
        glNormal3f(0.0, 1.0, 0.0);
         glVertex3f( -6.0,  0.0,   0.0);
         glVertex3f(  6.0,  0.0,   0.0);
	 glVertex3f(  6.0,  0.0, -24.0);
         glVertex3f( -6.0,  0.0, -24.0);
    glEnd();
    
 // Techo: Con coordenadas de textura fuera de rango
    
    glBegin(GL_QUADS);
        glNormal3f(0.0, 0.0, -1.0);
         glVertex3f( -6.0,  6.0, -24.0);
         glVertex3f(  6.0,  6.0, -24.0);
	 glVertex3f(  6.0,  6.0,   0.0);
         glVertex3f( -6.0,  6.0,   0.0);
    glEnd();

 // Pared izquierda: Con coordenadas de textura entre 0 y 1
    
    glBegin(GL_QUADS);
        glNormal3f(1.0, 0.0, 0.0);
         glVertex3f( -6.0,  0.0,   0.0);
         glVertex3f( -6.0,  0.0, -24.0);
	 glVertex3f( -6.0,  6.0, -24.0);
         glVertex3f( -6.0,  6.0,   0.0);
    glEnd();
    
 // Pared derecha: Con coordenadas de textura fuera de rango
    
    glBegin(GL_QUADS);
        glNormal3f(-1.0, 0.0, 0.0);
         glVertex3f(  6.0,  0.0, -24.0);
         glVertex3f(  6.0,  0.0,   0.0);
	 glVertex3f(  6.0,  6.0,   0.0);
         glVertex3f(  6.0,  6.0, -24.0);
    glEnd();
    
 // Pared fondo: Con coordenadas de textura fuera de rango
    
    glBegin(GL_QUADS);
        glNormal3f(0.0, 0.0, 1.0);
         glVertex3f( -6.0,  0.0, -24.0);
         glVertex3f(  6.0,  0.0, -24.0);
	 glVertex3f(  6.0,  6.0, -24.0);
         glVertex3f( -6.0,  6.0, -24.0);
    glEnd();
    
}

void drawObject() {
    
 // Definimos el material del Objeto
    GLfloat Ka[] = { 0.2, 0.2, 0.2, 1.0 };
    GLfloat Kd[] = { 0.7, 0.7, 0.3, 1.0 };
    GLfloat Ks[] = { 0.5, 0.5, 0.5, 1.0 };
    glMaterialfv(GL_FRONT, GL_AMBIENT  , Ka);
    glMaterialfv(GL_FRONT, GL_DIFFUSE  , Kd);
    glMaterialfv(GL_FRONT, GL_SPECULAR , Ks);
    glMaterialf (GL_FRONT, GL_SHININESS, 50.0);
    
    glPushMatrix();
        glTranslatef(0.0, 2.0, -5.0);
        glutSolidTeapot(1.0);
    glPopMatrix();

}
void drawSun(){
    if(tarea==3){
        glBindTexture(GL_TEXTURE_2D, textureName[0]);
        glTexGeni(GL_S, GL_TEXTURE_GEN_MODE, GL_SPHERE_MAP);
        glTexGeni(GL_T, GL_TEXTURE_GEN_MODE, GL_SPHERE_MAP);
        glEnable(GL_TEXTURE_GEN_S);
        glEnable(GL_TEXTURE_GEN_T);
    }
    
    GLfloat Ka[] = { 0.5, 0.5, 0.0, 1.0 };
    GLfloat Kd[] = { 0.5, 0.5, 0.0, 1.0 };
    GLfloat Ks[] = { 0.5, 0.5, 0.2, 1.0 };
    glMaterialfv(GL_FRONT, GL_AMBIENT  , Ka);
    glMaterialfv(GL_FRONT, GL_DIFFUSE  , Kd);
    glMaterialfv(GL_FRONT, GL_SPECULAR , Ks);
    glMaterialf (GL_FRONT, GL_SHININESS, 50.0);
    
    glPushMatrix();
        glRotatef(105,1.0,0.0,0.0);
        glutSolidSphere(2.0,20,20);
    glPopMatrix();

}

void drawEarth(){
    if(tarea==3){
        glBindTexture(GL_TEXTURE_2D, textureName[1]);
        glTexGeni(GL_S, GL_TEXTURE_GEN_MODE, GL_SPHERE_MAP);
        glTexGeni(GL_T, GL_TEXTURE_GEN_MODE, GL_SPHERE_MAP);
        glEnable(GL_TEXTURE_GEN_S);
        glEnable(GL_TEXTURE_GEN_T);
    }
    
    GLfloat Ka[] = { 0.0, 0.0, 0.5, 1.0 };
    GLfloat Kd[] = { 0.0, 0.0, 0.5, 1.0 };
    GLfloat Ks[] = { 0.2, 0.2, 0.5, 1.0 };
    glMaterialfv(GL_FRONT, GL_AMBIENT  , Ka);
    glMaterialfv(GL_FRONT, GL_DIFFUSE  , Kd);
    glMaterialfv(GL_FRONT, GL_SPECULAR , Ks);
    glMaterialf (GL_FRONT, GL_SHININESS, 50.0);
    
    glPushMatrix();    
        glRotatef(105,1.0,0.0,0.0);
        glutSolidSphere(0.5,20,20);
    glPopMatrix();

}

void drawMoon(){
    if(tarea==3){
        glBindTexture(GL_TEXTURE_2D, textureName[2]);
        glTexGeni(GL_S, GL_TEXTURE_GEN_MODE, GL_SPHERE_MAP);
        glTexGeni(GL_T, GL_TEXTURE_GEN_MODE, GL_SPHERE_MAP);
        glEnable(GL_TEXTURE_GEN_S);
        glEnable(GL_TEXTURE_GEN_T);
    }
    
    GLfloat Ka[] = { 0.5, 0.5, 0.5, 1.0 };
    GLfloat Kd[] = { 0.5, 0.5, 0.5, 1.0 };
    GLfloat Ks[] = { 0.7, 0.7, 0.7, 1.0 };
    glMaterialfv(GL_FRONT, GL_AMBIENT  , Ka);
    glMaterialfv(GL_FRONT, GL_DIFFUSE  , Kd);
    glMaterialfv(GL_FRONT, GL_SPECULAR , Ks);
    glMaterialf (GL_FRONT, GL_SHININESS, 50.0);
    
    glPushMatrix();
        glRotatef(105,1.0,0.0,0.0);
        glutSolidSphere(0.1,20,20);
    glPopMatrix();
//    glDisable(GL_TEXTURE_GEN_T);
//    glDisable(GL_TEXTURE_GEN_S);
}
void drawBackground(){
    glBindTexture(GL_TEXTURE_2D, textureName[3]);
    glBegin(GL_QUADS);
        glNormal3f(0.0, 0.0, 1.0);
         glTexCoord2f(0.0, 0.0);glVertex3f( -24.0,  0.0, -40.0);
         glTexCoord2f(4.0, 0.0);glVertex3f(  24.0,  0.0, -40.0);
	 glTexCoord2f(4.0, 2.0);glVertex3f(  24.0,  24.0, -40.0);
         glTexCoord2f(0.0, 2.0);glVertex3f( -24.0,  24.0, -40.0);
    glEnd();
}

void funSpecialKeyboard(int key, int x, int y) {
    
    switch(key) {
       case GLUT_KEY_RIGHT:
           moveX = moveX + 0.5;
           break;
       case GLUT_KEY_LEFT:
           moveX = moveX - 0.5;
           break;
       case GLUT_KEY_DOWN:
           moveZ = moveZ + 0.5;
           break;
       case GLUT_KEY_UP:
           moveZ = moveZ - 0.5;
           break;
   }    
   
   glutPostRedisplay();

}

void funMouse(int button, int state, int x, int y) {
    
    switch(button) {
        case 3:
            zoom -= 0.05;
            if(zoom<0.5) zoom = 0.5;
            funReshape(current_w, current_h);
            break;
        case 4:
            zoom += 0.05;
            if(zoom>1.5) zoom = 1.5;
            funReshape(current_w, current_h);
            break;
    }
   
    glutPostRedisplay();
    
}
void funKeyboard(unsigned char key, int x, int y){
    if(tarea==1){
        switch(key){
        
            case '+':
                intensity += 0.1;
                break;
            case '-':
                intensity -= 0.1;
                break;
       
    }
    }else{
        switch(key){
             case '+':
                ambiental += 0.1;
                break;
             case '-':
                ambiental -= 0.1;
                break;
            case 'e':
                if(!encendido){
                    glEnable(GL_LIGHT1);
                }else{
                    glDisable(GL_LIGHT1);
                }
                encendido=!encendido;
                break;
        }
    }
}

void funIdle(){
    
    day+=1%360;
    year+=365%360;
    month+=12%360;
    
    Sleep(50);
    glutPostRedisplay();            
}