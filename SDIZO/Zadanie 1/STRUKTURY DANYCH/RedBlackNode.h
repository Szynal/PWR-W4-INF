#pragma once
#include "stdafx.h"
/// Struktury danych i z³o¿onoœæ obliczeniowa (INEK006) – semestr 4
/// PROJEKT
/// AUTOR: PAWE£ SZYNAL
/// NR ALBUMU: 226026
/// DATA: 19.03.2017
class RedBlackNode // WÊZE£ 
{
public:
	RedBlackNode();
	~RedBlackNode();
	int value;
	char color;
	RedBlackNode *parent; 
	RedBlackNode *leftSon;	 
	RedBlackNode *rightSon;
};

