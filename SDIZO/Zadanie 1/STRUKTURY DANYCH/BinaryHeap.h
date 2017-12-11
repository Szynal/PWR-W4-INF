#pragma once
/// Struktury danych i z³o¿onoœæ obliczeniowa (INEK006) – semestr 4
/// PROJEKT
/// AUTOR: PAWE£ SZYNAL
/// NR ALBUMU: 226026
/// DATA: 19.03.2017
class BinaryHeap
{
public:
	struct BinaryHeapElement // Element Drzewa / Kopca 
	{
		int value;
		BinaryHeapElement *leftSidedChild, *rightSidedChild, *parent; // potemek_lewy, prawy , ojciec
	};

	BinaryHeap(void);	// KONSTRUKTOR
	~BinaryHeap(void); //DESTRUKTOR

	BinaryHeapElement *pointer; // WSKANIK !!!
	int giveNumberOfElements(); // podaj iloœc elementów... AKCESOR DO ZMIENNEJ PRYWATNEJ

	void newBinaryHeap(int n, int min, int max, unsigned seed);
	BinaryHeapElement *giveParentIndicator(BinaryHeapElement *pointer); // Parent indicator -> podaj rodzica
	BinaryHeapElement *giveRightSidedChild(BinaryHeapElement *pointer);
	BinaryHeapElement *giveleftSidedChild(BinaryHeapElement *pointer);

	void addNewElement(int value);
	BinaryHeapElement *createRoot(int value); // tworzenie korzenia 
	BinaryHeapElement *search(int value); // wyszukiwanie elementu
	BinaryHeapElement *giveRoot(); // podawanie korzenia 

	std::string showBinaryHeapInOrder();	//wyœwietl drzewo. kopiec
	std::string showBinaryHeapElement(BinaryHeapElement *pointer); // wyœwietl element

private:
	int numberOfBinaryHeap, numberOfBinaryHeapElement;
	BinaryHeapElement *root, *theLastElement;
	BinaryHeapElement *search(BinaryHeapElement *currentIndicator, int value);
	void addNewElement(BinaryHeapElement *newIndicator); // dodaj nowy element 
	std::string showBinaryHeapInOrder(std::string result, BinaryHeapElement *pointer);

};
