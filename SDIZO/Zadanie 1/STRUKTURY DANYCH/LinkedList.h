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
	int numberOfElements; // liczba element�w 
	void startCounter();
	void getCounter();
	void show(); // wy�wietl
	void randomFillLinkedList(int amountOfElements);
	bool search(int searchedNumber);
	void fillElementsTakenFromFile(string fileName, int Elements);
	void addAtTheBeginning(int newKey);
	void addAtTheEnd(int newKey);
	void addNewElementAfterSpecifiedKey(int keyOfElement, int newKey); // previousKey == klucz elementu za ktorym wstawiamy now� liczb�
	void removeFromBeginning(); // usu� z poczatku
	void removeFromEnd(); // usu� z ko�ca
	void remove(int keyToRemove);


};

