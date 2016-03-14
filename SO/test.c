#include <stdio.h>
#include <stdlib.h>
#include "libreria.h"

int main(int argc, char *argv[]){
	if (argc==1) {
		fprintf(stderr, "ERROR DE ARGUMENTOS PRINCIPALES");
		return 1;
	}else{
		switch(atoi(argv[1])){
			case 1:
				head(atoi(argv[2]));
				break;
			case 2:
				tail(atoi(argv[2]));
				break;
			case 3: 
				longlines(atoi(argv[2]));
				break;
			default: 
				fprintf(stderr, "ERROR EN CASE");
				return 1;
		}
	}
	return 0;
	

}
