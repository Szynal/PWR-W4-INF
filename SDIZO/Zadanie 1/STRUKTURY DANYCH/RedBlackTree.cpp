#include "stdafx.h"
#include <windows.h> 
#include "RedBlackTree.h"

/// Struktury danych i z�o�ono�� obliczeniowa (INEK006) � semestr 4
/// PROJEKT
/// AUTOR: PAWE� SZYNAL
/// NR ALBUMU: 226026
/// DATA: 19.03.2017
using namespace std;
/*
			Drzewo Czerwono-Czarne

	 - Ka�dy w�ze� drzewa jest albo czerwony, albo czarny.
     - Ka�dy li�� drzewa (w�ze� pusty nil) jest zawsze czarny.
     - Korze� drzewa jest zawsze czarny.
     - Je�li w�ze� jest czerwony, to obaj jego synowie s� czarni � innymi s�owy, w drzewie nie mog� wyst�powa� dwa kolejne czerwone w�z�y, ojciec i syn.
     - Ka�da prosta �cie�ka od danego w�z�a do dowolnego z jego li�ci potomnych zawiera t� sam� liczb� w�z��w czarnych.

	 */
RedBlackTree::RedBlackTree() // KONSTRUKTOR
{

	/*
	LEGENDA

	196 -

	218 |

	192 |_

	179 |

	�r�d�o ->	http://eduinf.waw.pl/inf/alg/001_search/0113.php
	*/
	stringR = stringL = stringP = "  ";
	stringR[0] = 218; stringR[1] = 196;
	stringL[0] = 192; stringL[1] = 196;
	stringP[0] = 179;

	guardNode.color = 'B';          // Inicjujemy stra�nika
	guardNode.parent = &guardNode;
	guardNode.leftSon = &guardNode;
	guardNode.rightSon = &guardNode;
	root = &guardNode;           // Korze� wskazuje stra�nika
}

RedBlackTree::~RedBlackTree()
{
	RecursiveRemoval(root);
}


/*

1. Tworzymy nowy w�ze�
2. Li��mi staje si� w�ze� stra�nik
3. Sprawdzamy, czy drzewo jest puste ...--> je�li tak, to nowy w�ze� staje si� jego korzeniem
4. Przechodzimy do sprawdzania warunk�w drzewa czerwono-czarnego
5. Por�wnujemy klucze
6. Je�li prawy syn nie istnieje (jest nim stra�nik), to nowy w�ze� staje si� prawym synem
7. Sprawdzanie warunk�w drzewa czerwono-czarnego.
8. Przechodzimy do prawego syna // konynuacja p�tli
9. To samo dla lewego syna
10.W�ze� kolorujemy na czerwono
11.Sprawdzamy kolejne przypadki
12.Skok do przypadk�w lustrzanych
13.Y wskazuje wujka w�z�a X
14. Sprawdzamy przypadek nr 1 -> wujek jest czerwony
15. Ojca kolorujemy na czarno
16. Wujka kolorujemy na czarno
17. Dziadka kolorujemy na czerwono
18. Nowe X przyjmujemy dziadka
19. Kontynuujemy p�tl�
20. Sprawdzamy przypadek 2 -> wujek czarny, X prawy syn
21. X staje si� swoim ojcem
22. Wykonujemy rotacj� w lewo, X przechodzi do lewego syna
23. Przypadek 3 -> wujek czarny, X lewy syn
24. Zieniamy kolory ojca i dziadka
25. Wykonujemy rotacj� w prawo wzgl�dem dziadka
26. Wychodzimy z p�tli
27. Pprzypadki lustrzane
28. Kolorujemy korze� na czarno
*/

void RedBlackTree::RecursiveRemoval(RedBlackNode *node)
{
	if (node != &guardNode)  // kiedy nie jest ostatni!
	{
		RecursiveRemoval(node->leftSon);   // usuwanie lewego poddrzewa
		RecursiveRemoval(node->rightSon);  // usuwanie prawego poddrzewa
		delete node; // usuwamy w�ze� 
	}
}

void RedBlackTree::RandomFillRedBlack(int roz)
{
	srand(time(NULL));
	for (int i = 0; i < roz; i++)
	{
		this->InsertNode(0, (rand() % 2000) - 1000);   //dodaj wezel o losowej wartosci
	}
}

