#pragma once
#include "stdafx.h"
/// Struktury danych i z³o¿onoœæ obliczeniowa (INEK006) – semestr 4
/// PROJEKT
/// AUTOR: PAWE£ SZYNAL
/// NR ALBUMU: 226026
/// DATA: 19.03.2017
class Heap
{
private:
	int *heap;
	int size;  //rozmiar
	int numberOfLvl = 0; // liczba poziomów
public:
	Heap();
	Heap(int size);
	~Heap();

	void show(std::string sp, std::string sn, int v); // wyswietlanie
	void Repair(); // uporz¹dkowanie 
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

lewy syn istnieje, jeœli 2k + 1 < n

prawy syn istnieje, jeœli 2k + 2 < n

wêze³ k jest liœciem, jeœli 2k + 2 > n
*/