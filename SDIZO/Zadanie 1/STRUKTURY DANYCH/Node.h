#pragma once
#include "stdafx.h"
/// Struktury danych i z≥oøonoúÊ obliczeniowa (INEK006) ñ semestr 4
/// PROJEKT
/// AUTOR: PAWE£ SZYNAL
/// NR ALBUMU: 226026
/// DATA: 19.03.2017

/*
Przemyúlenia :
Nie da sie stowrzyÊ Listy przy uøyciu jednej klasy
poniewaø  kazdy element listy musi posiadaÊ wskaznik na kolejnyt element " next "
oraz poprzedni " previous "

Tworzπc w jednej klasie przechowa≥bym inty ktore nie maja wskaznikow w sobie ....
*/
// ELEMNTY LIST
class Node
{
		friend class LinkedList; // DEKLARACJA PRZYJAèNI
public:

	Node();
	~Node();
	Node *nextElement, *previousElement;
	int key;
};



