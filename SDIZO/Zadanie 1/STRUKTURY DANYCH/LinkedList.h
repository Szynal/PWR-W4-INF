#pragma once
class LinkedList
{
private:
	Node *head, *tail;


public:
	LinkedList(void);
	~LinkedList(void);
	double PCFreq;
	__int64 counterStart;
	int numberOfElements; // liczba elementów 
	void startCounter();
	void getCounter();
	void show(); // wyœwietl
	void randomFillLinkedList(int amountOfElements);
	bool search(int searchedNumber);
	void fillElementsTakenFromFile(string fileName, int Elements);
	void addAtTheBeginning(int newKey);
	void addAtTheEnd(int newKey);
	void addNewElementAfterSpecifiedKey(int keyOfElement, int newKey); // previousKey == klucz elementu za ktorym wstawiamy now¹ liczbê
	void removeFromBeginning(); // usuñ z poczatku
	void removeFromEnd(); // usuñ z koñca
	void remove(int keyToRemove);


};

