#include "stdafx.h"
#include "Heap.h"

/// Struktury danych i z�o�ono�� obliczeniowa (INEK006) � semestr 4
/// PROJEKT
/// AUTOR: PAWE� SZYNAL
/// NR ALBUMU: 226026
/// DATA: 19.03.2017

/*
	KOPIEC jest drzewem kompletnym dlatego wykorzystuje tablic�:
*/

Heap::Heap() // KONSTRUKTOR
{
	size = 0;
	heap = new int[size];
}

Heap::Heap(int numberOfElements)
{
	srand(time(NULL));
	size = numberOfElements;
	heap = new int[size];

	while (numberOfElements > 0)
	{
		numberOfElements = numberOfElements - pow(2, numberOfLvl); //Returns base raised to the power exponent:  
		numberOfLvl++;
	}

	for (int i = 0; i < size; i++)
	{
		int element = ((rand() % 2000) - 1000); // wype�nianie liczbami pseudolosowymi  
		heap[i] = element;
	}
	Repair();
}

Heap::~Heap()
{
	delete[] heap;
}
/*
				 NAPRAWA/ PORZ�DKOWANIE TABLICY
*/
void Heap::Repair()
{
	for (int i = 0; i < size; i++)
	{
		int element = heap[i];
		for (int c = 0; c < (numberOfLvl - 1); c++)
		{
			int j = ceil((i - 1) / 2); //  zaokr�glanie w g�r� TZW sufit
			while (i > 0 && heap[j] < element)
			{
				heap[i] = heap[j];
				heap[j] = element;
				i = j;
				j = ceil((i - 1) / 2); //  zaokr�glanie w g�r� TZW sufit
			}
			heap[i] = element;
		}
	}
}


void Heap::addNewElement(int a, int valueOfElement)
{
	/*
			Dodawany element wstawiamy jako ostatni li�� kopca. 
		Nast�pnie sprawdzamy kolejno, czy jest on mniejszy lub r�wny swojemu rodzicowi.
		Je�li nie, to zamieniamy wstawiony element z jego rodzicem.
		Operacj� kontynuujemy, a� znajdziemy rodzica wi�kszego lub r�wnego elementowi lub dojdziemy do korzenia drzewa
	*/
	size++;
	int *pointer = new int[size]; // zmienna pomocnicza 

	for (int i = 0; i < size - 1; i++) { pointer[i] = heap[i]; }
	pointer[size - 1] = valueOfElement;
	delete[] heap;
	heap = new int[size];

	for (int i = 0; i < size; i++) { heap[i] = pointer[i]; }
	level();
	Repair();
	delete[] pointer;
}


void Heap::remove(int a)
{
	/*
	Usuwamy korze� drzewa, wstawiaj�c na jego miejsce ostatni z li�ci.
	Nast�pnie idziemy kolejnymi poziomami w d� drzewa, sprawdzaj�c warunek kopca.
	Je�li nie jest spe�niony, to zamieniamy ojca z najwi�kszym z syn�w.
	Operacj� kontynuujemy a� do momentu spe�nienia warunku kopca lub osi�gni�cia li�cia.

						z�o�ono�� obliczeniowa  -> O(log n)
	*/
	if (size != 0) 
	{
		size--;
		int *newHeap = new int[size]; // newHeap  jest wymczasoy tworzony jako zmienna pomocniczna 

		for (int i = 0; i < size; i++) { newHeap[i] = heap[i + 1]; }
		delete[] heap;
		heap = new int[size];
		for (int i = 0; i < size; i++) { heap[i] = newHeap[i]; }
		level();
		Repair();
		delete[] newHeap;
	}
	else return;
}

void Heap::level() {
	numberOfLvl = 0;

	int quantity = size;

	while (quantity > 0)
	{
		quantity = quantity - pow(2, numberOfLvl); // numberOfLvl jest tutaj pot�g� za� 2 jest podstaw� 	
		numberOfLvl++;
	}
}

void Heap::show(std::string sp, std::string sn, int valueOfElement) {
	std::string stringR, stringL, stringP; // �a�cuchy do znak�w ramek
	std::string s;
	stringR = stringL = stringP = "  ";
	/*
	LEGENDA

	196 -

	218 |

	192 |_

	179 |

	�r�d�o ->	http://eduinf.waw.pl/inf/alg/001_search/0113.php
	*/
	stringR[0] = 218; stringR[1] = 196;
	stringL[0] = 192; stringL[1] = 196;
	stringP[0] = 179;

	if (valueOfElement < size)
	{
		s = sp;
		if (sn == stringR) { s[s.length() - 2] = ' '; }

		show(s + stringP, stringR, 2 * valueOfElement + 2);
		s = s.substr(0, sp.length() - 2);
		// Zwraca niedawno skonstruowany obiekt �a�cuchowy z jego warto�ci� inicjalizowan� do egzemplarza pod�a�cuchu tego obiektu.
		std::cout << s << sn << heap[valueOfElement] << std::endl;

		s = sp;
		if (sn == stringL) { s[s.length() - 2] = ' '; }

		show(s + stringP, stringL, 2 * valueOfElement + 1);
	}
}
void Heap::fillElementsTakenFromFile(std::string nazwapliku)
{
	std::ifstream file;
	std::string element;
	file.open(nazwapliku);
	file >> element;
	size = atoi(element.c_str());
	heap = new int[size];

	for (int i = 0; i < size; i++)
	{
		file >> element;
		heap[i] = atoi(element.c_str());
	}

	file.close();
	level();
	Repair();
}



bool Heap::search(int searchedElement)
{
	if (heap[0] == searchedElement) { return true; }

	for (int i = 0; i < size; i++) //dla kazdego elementu
	{
		// lewy syn
		if ((2 * i + 1) < size) //je�li lewy syn istnieje
		{
			if (heap[2 * i + 1] == searchedElement) { return true; }

			// prawy syn
			else if ((2 * i + 2) < size)  //jesli prawy syn istnieje
			{
				if (heap[2 * i + 2] == searchedElement) { return true; }
			}

		}
	}
	return false;
}

