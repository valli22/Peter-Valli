#include <stdio.h>
#include <string.h>

tline* tokenize(char* str);

struct tcommand {
	char *filename;
	int argc;
	char **argv;
}

typedef struct tcommand tcommand;

struct tline {
	int ncommands;
	tcommand *commands;
	char *redirect_input;
	char *redirect_output;
	char *redirect_error;
}

typedef struct tline tline;

tline* tokenize(char* str){

	tline *comandos = (tline *) malloc (1024*sizeof(tline));
	(*comandos).ncommands = 0;
	(*comandos).commands = (tcommand *) malloc (1024*sizeof(tcommand));
	(*comandos).redirect_input = (char *) malloc (1024*sizeof(char));
	(*comandos).redirect_output = (char *) malloc (1024*sizeof(char));
	(*comandos).redirect_error = (char *) malloc (1024*sizeof(char));
	(*comandos).redirect_input = NULL;
	(*comandos).redirect_output = NULL;
	(*comandos).redirect_error = NULL;

	if (str[0]=='\0'){

		fprintf(stderr,"No se ha añadido nada");

	}else{
		int numeroComando = 0;
		int i=0;
		while (str[i]!='\0'){ //Inicio while principal, recorre toda la linea que entra por argumento

			int j = 0;
			int k = 0;
			(*comandos).commands[numeroComando].argc = 0;
			while ((str[i]!='|')||(str[i]!='\0')){ //Inicio while secundario, recorre los caracteres entre pipes

				if (str[i]==' ') { //En caso de que el caracter sea un espacio avanza el numero de argumentos
					k = 0;
					j++;
					(*comandos).commands[numeroComando].argc++;
				}else if (str[i] == '-'){ //En caso de que el caracter sea un - y anteriormente hubiese un espacio, entonces avanza en la linea
					if(k!=0){
						fprintf(stderr,"Linea de comandos mal escrita");
						exit 1;
					}else{
						i++;
					}
				}else if (str[i] == '<'){ //En caso de que el caracter sea un < y sea el primer comando, entonces avanza en la linea y reescribimos el input
					k = 0;
					if (numeroComando != 1) {
						fprintf(stderr,"Redireccion de entrada erronea. Sólo puede realizarse sobre el primer mandato");
						exit 1;
					}else{
						i++;
						while(str[i]!=' '){
							(*comandos).redirect_input[k] = str[i];
							k++;
							i++;
						}
					}
				}else if (str[i] == '>'){ //En caso de que el caracter sea un > hay dos opciones si es seguido de un & entonces reescribimos la salida error y sino reescribimos la salido estandar
					k = 0;
					i++;
					if (str[i]=='&'){
						i++;
						while(str[i]!=' '){
							(*comandos).redirect_erorr[k] = str[i];
							k++;
							i++;
						}
					}else{
						while(str[i]!= ' '){
							(*comandos).redirect_output[k] = str[i];
							k++;
							i++;
						}
					}
				}else { //En caso de que no sea ninguno de los anteriores se copia el caracter y se avanza en la linea
					(*comandos).commands[numeroComando].argv[j][k] = str[i];
					k++;
					i++;
				}


			}//Fin del while secundario
			if (numeroComando!=1) {
				(*comandos).commands[numeroComando].argc--;
			}
			i++;
			numeroComando++;

		}//Fin del while principal

	}

}
