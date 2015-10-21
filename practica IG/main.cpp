#include <GL/glew.h>
#include <GL/freeglut.h>
#include <stdio.h>

using namespace std;

#define SCREEN_SIZE 500,500
#define SCREEN_POSITION 50,50


void initFunc();
void funReshape(int w, int h);
void funDisplay();
void drawNumber();
void drawSphere();

int main(int argc, char** argv) {
    
    glutInit(&argc, argv);
    glutInitDisplayMode(GLUT_DOUBLE | GLUT_RGB);
    
  // Inicializamos la ventana
    glutInitWindowSize(SCREEN_SIZE);
    glutInitWindowPosition(SCREEN_POSITION);
    glutCreateWindow("Pedro Redondo Rabanal - David Vacas Miguel");
    
  //Inicializaciones especificas
    initFunc();
    
  //Configuracion de CallBacks (o eventos)
    glutReshapeFunc(funReshape);
    glutDisplayFunc(funDisplay);
    
    glutMainLoop();
    return 0;
}

void initFunc(){
    
    //Inicializar GLEW
    GLenum err = glewInit();
    if(GLEW_OK != err) {
        printf("Error: %s\n", glewGetErrorString(err));
    }
    printf("Status: Using glew %s\n", glewGetString(GLEW_VERSION));
    
    // Mas cosas
    //Activas el poder quitar las partes que no se vean porque tienen cosas delante
    glEnable(GL_DEPTH_TEST);
    //Activas poder usar glCullFace
    glEnable(GL_CULL_FACE);
    //glFrontFace = criterio de la mano derecha con ccw/criterio de la mano izquiera con cw; si no pones nada es el criterio de la mano derecha
    //glFrontFace(GL_CCW);
    
    //hace que se dejen de pintar las caras traseras (si pones front las de delante y se puede ambas); debes activarlo primero con glEnable
    glCullFace(GL_BACK);
    /*
    Pinta con el color del ultimo vertice (con GL_FLAT);
    con GL_SMOOTH pinta con colores interpolados
    */ 
    glShadeModel(GL_SMOOTH);
}

void funReshape (int w,int h){
    
    glViewport(0,0,w,h);
    
    glMatrixMode(GL_PROJECTION);
    glLoadIdentity();
    gluPerspective(120.0, (GLfloat)w/(GLfloat)h,0.1,50.0);
//    GLfloat left=-2.0,right=2.0,bottom=-2.0,top=2.0,nplane = 3.0, fplane = 10.0;
//    glFrustum(left, right, bottom, top, nplane, fplane);
    
    glMatrixMode(GL_MODELVIEW);
}

void funDisplay(){
   
    // Borramos el buffer de color
    glClearColor(0.0, 0.0, 0.0, 0.0);
    glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
    glLoadIdentity();
    
    glPushMatrix();
        glRotatef(-30,0.0,1.0,0.0);
        glTranslatef(0.0,0.0,-1.0);
        glScalef(0.3,0.3,0.0);
        drawNumber();
    glPopMatrix();
    
    glTranslatef(0.0,0.0,-3.0);
    glRotatef(-30,0.0,1.0,0.0);
    drawSphere();
    
    

     // Intercambio de buffers
    glutSwapBuffers();
}

void drawNumber() {
    
    glColor3f(1.0,1.0,1.0);
    glLineWidth(5.0);
    
    glBegin(GL_LINE_LOOP);
    
        glVertex3f(-0.25,-1.0,0.0); //1
        glVertex3f(0.25,-1.0,0.0); //2
        glVertex3f(0.25,1.0,0.0); //3
        glVertex3f(-0.25,1.0,0.0); //4
        glVertex3f(-0.75,0.5,0.0); //5
        glVertex3f(-0.25,0.5,0.0); //6
        
    glEnd();
           
}

void drawSphere() {
    
    glLineWidth(2.0);
    glColor3f(1.0,1.0,0.0);
    glutWireSphere(2.0,20,20);
    
}