void RedBlackTree::FillElementsTakenFromFile(string fileName)
{
	string s;
	int i = 0;
	ifstream file(fileName);
	if (!file)
		cout << "Nie mozna otworzyc pliku" << endl;
	else {
		getline(file, s);
		int size = atoi(s.c_str());
		while (!file.eof()) {
			if (i < size) {
				getline(file, s);
				int wartosc = atoi(s.c_str());
				InsertNode(0, wartosc);
				i++;
			}
			else break;
		}
		file.close();
	}
}

void RedBlackTree::Show() { ShowRedBlackTree("", "", root); }

void RedBlackTree::ShowRedBlackTree(string sp, string sn, RedBlackNode *node)
{
	string s;

	if (node != &guardNode)    // w�e� nie moze by� ostatni
	{
		s = sp;
		if (sn == stringR) s[s.length() - 2] = ' ';
		ShowRedBlackTree(s + stringP, stringR, node->rightSon);

		s = s.substr(0, sp.length() - 2);
		std::cout << s << sn << node->color << ":" << node->value << endl;

		s = sp;
		if (sn == stringL) s[s.length() - 2] = ' ';
		ShowRedBlackTree(s + stringP, stringL, node->leftSon);
	}
}

RedBlackNode *RedBlackTree::Search(int value)
{
	RedBlackNode * node;
	node = root;   //referencja do zmiennej, kt�ra przechowuje wskazanie korzenia drzewa czerwono-czarnego od niej zaczyna si� sprawdzanie 
	while ((node != &guardNode) && (node->value != value))
		if (value < node->value) // < wartosc wezla 
		{
			node = node->leftSon;
		}
		else { node = node->rightSon; }    // <=
		if (node == &guardNode) { return NULL; }
		return node;
}

/*							ROTACJA W LEWO 
						  A              B
					     / \            / \
					    AL  B          A   BR
				           / \    ->  / \            
						  BL  BR     AL BL
*/

void RedBlackTree::RotationLeft(RedBlackNode *A)
{
	RedBlackNode * B, *p;
	B = A->rightSon; //B jest (R) synem A
	if (B != &guardNode) // (je�li ma syn�w)
	{
		p = A->parent;
		A->rightSon = B->leftSon;
		if (A->rightSon != &guardNode) { A->rightSon->parent = A; }

		B->leftSon = A;
		B->parent = p;
		A->parent = B;

		if (p != &guardNode)
		{    //je�eli A mia�o rodzica 
			if (p->leftSon == A) { p->leftSon = B; }
			else { p->rightSon = B; } 	// B staje si� prawym synem p
		}


	}
	else root = B;  //je�li A by�o korzeniem to teraz korzeniem jest B
}

///		          A					 B
///	             / \		    	/ \
///		        B   AR		       BL  A                                                                      
///		      / \				      / \      
///		     BL  BR		     	     BR AR

RedBlackNode * B, *p;
void RedBlackTree::RotationToRight(RedBlackNode *A)
{
	B = A->leftSon;
	if (B != &guardNode) {                   //je�li ma syn�w
		p = A->parent;
		A->leftSon = B->rightSon;
		if (A->leftSon != &guardNode) { A->leftSon->parent = A; }

		B->rightSon = A;
		B->parent = p;   //  by�y rodzic A
		A->parent = B;

		if (p != &guardNode)
		{
			if (p->leftSon == A)
			{
				p->leftSon = B;
			}
			else {
				p->rightSon = B;
			}
		}
		else root = B;
	}
}



void RedBlackTree::InsertNode(int a, int wartosc)
{
	RedBlackNode * X, *Y;
	X = new RedBlackNode;
	// INICJALIZACJE
	X->leftSon = &guardNode;
	X->rightSon = &guardNode;
	X->parent = root;
	X->value = wartosc;
	if (X->parent == &guardNode) root = X;   //je�li nie ma korzenia X staje si� korzeniem
	else
		while (true) {                                                    // Szukamy li�cia do zast�pienia przez X
			if (wartosc < X->parent->value)
			{
				if (X->parent->leftSon == &guardNode) { X->parent->leftSon = X;  break; }
				X->parent = X->parent->leftSon;                          //rodzicem X staje si� lewy syn
			}
			else {                                                     //je�li warto�� X jest wi�ksza lub r�wna wartosci jego rodzica
				if (X->parent->rightSon == &guardNode) { X->parent->rightSon = X; break; }
				X->parent = X->parent->rightSon;                        //rodzicem staje si� prawy syn
			}
		}
	X->color = 'R';
	while ((X != root) && (X->parent->color == 'R'))
	{
		//							 I        
		if (X->parent == X->parent->parent->leftSon)
		{
			Y = X->parent->parent->rightSon; // wujek X

			if (Y->color == 'R')
			{
				X->parent->color = 'B';   //Ojciec i WUJO  na CZARNO
				Y->color = 'B';
				X->parent->parent->color = 'R'; // DZIADEK
				X = X->parent->parent;
				continue;
			}
			//							 II 
			if (X == X->parent->rightSon)
			{
				X = X->parent;
				RotationLeft(X);
			}
			//							III je�eli X jest lewym synem to obr�t w prawo wzgl�dem dziadka
			X->parent->color = 'B';
			X->parent->parent->color = 'R';
			RotationToRight(X->parent->parent);
			break;
		}
		// Przypadki lustrzane
		else
		{
			Y = X->parent->parent->leftSon;

			if (Y->color == 'R')
			{
				X->parent->color = 'B';
				Y->color = 'B';
				X->parent->parent->color = 'R';
				X = X->parent->parent;
				continue;
			}

			if (X == X->parent->leftSon)
			{
				X = X->parent;
				RotationToRight(X);
			}

			X->parent->color = 'B';
			X->parent->parent->color = 'R';
			RotationLeft(X->parent->parent);
			break;
		}
	}
	root->color = 'B';
}


