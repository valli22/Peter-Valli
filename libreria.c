
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
	}
	return 0;

}
