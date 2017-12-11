#pragma once
#include "stdafx.h"

/// Struktury danych i z�o�ono�� obliczeniowa (INEK006) � semestr 4
/// PROJEKT
/// AUTOR: PAWE� SZYNAL
/// NR ALBUMU: 226026
/// DATA: 19.03.2017
class Array
{
public:
	
	//pola
	

	//funkcje
	Array();
	~Array();
	
	//									STOPWATCH
	double PCFreq;
	__int64 counterStart;

	void startCounter();
	void getCounter();

	void show(); // wy�wietl
	void relocate(); // relokuj
	void addAtTheBeginning(int  valueOfTheNewElement); // dodaj na pocz�tek
	void addAtTheEnd(int valueOfTheNewElement);
	void addAtTheEnd(int  indexOfNewElement, int valueOfTheNewElement); // dodaj na koniec
	void remove(int indexElementToRemove); // usuwanie element�w 
	void removeAll(int numberOfElements);
	void removeFromBeginning(); // usu� z poczatku
	void removeFromEnd(); // usu� z ko�ca
	void search(int searchedNumber);
	void fillElementsTakenFromFile(string fileName, int Elements);
	void fillRandomNumbers(int NumberOfElements);

private:
	int *pointer; // wska�nik
	int numberOfElements; // liczba element�w 
};

