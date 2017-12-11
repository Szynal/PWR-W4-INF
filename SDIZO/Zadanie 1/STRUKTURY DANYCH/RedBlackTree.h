#pragma once
#include "stdafx.h"
/// Struktury danych i z�o�ono�� obliczeniowa (INEK006) � semestr 4
/// PROJEKT
/// AUTOR: PAWE� SZYNAL
/// NR ALBUMU: 226026
/// DATA: 19.03.2017

class RedBlackTree
{

private:
	RedBlackNode guardNode; // w�ze� stra�nika 
	 /*
			W celu zaoszcz�dzenia pami�ci korzystam z guardNode.
			Zmienna pe�ni rol� wszystkich li�ci w drzewie
	 */
	RedBlackNode * root;
	string stringR, stringL, stringP;           // wyswietlanie
	void ShowRedBlackTree(string sp, string sn, RedBlackNode * node);      

public:
	RedBlackTree();
	~RedBlackTree();
	void Show();
	void RandomFillRedBlack(int roz);
	void FillElementsTakenFromFile(string fileName);
	void RecursiveRemoval(RedBlackNode *node);               
	void RotationLeft(RedBlackNode *A);
	void RotationToRight(RedBlackNode *A);
	void InsertNode(int a, int value);
	void Remove(RedBlackNode *X);
	RedBlackNode * Search(int value);                    
	RedBlackNode * FindSmallestNode(RedBlackNode *node);       
	RedBlackNode * FindFollower(RedBlackNode *node); 
	
};

