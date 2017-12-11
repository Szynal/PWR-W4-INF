#pragma once
#include "stdafx.h"

/// Struktury danych i z³o¿onoœæ obliczeniowa (INEK006) – semestr 4
/// PROJEKT
/// AUTOR: PAWE£ SZYNAL
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

	void show(); // wyœwietl
	void relocate(); // relokuj
	void addAtTheBeginning(int  valueOfTheNewElement); // dodaj na pocz¹tek
	void addAtTheEnd(int valueOfTheNewElement);
	void addAtTheEnd(int  indexOfNewElement, int valueOfTheNewElement); // dodaj na koniec
	void remove(int indexElementToRemove); // usuwanie elementów 
	void removeAll(int numberOfElements);
	void removeFromBeginning(); // usuñ z poczatku
	void removeFromEnd(); // usuñ z koñca
	void search(int searchedNumber);
	void fillElementsTakenFromFile(string fileName, int Elements);
	void fillRandomNumbers(int NumberOfElements);

private:
	int *pointer; // wskaŸnik
	int numberOfElements; // liczba elementów 
};