void RedBlackTree::Remove(RedBlackNode *X)
{
	RedBlackNode * V, *Y, *Z;
	//je�eli X nie ma kt�rego� z syn�w
	if ((X->leftSon == &guardNode) || (X->rightSon == &guardNode)) { Y = X; }
	else { Y = FindFollower(X); }
	if (Y->leftSon != &guardNode) { Z = Y->leftSon; }
	else { Z = Y->rightSon; }
	Z->parent = Y->parent;
	if (Y->parent == &guardNode) { root = Z; }
	else if (Y == Y->parent->leftSon) { Y->parent->leftSon = Z; }
	else { Y->parent->rightSon = Z; }
	if (Y != X) { X->value = Y->value; }
	//						NAPRAWA 
	if (Y->color == 'B')
		while ((Z != root) && (Z->color == 'B'))
			if (Z == Z->parent->leftSon) {
				V = Z->parent->rightSon;  //V jest bratem (R) Z 
				if (V->color == 'R')   //je�li V jest czerwony
				{
					V->color = 'B';
					Z->parent->color = 'R';
					RotationLeft(Z->parent);
					V = Z->parent->rightSon;
				}
				//										 II
				if ((V->leftSon->color == 'B') && (V->rightSon->color == 'B'))
				{
					V->color = 'R';
					Z = Z->parent;
					continue;
				}
				if (V->rightSon->color == 'B')
				{
					V->leftSon->color = 'B';
					V->color = 'R';
					RotationToRight(V);
					V = Z->parent->rightSon;
				}

				V->color = Z->parent->color;
				Z->parent->color = 'B';
				V->rightSon->color = 'B';
				RotationLeft(Z->parent);
				Z = root;// END OF LOOP   
			}
			else
			{
				V = Z->parent->leftSon;

				if (V->color == 'R')
				{
					V->color = 'B';
					Z->parent->color = 'R';
					RotationToRight(Z->parent);
					V = Z->parent->leftSon;
				}

				if ((V->leftSon->color == 'B') && (V->rightSon->color == 'B'))
				{
					V->color = 'R';
					Z = Z->parent;
					continue;
				}

				if (V->leftSon->color == 'B')
				{
					V->rightSon->color = 'B';
					V->color = 'R';
					RotationLeft(V);
					V = Z->parent->leftSon;
				}

				V->color = Z->parent->color;
				Z->parent->color = 'B';
				V->leftSon->color = 'B';
				RotationToRight(Z->parent);
				Z = root;// END OF LOOP                                                        
			}
	Z->color = 'B';

	delete Y;
}

RedBlackNode *RedBlackTree::FindSmallestNode(RedBlackNode *wezel) 
{
	if (wezel != &guardNode)
		while (wezel->leftSon != &guardNode) wezel = wezel->leftSon;    //znajd� ostatnieego lewego syna
	return wezel;
}

RedBlackNode *RedBlackTree::FindFollower(RedBlackNode *wezel)
{
	RedBlackNode * r;
	if (wezel != &guardNode)
	{
		if (wezel->rightSon != &guardNode) {
			return FindSmallestNode(wezel->rightSon);
		}
		else {
			r = wezel->parent;
			while ((r != &guardNode) && (wezel == r->rightSon)) 
			{
				wezel = r;
				r = r->parent;
			}
			return r;
		}
	}
	return &guardNode;
}


