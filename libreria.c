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

	if (str[0]=='\0'){

		fprintf(stderr,"No se ha añadido nada");

	}else{


		int i=0;
		while (str[i]!='\0'){

		}

	}

}
