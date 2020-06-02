#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

int dia, mes, ano;


int bissexto(int ano) {
	return ano % 4 == 0 && (ano % 100 != 0 || ano % 400 == 0);
}


int dias_mes(int mes, int ano) {
	switch(mes) {
		case 1:
		case 3:
		case 5:
		case 7:
		case 8:
		case 10:
		case 12:
			return 31;
		case 4:
		case 6:
		case 9:
		case 11:
			return 30;
		case 2:
			return 28 + bissexto(ano);
		default:
			break;
	}
}


int dias_ate() {
	int dd = 0, mm;

	for (mm = 1; mm < mes; ++mm)
		dd += dias_mes(mm, ano);

	return dd;
}


int dia_ano(int dia, int mes, int ano) {
	return dias_ate() + dia;
}

void nome(int mes) {
    switch (mes) {
		case 1:
		  printf("Janeiro");
		  break;
		case 2:
		  printf("Fevereiro");
		  break;
		case 3:
		  printf("Marco");
		  break;
		case 4:
		  printf("Abril");
		  break;
		case 5:
		  printf("Maio");
		  break;
		case 6:
		  printf("Junho");
		  break;
		case 7:
		  printf("Julho");
		  break;
		case 8:
		  printf("Agosto");
		  break;
		case 9:
		  printf("Setembro");
		  break;
		case 10:
		  printf("Outubro");
		  break;
		case 11:
		  printf("Novembro");
		  break;
		case 12:
		  printf("Dezembro");
		  break;
      	default:
      		break;
    }
}


int main() {
	dia = 25; mes = 5; ano = 2013;
	printf("O dia %d de ", dia);
	nome(mes);
  	printf(" de %d e' o %d -esimo dia do ano.\n", ano, dia_ano(dia, mes, ano));
}