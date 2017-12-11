#pragma once
#include "stdafx.h"
/// Struktury danych i z�o�ono�� obliczeniowa (INEK006) � semestr 4
/// PROJEKT
/// AUTOR: PAWE� SZYNAL
/// NR ALBUMU: 226026
/// DATA: 19.03.2017

/*
Przemy�lenia :
Nie da sie stowrzy� Listy przy u�yciu jednej klasy
poniewa�  kazdy element listy musi posiada� wskaznik na kolejnyt element " next "
oraz poprzedni " previous "

Tworz�c w jednej klasie przechowa�bym inty ktore nie maja wskaznikow w sobie ....
*/
// ELEMNTY LIST
class Node
{
		friend class LinkedList; // DEKLARACJA PRZYJA�NI
public:

	Node();
	~Node();
	Node *nextElement, *previousElement;
	int key;
};



