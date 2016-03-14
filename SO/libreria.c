<<<<<<< HEAD

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int head(int N);
int tail(int N);
int longlines(int N);

int head(int N){
	int contador = 0;
	char buf[1024];
	while( (fgets(buf, 1024, stdin) != NULL) && (contador < N) )
	{
		printf("%s", buf);
		contador++;
	}
	return 0;
}

int longlines(int N){

	int contador;
	char comparador[1024];
	struct infoLen {
		char *info;
		int len;
	};
	typedef struct infoLen infoLen;
	infoLen *buf;
	buf = (infoLen *) malloc (N*sizeof(infoLen));

	for (contador=0;contador<N;contador++){
		buf[contador].info = (char *) malloc (1024*sizeof(char));
		buf[contador].len = 0;
	}
	while( fgets(comparador, 1024, stdin) != NULL){
		infoLen entrante;
		entrante.info = (char *) malloc (1024*sizeof(char));
		strcpy(entrante.info,comparador);
		entrante.len = strlen(entrante.info);	
		contador = 0;
		if (buf[contador].len<=entrante.len){
			int metido = 0;
			while (metido==0){
				if (contador == N-1) {
					if (buf[contador].len <= entrante.len){ 					
						buf[contador] = entrante;
					}else {
						contador--;
						buf[contador] = entrante;
					}
					metido = 1;
				}else if (buf[contador].len <= entrante.len){
					buf[contador] = buf[contador+1];
					contador++;
				}else {
					contador--;
					buf[contador] = entrante;
					metido=1;					
				}					
			}
		}
	}
	for (contador=0; contador<N;contador++){
		if(buf[contador].len!=0){
			printf("%s",buf[contador].info);
		}
	}
	
	
	return 0;

}

int tail(int N) {
	int contador = 0;
	char comparador[1024];
	char **buf;
	int lleno = 0;

	buf = (char **) malloc (N*sizeof(char*));
	for (contador; contador<N;contador++){

		buf[contador] = (char *) malloc (1024*sizeof(char));

	}
	contador=0;

	while( (fgets(comparador, 1024, stdin) != NULL))
	{
		if (contador< N){
			strcpy(buf[contador],comparador);
			contador++;
		}else {
			contador = 0;
			strcpy(buf[contador],comparador);
			contador++;
			lleno = 1;
		}
	}
	int i;
	if (contador==N || !lleno){
		i=0;
	}else{
		i=contador;
	}
	contador--;
	printf("%s",buf[i]);
	while (i!=contador){
		if (i == N-1){
			i = 0;
		}else{
			i++;
		}
		printf("%s",buf[i]);
=======

#include <stdio.h>
#include <stdlib.h>
#include "libreria.h"

int head(int N);
int tail(int N);
int longlines(int N);

int head(int N){
	int contador = 0;
	char buf[1024];
	while( (fgets(buf, 1024, stdin) != NULL) && (contador < N) )
	{
		printf("%s", buf);
		contador++;
	}
	return 0;
}	

int longlines(int N){

	int contador = 0;
	char comparador[1024];
	char buf[N][1024];	
	while( (fgets(comparador, 1024, stdin) != NULL))
	{
		
		int i = N-1;
		if (length(buf[i])<length(comparador)){
			medio = false;
			while (	i>=0 && !metido){
					
				if (length(buf[i])<length(comparador)){
					buf[i] = buf[i--];
				}else {
					buf[i++] = comparador;
					metido=true;
				}

			}
		}
	}
	return 0;

}

int tail(int N) {
	int contador = 0;
	char comparador[1024];
	char buf[N][1024];
	while( (fgets(comparador, 1024, stdin) != NULL))
	{
		if (contador< N){
			buf[contador]=comparador;
			contador++;
		}else {
			contador = 0;
			buf[contador] = comparador;
			contador++;
		}	
	}
	int i=contador;
	contador--;
	while (i!=contador){
		if (i == N){
			i = 0;
		}
		printf("%s",buf[i]);
		i++;		
>>>>>>> origin/master
	}
	return 0;

}
