#include "stdafx.h"
#include "LinkedList.h"
#include <windows.h>

LinkedList::LinkedList()
{
	tail = NULL;
	head = NULL;
	numberOfElements = 0;
}


LinkedList::~LinkedList()
{
	Node *element;

	while (head)
	{
		element = head->nextElement;
		delete head; // !!!
		head = element;
	}
	numberOfElements = 0;
}

void LinkedList::startCounter()
{
	LARGE_INTEGER li;
	if (!QueryPerformanceFrequency(&li))
		cout << "QueryPerformanceFrequency failed!\n";

	PCFreq = double(li.QuadPart) / 1000.0;

	QueryPerformanceCounter(&li);
	counterStart = li.QuadPart;
}

void LinkedList::getCounter()
{
	LARGE_INTEGER li;
	QueryPerformanceCounter(&li);
	cout << "Operacja zajela: " << (li.QuadPart - counterStart) / PCFreq << " milisekund\n" << endl;
}
void LinkedList::show()
{
	Node * element = new Node();
	int i = 0;
	if (!head) cout << "Lista nie zawiera elementow.\n";
	else
	{
		element = head;
		while (element)
		{
			cout << i << ": " << element->key << endl;
			element = element->nextElement;
			i++;
		}
		cout << endl;
	}
	delete element; // !!! 
}

void LinkedList::addAtTheBeginning(int newKey)
{
	counterStart = 0; // NASTAWIAM LICZNIK
	startCounter(); // CZAS START 
	Node * newElement = new Node();
	newElement->nextElement = head;
	newElement->previousElement = NULL;

	if (head) { head->previousElement = newElement; }
	head = newElement;//jeœli g³owa nie istnieje to nowy element staje siê g³ow¹
	if (!tail) { tail = head; }
	newElement->key = newKey;
	numberOfElements++;
	getCounter(); // CZAS STOP
}


void LinkedList::addAtTheEnd(int newKey)
{
	counterStart = 0; // NASTAWIAM LICZNIK
	startCounter(); // CZAS START 
	Node * newElement = new Node(); // tworzymy nowy element
	newElement->previousElement = tail;
	newElement->nextElement = NULL; // bêdzie wyznacza³ koniec listy
	if (tail) tail->nextElement = newElement;
	tail = newElement;
	if (!head) head = tail;
	newElement->key = newKey;
	numberOfElements++;
	getCounter(); // CZAS STOP
}


void LinkedList::addNewElementAfterSpecifiedKey(int keyOfElement, int newKey)
{
	counterStart = 0; // NASTAWIAM LICZNIK
	startCounter(); // CZAS START 
	Node *element;
	Node * newElement = new Node();
	if (!(search(keyOfElement)))
	{
		addAtTheEnd(newKey);
		cout << "Brak elementu o podanej wartosci - wstawiono na poczatek listy\n";
	}
	else
	{
		element = head;
		for (int i = 1; i <= numberOfElements; i++)
		{
			if (element->key == keyOfElement)
			{
				newElement->nextElement = element->nextElement;
				newElement->previousElement = element;
				element->nextElement = newElement;
				if (newElement->nextElement) newElement->nextElement->previousElement = newElement;
				else tail = newElement;
				newElement->key = newKey;
				numberOfElements++;
				break;
			}
			else element = element->nextElement;
		}

	}
	getCounter(); // CZAS STOP
}

void LinkedList::removeFromBeginning()
{
	if (numberOfElements == 0)
	{
		cout << "Lista jest pusta\n";
	}
	else
	{
		counterStart = 0; // NASTAWIAM LICZNIK
		startCounter(); // CZAS START 
		head = head->nextElement;   // nowy pocz¹tek
		numberOfElements--;
		getCounter(); // CZAS STOP
		cout << "Usuwanie powiodlo sie.";
	}
}

void LinkedList::removeFromEnd()
{
	if (numberOfElements == 0) { cout << "Lista jest pusta\n"; }
	else if (numberOfElements == 1)
	{
		removeFromBeginning();
	}
	else
	{
		counterStart = 0; // NASTAWIAM LICZNIK
		startCounter(); // CZAS START 

		tail->previousElement->nextElement = NULL;
		tail = tail->previousElement;
		numberOfElements--;
		getCounter(); // CZAS STOP
		cout << "Usuwanie powiodlo sie.";
	}
}

void LinkedList::remove(int keyToRemove)
{
	Node *element;
	element = head;
	if (numberOfElements == 0) cout << "Lista jest pusta, nie mozna nic usunac\n";
	else
	{
		counterStart = 0; // NASTAWIAM LICZNIK
		startCounter(); // CZAS START 

		while ((element->nextElement != NULL) && (element->key != keyToRemove)) { element = element->nextElement; }
		if (element->key == keyToRemove)
		{
			if (element->nextElement) element->nextElement->previousElement = element->previousElement;
			else tail = element->previousElement;
			if (element->previousElement) { element->previousElement->nextElement = element->nextElement; }
			else head = element->nextElement;
			numberOfElements--;
			getCounter(); // CZAS STOP
		}
		else cout << "Na liscie nie ma elementu o podanym kluczu! \n";
	}
}

void LinkedList::randomFillLinkedList(int amountOfElements)
{
	{
		for (int i = 1; i <= amountOfElements; i++)
			addAtTheBeginning((rand() % 2000) - 1000);
	}
}

bool LinkedList::search(int searchedNumber)
{
	counterStart = 0; // NASTAWIAM LICZNIK
	startCounter(); // CZAS START 
	bool be = false;
	Node *element;
	element = head;
	for (int i = 1; i <= numberOfElements; i++)
	{
		if (element->key == searchedNumber) { be = true; break; }
		else element = element->nextElement;
	}
	return be;
	getCounter(); // CZAS STOP
}


void LinkedList::fillElementsTakenFromFile(string fileName, int Elements)
{
	string s;
	int i = 1;
	ifstream plik(fileName);
	if (!plik)
		cout << "Nie mozna otworzyc pliku\n";
	else
	{
		getline(plik, s);
		int n = atoi(s.c_str());

		while (!plik.eof())
		{
			if (i <= n)

			{
				getline(plik, s);
				int key = atoi(s.c_str());
				addAtTheEnd(key);
				i++;
			}
			else break;
		}
		plik.close();
		for (i; i <= n; i++)
		{
			addAtTheEnd(0);
		}
	}
}


