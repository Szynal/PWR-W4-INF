#pragma once
#include "stdafx.h"
/// Struktury danych i z�o�ono�� obliczeniowa (INEK006) � semestr 4
/// PROJEKT
/// AUTOR: PAWE� SZYNAL
/// NR ALBUMU: 226026
/// DATA: 19.03.2017
class Heap
{
private:
	int *heap;
	int size;  //rozmiar
	int numberOfLvl = 0; // liczba poziom�w
public:
	Heap();
	Heap(int size);
	~Heap();

	void show(std::string sp, std::string sn, int v); // wyswietlanie
	void Repair(); // uporz�dkowanie 
	void addNewElement(int a, int valueOfElement);
	void remove(int a);	// usuwanie
	void level();	// poziomy
	void fillElementsTakenFromFile(std::string fileName);
	bool search(int value);	// wyszukiwanie 
};

/*
numer lewego syna = 2k + 1

numer prawego syna = 2k + 2

numer ojca = [(k - 1) / 2], dla k > 0

lewy syn istnieje, je�li 2k + 1 < n

prawy syn istnieje, je�li 2k + 2 < n

w�ze� k jest li�ciem, je�li 2k + 2 > n
*/