#include "stdafx.h"
#include <windows.h> 
#include "RedBlackTree.h"

/// Struktury danych i z³o¿onoœæ obliczeniowa (INEK006) – semestr 4
/// PROJEKT
/// AUTOR: PAWE£ SZYNAL
/// NR ALBUMU: 226026
/// DATA: 19.03.2017
using namespace std;
/*
			Drzewo Czerwono-Czarne

	 - Ka¿dy wêze³ drzewa jest albo czerwony, albo czarny.
     - Ka¿dy liœæ drzewa (wêze³ pusty nil) jest zawsze czarny.
     - Korzeñ drzewa jest zawsze czarny.
     - Jeœli wêze³ jest czerwony, to obaj jego synowie s¹ czarni – innymi s³owy, w drzewie nie mog¹ wystêpowaæ dwa kolejne czerwone wêz³y, ojciec i syn.
     - Ka¿da prosta œcie¿ka od danego wêz³a do dowolnego z jego liœci potomnych zawiera tê sam¹ liczbê wêz³ów czarnych.

	 */
RedBlackTree::RedBlackTree() // KONSTRUKTOR
{

	/*
	LEGENDA

	196 -

	218 |

	192 |_

	179 |

	Ÿród³o ->	http://eduinf.waw.pl/inf/alg/001_search/0113.php
	*/
	stringR = stringL = stringP = "  ";
	stringR[0] = 218; stringR[1] = 196;
	stringL[0] = 192; stringL[1] = 196;
	stringP[0] = 179;

	guardNode.color = 'B';          // Inicjujemy stra¿nika
	guardNode.parent = &guardNode;
	guardNode.leftSon = &guardNode;
	guardNode.rightSon = &guardNode;
	root = &guardNode;           // Korzeñ wskazuje stra¿nika
}

RedBlackTree::~RedBlackTree()
{
	RecursiveRemoval(root);
}


/*

1. Tworzymy nowy wêze³
2. Liœæmi staje siê wêze³ stra¿nik
3. Sprawdzamy, czy drzewo jest puste ...--> jeœli tak, to nowy wêze³ staje siê jego korzeniem
4. Przechodzimy do sprawdzania warunków drzewa czerwono-czarnego
5. Porównujemy klucze
6. Jeœli prawy syn nie istnieje (jest nim stra¿nik), to nowy wêze³ staje siê prawym synem
7. Sprawdzanie warunków drzewa czerwono-czarnego.
8. Przechodzimy do prawego syna // konynuacja pêtli
9. To samo dla lewego syna
10.Wêze³ kolorujemy na czerwono
11.Sprawdzamy kolejne przypadki
12.Skok do przypadków lustrzanych
13.Y wskazuje wujka wêz³a X
14. Sprawdzamy przypadek nr 1 -> wujek jest czerwony
15. Ojca kolorujemy na czarno
16. Wujka kolorujemy na czarno
17. Dziadka kolorujemy na czerwono
18. Nowe X przyjmujemy dziadka
19. Kontynuujemy pêtlê
20. Sprawdzamy przypadek 2 -> wujek czarny, X prawy syn
21. X staje siê swoim ojcem
22. Wykonujemy rotacjê w lewo, X przechodzi do lewego syna
23. Przypadek 3 -> wujek czarny, X lewy syn
24. Zieniamy kolory ojca i dziadka
25. Wykonujemy rotacjê w prawo wzglêdem dziadka
26. Wychodzimy z pêtli
27. Pprzypadki lustrzane
28. Kolorujemy korzeñ na czarno
*/

void RedBlackTree::RecursiveRemoval(RedBlackNode *node)
{
	if (node != &guardNode)  // kiedy nie jest ostatni!
	{
		RecursiveRemoval(node->leftSon);   // usuwanie lewego poddrzewa
		RecursiveRemoval(node->rightSon);  // usuwanie prawego poddrzewa
		delete node; // usuwamy wêze³ 
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

	if (node != &guardNode)    // wê¿e³ nie moze byæ ostatni
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
	node = root;   //referencja do zmiennej, która przechowuje wskazanie korzenia drzewa czerwono-czarnego od niej zaczyna siê sprawdzanie 
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
	if (B != &guardNode) // (jeœli ma synów)
	{
		p = A->parent;
		A->rightSon = B->leftSon;
		if (A->rightSon != &guardNode) { A->rightSon->parent = A; }

		B->leftSon = A;
		B->parent = p;
		A->parent = B;

		if (p != &guardNode)
		{    //je¿eli A mia³o rodzica 
			if (p->leftSon == A) { p->leftSon = B; }
			else { p->rightSon = B; } 	// B staje siê prawym synem p
		}


	}
	else root = B;  //jeœli A by³o korzeniem to teraz korzeniem jest B
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
	if (B != &guardNode) {                   //jeœli ma synów
		p = A->parent;
		A->leftSon = B->rightSon;
		if (A->leftSon != &guardNode) { A->leftSon->parent = A; }

		B->rightSon = A;
		B->parent = p;   //  by³y rodzic A
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
	if (X->parent == &guardNode) root = X;   //jeœli nie ma korzenia X staje siê korzeniem
	else
		while (true) {                                                    // Szukamy liœcia do zast¹pienia przez X
			if (wartosc < X->parent->value)
			{
				if (X->parent->leftSon == &guardNode) { X->parent->leftSon = X;  break; }
				X->parent = X->parent->leftSon;                          //rodzicem X staje siê lewy syn
			}
			else {                                                     //jeœli wartoœæ X jest wiêksza lub równa wartosci jego rodzica
				if (X->parent->rightSon == &guardNode) { X->parent->rightSon = X; break; }
				X->parent = X->parent->rightSon;                        //rodzicem staje siê prawy syn
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
			//							III je¿eli X jest lewym synem to obrót w prawo wzglêdem dziadka
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
	//je¿eli X nie ma któregoœ z synów
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
				if (V->color == 'R')   //jeœli V jest czerwony
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
		while (wezel->leftSon != &guardNode) wezel = wezel->leftSon;    //znajdŸ ostatnieego lewego syna
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


