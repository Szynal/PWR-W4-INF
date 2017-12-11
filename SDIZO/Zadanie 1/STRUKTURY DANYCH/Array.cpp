#include "stdafx.h"
#include "Array.h"
#include <windows.h>
/// Struktury danych i z³o¿onoœæ obliczeniowa (INEK006) – semestr 4
/// PROJEKT
/// AUTOR: PAWE£ SZYNAL
/// NR ALBUMU: 226026
/// DATA: 19.03.2017


Array::Array()
{
	numberOfElements = 0;
	pointer = new int[numberOfElements];
}

Array::~Array()
{
	delete[]pointer;
	numberOfElements = 0;
}

void Array::fillRandomNumbers(int NumberOfElements)// zape³nij losowymi liczbami 
{
	numberOfElements = NumberOfElements;
	pointer = new int[2 * numberOfElements];
	for (int i = 0; i < numberOfElements; i++)
	{
		pointer[i] = ((rand() % 2000) - 1000);
	}
}

void Array::startCounter()
{
	LARGE_INTEGER li;
	if (!QueryPerformanceFrequency(&li))
		cout << "QueryPerformanceFrequency failed!\n";

	PCFreq = double(li.QuadPart) / 1000.0;

	QueryPerformanceCounter(&li);
	counterStart = li.QuadPart;
}

void Array::getCounter()
{
	LARGE_INTEGER li;
	QueryPerformanceCounter(&li);
	cout << "Operacja zajela: " << (li.QuadPart - counterStart) / PCFreq << " milisekund\n" << endl;
}

void Array::show()// wyœwietl
{
	if (numberOfElements == 0) cout << "Tablica jest pusta" << endl;
	else
	{
		for (int i = 0; i < numberOfElements; i++) { cout << i << ": " << pointer[i] << endl; }
		cout << endl;
	}
}

void Array::relocate()
{
	int *w = new int[numberOfElements];
	for (int i = 0; i < numberOfElements; i++)
	{
		w[i] = pointer[i];
	}
	pointer = new int[2 * numberOfElements];
	for (int i = 0; i < numberOfElements; i++)
	{
		pointer[i] = w[i];
	}
	delete[]w;
}


/*
					DODAWANIE ELEMENTÓW
*/

void Array::addAtTheBeginning(int valueOfTheNewElement)
{
	counterStart = 0; // NASTAWIAM LICZNIK
	startCounter(); // CZAS START 
	numberOfElements++;

	for (int i = numberOfElements - 1; i > 0; i--)
	{
		pointer[i] = pointer[i - 1];
	}
	pointer[0] = valueOfTheNewElement;
	relocate();
	getCounter(); // CZAS STOP

}


void Array::addAtTheEnd(int valueOfTheNewElement)
{
	counterStart = 0; // NASTAWIAM LICZNIK
	startCounter(); // CZAS START 		

	int * array2 = new int[numberOfElements + 1];
	for (int i = 0; i < numberOfElements; i++)
		array2[i] = pointer[i];
	array2[numberOfElements] = valueOfTheNewElement;
	numberOfElements++;
	delete[] pointer;
	pointer = array2;
	getCounter(); // CZAS STOP
}

void Array::addAtTheEnd(int indexOfNewElement, int valueOfTheNewElement)
{

	if (indexOfNewElement >= numberOfElements)
	{
		addAtTheEnd(valueOfTheNewElement);
	}
	else
	{
		counterStart = 0; // NASTAWIAM LICZNIK
		startCounter(); // CZAS START 
		numberOfElements++;
		for (int i = numberOfElements - 1; i > indexOfNewElement; i--)
		{
			pointer[i] = pointer[i - 1];
		}
		pointer[indexOfNewElement] = valueOfTheNewElement;
		getCounter(); // CZAS STOP
		relocate();

	}
}


/*
						USUWANIE ELEMENTÓW
*/

void Array::remove(int indexElementToRemove) // usuwanie elementów 
{
	counterStart = 0; // NASTAWIAM LICZNIK
	startCounter(); // CZAS START 
	int * array = new int[numberOfElements - 1];
	int j = 0;
	for (int i = 0; i < indexElementToRemove; i++) {
		array[i] = pointer[i];
	}
	for (int i = indexElementToRemove + 1; i < numberOfElements; i++)
	{
		array[i - 1] = pointer[i];
	}
	numberOfElements--;
	delete[] pointer;
	pointer = array;
	getCounter(); // CZAS STOP
}


void Array::removeAll(int numberOfElements)
{
	counterStart = 0; // NASTAWIAM LICZNIK
	startCounter(); // CZAS START 

	// for (int i = 0; i < numberOfElements; i++) { delete *pointer[i]; }
	delete[] pointer;
	numberOfElements--;
	relocate();
	getCounter(); // CZAS STOP
}

void Array::removeFromBeginning() // usuñ z poczatku
{
	if (numberOfElements == 0) { cout << "Tablica jest pusta, nie mozna nic usunac" << endl; }
	else
	{
		counterStart = 0; // NASTAWIAM LICZNIK
		startCounter(); // CZAS START 
		numberOfElements--;
		for (int i = 0; i < numberOfElements; i++)
		{
			pointer[i] = pointer[i + 1];
		}
		relocate();
		getCounter(); // CZAS STOP
	}
}

void Array::removeFromEnd()
{
	if (numberOfElements == 0) { cout << "Tablica jest pusta, nie mozna nic usunac" << endl; }

	else
	{
		counterStart = 0; // NASTAWIAM LICZNIK
		startCounter(); // CZAS START 
		pointer[numberOfElements - 1] = NULL;
		numberOfElements--;
		relocate();
		getCounter(); // CZAS STOP
	}
}

/*

WYSZUKIWANIE ELEMENTU

*/

void Array::search(int searchedNumber)
{
	counterStart = 0; // NASTAWIAM LICZNIK
	startCounter(); // CZAS START 

	bool be = false; // czy jest ?
	for (int i = 0; i < numberOfElements; i++) { if (searchedNumber == pointer[i]) { be = true; break; } }
	getCounter(); // CZAS STOP

	if (be) cout << "Szukany element znajduje sie w tablicy" << endl;
	if (!be) cout << "Szukego elementu nie ma w tablicy" << endl;
}

void Array::fillElementsTakenFromFile(string fileName, int Elements)
{
	string s;
	int i = 0;

	std::ifstream plik(fileName);

	if (!plik)
	{
		cout << "Nie mozna otworzyc pliku" << endl;;

	}
	else
	{
		getline(plik, s);
		numberOfElements = Elements;
		while (!plik.eof())
		{
			if (i < numberOfElements)
			{
				getline(plik, s);
				pointer[i] = atoi(s.c_str());
				i++;
			}
			else break;

		}
		plik.close();
		for (i; i < numberOfElements; i++)
		{
			pointer[i] = 0;
		}
	}
}




