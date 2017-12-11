/// Struktury danych i z≥oøonoúÊ obliczeniowa (INEK006) ñ semestr 4
/// PROJEKT
/// AUTOR: PAWE£ SZYNAL
/// NR ALBUMU: 226026
/// DATA: 19.03.2017
#include "stdafx.h"
#include "BinaryHeap.h"


BinaryHeap::BinaryHeap(void)
{
	numberOfBinaryHeap = 0;
	numberOfBinaryHeapElement = 0;
	//WSKAèNIKI
	pointer = NULL;
	root = NULL;
}

BinaryHeap::~BinaryHeap(void)
{
	//DOMYåLNY DESTRUKTOR
}


int BinaryHeap::giveNumberOfElements() // AKCESOR GETER*
{
	return numberOfBinaryHeapElement;
}

//NOWY KOPIEC
void BinaryHeap::newBinaryHeap(int n, int min, int max, unsigned seed)
{
	pointer = new BinaryHeapElement;
	root = new BinaryHeapElement;
	pointer = root = createRoot(rand() % +(max - min) + min);
	for (int i = 1; (numberOfBinaryHeapElement + 1) <= n; i++)
	{
		addNewElement(rand() % +(max - min) + min);
	}
}

BinaryHeap::BinaryHeapElement *BinaryHeap::giveRoot() { return root; } // AKCESOR KORZENIA 

void BinaryHeap::addNewElement(BinaryHeapElement * newIndicator)
{
	/* zaczynamy od korzenia i porownojemy wartos korzenia z wartoscia nowego elementu
	Jesli wartosc jest wieksza i korzen nie posiada prawego potomka = nowy element to prawy potomek
	Jesli posiada potomka idziemy do niego i sprawdzamy ponownie az nie dojdziemy do ostatniego
	Jesli natomiast wartosc jest mniejsza to sprawdzamy czy posiada lewego potomka, jak tak to idziemy do niego
	Jesli nie posiada to lewym potomkiem jest nowy element
	*/

	pointer = new BinaryHeapElement;
	BinaryHeapElement *parent = new BinaryHeapElement;
	parent = NULL;
	pointer = root;
	while (pointer)
	{
		parent = pointer;
		if (newIndicator->value < pointer->value) { pointer = pointer->leftSidedChild; }
		else pointer = pointer->rightSidedChild;
	}
	newIndicator->parent = parent;
	if (parent == NULL) { root = newIndicator; }
	else {
		if (newIndicator->value < parent->value) {
			parent->leftSidedChild = newIndicator;
		}
		else parent->rightSidedChild = newIndicator;
	}
};

void BinaryHeap::addNewElement(int value)
{
	BinaryHeapElement *newIndicator = new BinaryHeapElement;
	newIndicator->value = value;
	newIndicator->leftSidedChild = NULL;
	newIndicator->rightSidedChild = NULL;
	addNewElement(newIndicator);
	numberOfBinaryHeapElement++;
};
BinaryHeap::BinaryHeapElement *BinaryHeap::createRoot(int value) {
	root->value = value;
	root->parent = NULL;
	root->leftSidedChild = NULL;
	root->rightSidedChild = NULL;
	numberOfBinaryHeapElement++;
	return root;
};
BinaryHeap::BinaryHeapElement *BinaryHeap::giveParentIndicator(BinaryHeapElement *pointer) { return pointer->parent; };
BinaryHeap::BinaryHeapElement *BinaryHeap::giveleftSidedChild(BinaryHeapElement *pointer) { return pointer->leftSidedChild; }
BinaryHeap::BinaryHeapElement *BinaryHeap::giveRightSidedChild(BinaryHeapElement *pointer) { return pointer->rightSidedChild; }
BinaryHeap::BinaryHeapElement *BinaryHeap::search(int value) {
	pointer = root;
	return search(pointer, value);
};
BinaryHeap::BinaryHeapElement *BinaryHeap::search(BinaryHeapElement *currentIndicator, int value) {
	if (currentIndicator == NULL || value == currentIndicator->value) { return currentIndicator; }
	if (value < currentIndicator->value)
	{
		return search(currentIndicator->leftSidedChild, value);
	}
	else return search(currentIndicator->rightSidedChild, value);
	return NULL;
};
std::string BinaryHeap::showBinaryHeapInOrder(std::string wynik, BinaryHeapElement *p) {
	std::stringstream strumien;
	if (p) {
		wynik = showBinaryHeapInOrder(wynik, p->leftSidedChild);
		strumien << p->value;
		wynik = wynik + " " + strumien.str();
		wynik = showBinaryHeapInOrder(wynik, p->rightSidedChild);
	}
	return wynik;
};
std::string BinaryHeap::showBinaryHeapElement(BinaryHeapElement *pointer) {
	std::string brak = "Nie ma takiego elementu";
	if (pointer) {
		std::stringstream strumien;
		strumien << pointer->value;
		return strumien.str();
	}
	else return brak;
};
std::string BinaryHeap::showBinaryHeapInOrder() {
	std::string wynik;
	wynik = wynik + showBinaryHeapInOrder(wynik, root);
	return wynik;
};


