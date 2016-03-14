#include <GL/glew.h>
#include <GL/freeglut.h>
#include <stdio.h>

using namespace std;

#define SCREEN_SIZE 500,500
#define SCREEN_POSITION 50,50
float day=0.0,year=0.0,month=0.0;
int tarea=4;
bool encendido=true;
float zoom =-12.0,desplazamiento = 0.0;
int leftButtonState = 0;
int yaux=0.0;
float rota=0.0,desp=0.0;

void initFunc();
void funReshape(int w, int h);
void funDisplay();
void drawSun();
void drawEarth();
void drawMoon();
void funIdle();
void processSpecialKeys(int key, int xx, int yy);
void funMouse(int a,int b, int c, int d);
void funMotion(int x, int y);

int main(int argc, char** argv) {
    
    glutInit(&argc, argv);
    glutInitDisplayMode(GLUT_DOUBLE | GLUT_RGB);
    
  // Inicializamos la ventana
    glutInitWindowSize(SCREEN_SIZE);
    glutInitWindowPosition(SCREEN_POSITION);
    glutCreateWindow("GLUT Test");
    
  //Inicializaciones especificas
    initFunc();
    
  //Configuracion de CallBacks (o eventos)
    glutReshapeFunc(funReshape);
    glutDisplayFunc(funDisplay);
    glutSpecialFunc(processSpecialKeys);
    glutMouseFunc(funMouse);
    glutMotionFunc(funMotion);
    glutIdleFunc(funIdle);
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
    gluPerspective(150.0, (GLfloat)w/(GLfloat)h,0.1,50.0);
    
    glMatrixMode(GL_MODELVIEW);
}

void funDisplay(){
    glClearColor(0.0, 0.0, 0.0, 0.0);
    glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
    glLoadIdentity();
    gluLookAt(0.0,desplazamiento,zoom,0.0,0.0,-20.0,0.0,1.0,0.0);
    switch (tarea){
        case 1:
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
            glTranslatef(0.0,0.0,-20.0);
            glRotatef(year,0.0,1.0,0.0);
            glTranslatef(4.0,0.0,0.0);
            glRotatef(month,0.0,1.0,0.0);
            glTranslatef(1.5,0.0,0.0);
            drawMoon();
            break;
        case 2:
            glPushMatrix();
                glTranslatef(0.0,0.0,-20.0);
                drawSun();
            glPopMatrix();
            glPushMatrix();
                glTranslatef(0.0,0.0,-20.0);
                glRotatef(-30,0.0,0.0,1.0);
                glRotatef(year,0.0,1.0,0.0);
                
                glTranslatef(4.0,0.0,0.0);            
                glRotatef(day,0.0,1.0,0.0);
                drawEarth();
            glPopMatrix();
            glTranslatef(0.0,0.0,-20.0);
           
            glRotatef(-30,0.0,0.0,1.0);
            glRotatef(year,0.0,1.0,0.0);
            
            glTranslatef(4.0,0.0,0.0);
            glRotatef(45,0.0,0.0,1.0);
            glRotatef(month,0.0,1.0,0.0);
            
            glTranslatef(1.5,0.0,0.0);
            drawMoon();
            break;
        case 3:
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
            glTranslatef(0.0,0.0,-20.0);
            glRotatef(year,0.0,1.0,0.0);
            glTranslatef(4.0,0.0,0.0);
            glRotatef(month,0.0,1.0,0.0);
            glTranslatef(1.5,0.0,0.0);
            drawMoon();
            break;
        case 4:
            glPushMatrix();
                glTranslatef(-5.0,0.0,-15.0);
            
                glColor3f(0.5,0.5,1.0);
                glScalef(1.0,0.5,0.5);
                glutSolidCube(3.0);
            glPopMatrix();
            glTranslatef(-3.7,0.0,-15.0);
            glRotatef(rota,0.0,0.0,1.0);
            glTranslatef(1.5,0.0,0.0);
            glColor3f(1.0,0.3,0.3);
            glScalef(1.0,0.5,0.5);
            glutSolidCube(3.0);
            glTranslatef(2.5,0.0,0.0);
            glTranslatef(desp,0.0,0.0);
            glColor3f(0.5,1.0,0.5);
            glScalef(1.0,0.4,0.4);
            glutSolidCube(3.0);
            
    }

     // Intercambio de buffers
    glutSwapBuffers();
}

void drawSun(){
    
    glPushMatrix();
        glColor3f(1.0,1.0,0.0);
        glRotatef(105,1.0,0.0,0.0);
        glutWireSphere(2.0,20,20);
    glPopMatrix();
    
}

void drawEarth(){
    
    glPushMatrix();
        glColor3f(0.0,0.0,1.0);        
        glRotatef(105,1.0,0.0,0.0);
        glutWireSphere(0.5,20,20);
    glPopMatrix();
    
}

void drawMoon(){
    
    glPushMatrix();
        glColor3f(1.0,1.0,1.0);
        glRotatef(105,1.0,0.0,0.0);
        glutWireSphere(0.1,20,20);
    glPopMatrix();
    
}
void processSpecialKeys(int key, int xx, int yy){
    
    switch(tarea){
        case 3:switch(key){
        
                    case GLUT_KEY_F1: encendido = !encendido;
                                      break;
                    case GLUT_KEY_UP: if (!encendido){
                                            year+=365%360;
                                        }
                                      break;
                    case GLUT_KEY_DOWN:if (!encendido){
                                            year-=365%360;
                                        }
                                       break;
                    case GLUT_KEY_LEFT:if (!encendido){
                                            month-=12%360;
                                        }
                                       break;
                    case GLUT_KEY_RIGHT:if (!encendido){
                                            month+=12%360;
                                        }
                                        break;
        
        
                    }
                    break;
        case 4: switch(key){
                    case GLUT_KEY_LEFT: desp-=1.0;
                                        break;
                    case GLUT_KEY_RIGHT: desp+=1.0;
                                         break;
                    case GLUT_KEY_UP: rota+=1.0;
                                      break;
                    case GLUT_KEY_DOWN: rota-=1.0;
                                        break;
                }
                break;
    }
    
    
}
void funMouse(int a,int b, int c, int d){
    
    if(tarea==3){
        switch (a){
        
            case 3: if(zoom>-14.5){
                     zoom-=0.1;
                    }
                    break;
            case 4: if(zoom<-10.0){
                        zoom +=0.1;
                    }
                    break;
            case GLUT_LEFT_BUTTON:
                    if(b==GLUT_DOWN){
                        leftButtonState=1;
                    }else {
                        leftButtonState=0;
                    }
        }
    }
    
}
void funMotion(int x, int y){
    if(tarea==3){
        if (leftButtonState==1){
            if(yaux>y){
                desplazamiento+=0.1;
            }else if (yaux<y){
                desplazamiento-=0.1;
            }
            yaux=y;
        }
    }
}

void funIdle(){
    if(tarea==3){
        if(encendido){
            day+=1%360;
            year+=365%360;
            month+=12%360;
        }
    } else{
        day+=1%360;
        year+=365%360;
        month+=12%360;
    }
    Sleep(50);
    glutPostRedisplay();
}