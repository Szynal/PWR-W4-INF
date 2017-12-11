/// Struktury danych i z³o¿onoœæ obliczeniowa (INEK006) – semestr 4
/// PROJEKT
/// AUTOR: PAWE£ SZYNAL
/// NR ALBUMU: 226026
/// DATA: 19.03.2017

#include "stdafx.h"
#include "ConsoleUserDialog.h"

/*
*  Klasa ConsoleUserDialog
*  - implementacja interfejsu UserDialog.

*  Prosta biblioteka metod do realizacji dialogu z uzytkownikiem bez graficznego interfejsu.
*/

using namespace std;
ConsoleUserDialog::ConsoleUserDialog()
{
}


ConsoleUserDialog::~ConsoleUserDialog()
{
}


void ConsoleUserDialog::printInfoMessage()
{
	cout << "			Zadanie projektowe nr 1				\n"
		<< "Badanie efektywnoci operacji dodawania, usuwania oraz wyszukiwania elementopw w roznych strukturach danych.\n\n"		<< "AUTOR: PAWE£ SZYNAL 226026\n\n ";
}

void ConsoleUserDialog::printInfoMenuMessage()
{
	cout << "				Struktury danych				 \n"
		<< " W celu wybrania odpowiedniej struktury danych nacisnij przycisk przyporzadkowany odpowiedniej strukturze\n\n"
		<< " Tablica				  -> nacisnij '1'\n"
		<< " Lista					  -> nacisnij '2'\n"
		<< " Kopiec binarny			  -> nacisnij '3'\n"
		<< " Drzewo czerwono - czarne -> nacisnij '4'\n";
}

char ConsoleUserDialog::MenuDataStructures(char input)
{
	switch (input)
	{
	case '1':
		//TABLICA
		break;
	case '2':
		// b) Lista,
		break;
	case '3':
		//c) Kopiec binarny,
		break;
	case '4':
		//c) Kopiec binarny

	default:
		cout << "ERROR";
	}
	return 0;
}

