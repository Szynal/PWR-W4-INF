#include "stdafx.h"
#include "StrukturyDanych.h"
/// Struktury danych i z³o¿onoœæ obliczeniowa (INEK006) – semestr 4
/// PROJEKT
/// AUTOR: PAWE£ SZYNAL
/// NR ALBUMU: 226026
/// DATA: 19.03.2017
using namespace std;
bool loop = true;
char input, inputK_Elements;

fstream file;

//STOPWATCH


//ARRAY
Array table;
Array* arrayPointer = new Array();

char arrayinput;
char arrayMeasurementsInput_K;
char arrayMeasurementsInput_1K;
char arrayMeasurementsInput_5K;
char arrayMeasurementsInput_15K;
char arrayMeasurementsInput_50K;
char arrayMeasurementsInput_150K;

bool arrayloop = true;
int valueOfTheNewElement = 0;
int indexOfNewElement = 0;
int indexElementToRemove = 0;
int searchedNumber = 0;
int NumberOfElements = 0;
string fileName;
//BH
BinaryHeap binaryheap;
BinaryHeap* binaryheapPointer = new BinaryHeap();
//LIST
LinkedList linkedList;
char inputlinkedList;
bool linkedListloop = true;
int newKey;
//RBTREE
RedBlackNode node;
RedBlackTree redBlackTree;
char inputRedBlackTree;
bool redBlackNodeloop = true;
//
int z = 0;
int numberOfElements = 0;
string sampleFile = "a.txt";
bool f = true;
int x;

// HEAP
Heap* heap;
Heap h;

//  RB TREE 
RedBlackTree tree;
RedBlackTree *RB_Tree;

/*
The reason the program goes into an infinite loop is because std :: cin's bad input flag is set due to the input failing.
The thing to do is to clear the flag and discard the input badly from the input buffer.

*/
//executes loop if the input fails (e.g., no characters were read)
/*

int executesLoop(int value)
{
	while (std::cout << "Ponownie wprowadz liczbe [KOREKTA]" && !(std::cin >> value))
	{
		std::cin.clear(); //clear bad input flag
		std::cin.ignore(std::numeric_limits<std::streamsize>::max(),'\n'); //discard input
		std::cout << "Invalid input; please re-enter.\n";
		// http://stackoverflow.com/questions/10349857/how-to-handle-wrong-data-type-input
	}
	return value;
}
*/


// PRINTINFO
void printInfoMessage()
{
	cout << "				Struktury danych				 \n"
		<< "\t\t\t    Zadanie projektowe nr 1				 \n"
		<< "Badanie efektywnosci operacji dodawania, usuwania oraz wyszukiwania elementow w roznych strukturach danych.\n\n"
		<< "AUTOR: PAWEL SZYNAL 226026\n\n ";
}
void printInfoMenuMessage()
{
	cout << "				Struktury danych				 \n"
		<< " W celu wybrania odpowiedniej struktury danych nacisnij przycisk przyporzadkowany odpowiedniej strukturze\n\n"
		<< " Tablica \t\t  -> nacisnij '1'\n"
		<< " Lista	\t\t  -> nacisnij '2'\n"
		<< " Kopiec 		  -> nacisnij '3'\n"
		<< " Drzewo czerwono - czarne -> nacisnij '4'\n"
		<< " Wyjscie z programu \t  -> nacisnij '5'\n\n";
}
void printMeasurementsInfo()
{
	cout << "Pomiar czasu :\n\n"
		<< "dodawania na poczatek \t\t -> '1' \n"
		<< "dodawania na koniec \t\t -> '2' \n"
		<< "usowanie elementu z poczatku \t -> '3' \n"
		<< "usowanie elementu z konca\t -> '4' \n"
		<< "\wyszukiwania elementu\t\t -> '5' \n\n";
}

//tablica
void printArrayInfoMessage()
{
	cout << "\n Wybrana struktura danych: TABLICA \n\n"
		<< "Mozliwe opracaje: \n"
		<< "Wyswietl zawartosc\t\t\t\t\t -> '1' \n"
		<< "Dodaj na poczatek tablicy\t\t\t\t -> '2' \n"
		<< "Dodaj na koniec tablicy\t\t\t\t\t -> '3' \n"
		<< "Usun element z poczatku\t\t\t\t\t -> '4' \n"
		<< "Usun element z konca\t\t\t\t\t -> '5' \n"
		<< "Usun wybrany element\t\t\t\t\t -> '6' \n"
		<< "Wyszukiwanie elementu\t\t\t\t\t -> '7' \n"
		<< "Wypelnij tablice elementami pobranymi z pliku\t\t -> '8'\n"
		<< "Wypelniej tablice losowymi (pseudolosowymi) liczbami\t -> '9' \n\n"
		<< "Wyjscie z danej struktury\t\t\t\t -> 'A'\n"
		<< "W celu wybrania odpowiedniej operacji na powyzszej strukturze nacisnij\n\n";

}
// KOPIEC
void printHeepInfoMessage()
{
	cout << "\n Wybrana struktura danych: KOPIEC  \n\n"
		<< "Mozliwe opracaje: \n"
		<< "Wyswietl zawartosc \t  -> '1' \n" // JEST
		<< "Dodaj element \t\t  -> '2' \n"	//JEST
		<< "Usun elemnt  \t\t  -> '3' \n"	//jest
		<< "Wyszukaj element \t  -> '4' \n"	//JEST
		<< "Wczytaj dane z pliku\t  -> '5' \n"
		<< "Wypelniej kopiec losowymi (pseudolosowymi) liczbami\t -> '6' \n\n"
		<< "Wyjscie z danej struktury -> '7'\n"
		<< "W celu wybrania odpowiedniej operacji na powyzszej strukturze nacisnij\n\n";

}
// LISTA 
void printLinkedListInfoMessage()
{
	cout << "\n Wybrana struktura danych: LISTA \n\n"
		<< "Mozliwe opracaje: \n"
		<< "Wyswietl zawartosc\t\t\t\t\t -> '1' \n" // JEST
		<< "Dodaj na koniec Listy\t\t\t\t\t -> '2' \n"	//JEST
		<< "Dodaj na poczatek Listy\t\t\t\t\t -> '3' \n"	//JEST
		<< "Dodaj element za elementem o podanycm kluczu\t\t -> '4' \n"	//JEST
		<< "Usun element z poczatku\t\t\t\t\t -> '5' \n"	//JEST
		<< "Usun element z konca\t\t\t\t\t -> '6' \n"	// JEST
		<< "Usun wybrany elemnt\t\t\t\t\t -> '7' \n"	//jest
		<< "Wyszukaj element\t\t\t\t\t -> '8' \n"	//JEST
		<< "Wypelnij liste elementami pobranymi z pliku\t\t -> '9'\n"	//JEST
		<< "Wypelniej liste losowymi (pseudolosowymi) liczbami\t -> 'A' \n" // JEST
		<< "Wyjscie z danej struktury\t\t\t\t -> 'B'\n"
		<< "W celu wybrania odpowiedniej operacji na powyzszej strukturze nacisnij\n\n";
}

// RB_TREE
void printRB_TREEInfoMessage()
{
	cout << "\n Wybrana struktura danych: Drzewo czerwono - czarne  \n\n"
		<< "Mozliwe opracaje: \n"
		<< "Wyswietl zawartosc \t  -> '1' \n" // JEST
		<< "Dodaj element \t\t  -> '2' \n"	//JEST
		<< "Usun elemnt  \t\t  -> '3' \n"	//jest
		<< "Wyszukaj element \t  -> '4' \n"	//JEST
		<< "Wczytaj dane z pliku\t  -> '5' \n"
		<< "Wypelniej kopiec losowymi (pseudolosowymi) liczbami\t -> '6' \n\n"
		<< "Wyjscie z danej struktury -> '7'\n"
		<< "W celu wybrania odpowiedniej operacji na powyzszej strukturze nacisnij\n\n";
}
// CSTOPER
double PCFreq;
__int64 counterStart;

void startCounter()
{
	LARGE_INTEGER li;
	if (!QueryPerformanceFrequency(&li))
		cout << "QueryPerformanceFrequency failed!\n";

	PCFreq = double(li.QuadPart) / 1000.0;

	QueryPerformanceCounter(&li);
	counterStart = li.QuadPart;
}

void getCounter()
{
	LARGE_INTEGER li;
	QueryPerformanceCounter(&li);
	cout << "Operacja zajela: " << (li.QuadPart - counterStart) / PCFreq << " milisekund\n" << endl;
	//cout << (li.QuadPart - counterStart) / PCFreq << endl;
}

char MenuDataStructures(char input)
{

	switch (input)
	{
		// ___________________________________TABLICA_____________________________________________________ 		
	case '1':
	{
		while (arrayloop == true)
		{
			//TABLICA
			printArrayInfoMessage();
			cin >> arrayinput;
			system("cls");

			switch (arrayinput)
			{

			case '1':
			{
				cout << "Wyswietlanie zawartosci\n\n";
				table.show();
				break;
			}

			case '2':
			{
				cout << "Dodawanie na poczatek tablicy\n\n"
					<< "PRZYPOMNIENIE O ZALOZENIACH PROGRAMU\n"
					<< "Podstawowym elementem struktur danych jest 4 bajtowa liczba calkowita ze znakiem\n\n";

				while (std::cout << "Wprowadz liczbe " && !(std::cin >> valueOfTheNewElement))
				{
					std::cin.clear(); //clear bad input flag
//					std::cin.ignore(std::numeric_limits<std::streamsize>::max(), '\n'); //discard input
//					std::cout << "Invalid input; please re-enter.\n";
				}

				table.addAtTheBeginning(valueOfTheNewElement);
				cout << "Dodano elemet\n";
				NumberOfElements++;
				valueOfTheNewElement = 0;
				break;
			}
			case '3':
			{
				cout << "Dodawanie na koniec tablicy \n\n"
					<< "PRZYPOMNIENIE O ZALOZENIACH PROGRAMU\n"
					<< "Podstawowym elementem struktur danych jest 4 bajtowa liczba calkowita ze znakiem\n\n";
				cout << "\nPodaj wartosc nowego elemntu pamietajac o zalozeniach:		";


				while (std::cout << "Wprowadz liczbe " && !(std::cin >> valueOfTheNewElement))
				{
					std::cin.clear(); //clear bad input flag
				//	std::cin.ignore(std::numeric_limits<std::streamsize>::max(), '\n'); //discard input
				//	std::cout << "Invalid input; please re-enter.\n";
				}

				table.addAtTheEnd(NumberOfElements, valueOfTheNewElement);
				NumberOfElements++;
				cout << "Dodano element";
				indexOfNewElement = 0;
				valueOfTheNewElement = 0;
				break;
			}

			case '4':
			{
				cout << "Usuwanie elementu z poczatku tablicy \n\n";
				table.removeFromBeginning();
				cout << "Element zosatl usuniety\n";
				break;
			}
			case '5':
			{
				cout << "Usuwanie elementu z konca tablicy \n\n";
				table.removeFromEnd();
				cout << "Element zosatl usuniety\n";
				break;
			}
			case '6':
			{

				cout << "Usuwanie wybranego elementu z tablicy \n\n";
				while (std::cout << "Wprowadz ideks liczby " && !(std::cin >> indexElementToRemove))
				{
					std::cin.clear(); //clear bad input flag
//					std::cin.ignore(std::numeric_limits<std::streamsize>::max(), '\n'); //discard input
//					std::cout << "Invalid input; please re-enter.\n";
				}
				table.remove(indexElementToRemove);
				cout << "\nElement zostal usuniety\n";
				indexElementToRemove = 0;
				break;
			}
			case '7':
			{
				cout << "Wyszukiwanie elementu \n\n";
				cout << "Wpisz szukany element";
				while (std::cout << "Wprowadz liczbe " && !(std::cin >> searchedNumber))
				{
					std::cin.clear(); //clear bad input flag
//					std::cin.ignore(std::numeric_limits<std::streamsize>::max(), '\n'); //discard input
//					std::cout << "Invalid input; please re-enter.\n";
				}
				table.search(searchedNumber);
				searchedNumber = 0;
				break;

			}
			case '8':
			{
				cout << "Wypelnianie tablicy elementami pobranymi z pliku\n\n"
					<< "TABLICA TESTY wybierz ->0\n"
					<< "TABLICA 1K elementowa wybierz ->1\n"
					<< "TABLICA 5K elementowa wybierz ->2\n"
					<< "TABLICA 15K elementowa wybierz ->3\n"
					<< "TABLICA 50K elementowa wybierz ->4\n"
					<< "TABLICA 100K elementowa wybierz ->5\n";
				cout << "WYBIERAM: ";

				while (std::cout << "Wprowadz liczbe " && !(std::cin >> inputK_Elements))
				{
					std::cin.clear(); //clear bad input flag
//					std::cin.ignore(std::numeric_limits<std::streamsize>::max(), '\n'); //discard input
	//				std::cout << "Invalid input; please re-enter.\n";
				}

				switch (inputK_Elements)
				{
				case '0':
				{

					file.open("TESTY.txt", ios::in);
					if (file.is_open()) {
						for (int i = 0; i < 10; i++) {
							file >> valueOfTheNewElement;
							table.addAtTheBeginning(valueOfTheNewElement);
							NumberOfElements++;
						}
					}
					file.close();
					//table.fillElementsTakenFromFile("Elements_1K.txt", 1000); // 

					break;
				}
				case '1':
				{

					file.open("Elements_1K.txt", ios::in);
					if (file.is_open()) {
						for (int i = 0; i < 1000; i++) {
							file >> valueOfTheNewElement;
							table.addAtTheBeginning(valueOfTheNewElement);
							NumberOfElements++;
						}
					}
					file.close();
					//table.fillElementsTakenFromFile("Elements_1K.txt", 1000); // 

					break;

				}
				case '2':
				{
					file.open("Elements_5K.txt", ios::in);
					if (file.is_open()) {
						for (int i = 0; i < 5000; i++) {
							file >> valueOfTheNewElement;
							table.addAtTheBeginning(valueOfTheNewElement);
							NumberOfElements++;
						}
					}
					file.close();
					//table.fillElementsTakenFromFile("Elements_5K.txt", 5000); // FILENAME_5K_ELEMENT
					break;
				}
				case '3':
				{
					//table.fillElementsTakenFromFile("Elements_15K.txt", 15000); // FILENAME_15K_ELEMENT
					file.open("Elements_15K.txt", ios::in);
					if (file.is_open()) {
						for (int i = 0; i < 5000; i++) {
							file >> valueOfTheNewElement;
							table.addAtTheBeginning(valueOfTheNewElement);
							NumberOfElements++;
						}
					}
					file.close();
					break;
				}
				case '4':
				{

					file.open("Elements_50K.txt", ios::in);
					if (file.is_open()) {
						for (int i = 0; i < 50000; i++) {
							file >> valueOfTheNewElement;
							table.addAtTheBeginning(valueOfTheNewElement);
						}
					}
					file.close();
					//table.fillElementsTakenFromFile("Elements_50K.txt", 50000); // FILENAME_50K_ELEMENT
					break;
				}
				case '5':
				{
					file.open("Elements_100K.txt", ios::in);
					if (file.is_open()) {
						for (int i = 0; i < 100000; i++) {
							file >> valueOfTheNewElement;
							table.addAtTheBeginning(valueOfTheNewElement);
							NumberOfElements++;
						}
					}
					file.close();
					//table.fillElementsTakenFromFile("Elements_100K.txt", 100000); // FILENAME_100K_ELEMENT
					break;
				}
				default:
				{
					cout << "ERROR";
					break;
				}

				} // KONIEC WCZYTYWANIA PLIKU 
				break; // koniec '8'
			}
			// 

			case '9':
			{
				cout << "Wypelnianie tablicy losowymi (pseudolosowymi) liczbami \n\n"
					<< "Ilosc wygenerowanych liczb: ";
				while (std::cout << "Wprowadz liczbe " && !(std::cin >> NumberOfElements))
				{
					std::cin.clear(); //clear bad input flag
//					std::cin.ignore(std::numeric_limits<std::streamsize>::max(), '\n'); //discard input
	//s				std::cout << "Invalid input; please re-enter.\n";
				}

				table.fillRandomNumbers(NumberOfElements);
				cout << "\nTablica wypelniona";
				break;
			}

			case 'A':
			{
				cout << "Wyjscie z struktury";
				arrayloop = false;
				break;
			}
			default:
			{
				cout << "ERROR";
				break;
			}
			}//

		}// KONIEC PETLI TABLICY
		break;
	}


	// ________________________________________LISTA_____________________________________________________ 	
	case '2':
	{

		linkedListloop = true;
		unsigned value;
		LinkedList* linkedListPointer;
		linkedListPointer = new LinkedList();
		while (linkedListloop == true)
		{
			printLinkedListInfoMessage();
			cin >> inputlinkedList;
			system("cls");
			switch (inputlinkedList)
			{
			case '1':
			{
				cout << "Wyswietlanie zawartosci\n\n";
				linkedList.show();
			}break;
			case '2': // dodaj na koniec listy
			{
				cout << "Podaj wartosc nowego elementu" << endl;
				cin >> newKey;
				cout << endl;
				counterStart = 0;
				startCounter();
				linkedList.addAtTheEnd(newKey);
				getCounter();


			}break;
			case '3': //	<< "Dodaj na poczatek Listy
			{
				cout << "Podaj wartosc nowego elementu" << endl;
				cin >> newKey;
				cout << endl;
				counterStart = 0;
				startCounter();
				linkedList.addAtTheBeginning(newKey);
				getCounter();

			}break;
			case '4': //<< "Dodaj element za elementem o podanycm kluczu 
			{
				int key;
				cout << "Podaj wartosc elementu do wstawienia" << endl;
				cin >> key;
				cout << endl;
				cout << "Za ktorym elementem chcesz wstawic nowy ?" << endl;
				cin >> value;
				cout << endl;
				linkedList.addNewElementAfterSpecifiedKey(value, key);
			}break;
			case '5': //<< "Usun element z poczatku
			{
				linkedList.removeFromBeginning();
			}break;
			case '6': // "Usun element z konca
			{
				linkedList.removeFromEnd();
			}break;
			case '7': // << "Usun wybrany elemnt
			{
				cout << "Co usunac  (podaj ideks)?" << endl;
				cin >> value;
				cout << endl;
				linkedList.remove(value);
			}break;
			case '8': //	<< "Wyszukaj element
			{
				cout << "Podaj klucz, ktory chcesz sprawdzic, czy jest na liscie" << endl;
				cin >> value;
				cout << endl;
				counterStart = 0;
				startCounter();
				linkedList.search(value);
				getCounter();
				if (linkedList.search(value)) { cout << "Szukany element znajduje sie na liscie" << endl; }
				else { cout << "Szukego elementu nie ma na liscie" << endl; }
			}break;
			case '9': //	<< "Wypelnij liste elementami pobranymi z pliku
			{
				linkedList.~LinkedList();
				linkedListPointer = new LinkedList();
				cout << "Wypelnianie listy elementami pobranymi z pliku\n\n"
					<< "TEST wybierz ->0\n"
					<< "Lista 1K elementowa wybierz ->1\n"
					<< "Lista 5K elementowa wybierz ->2\n"
					<< "Lista 15K elementowa wybierz ->3\n"
					<< "Lista 50K elementowa wybierz ->4\n"
					<< "Lista 100K elementowa wybierz ->5\n";
				cout << "WYBIERAM: ";

				while (std::cout << "Wprowadz liczbe " && !(std::cin >> inputK_Elements))
				{
					std::cin.clear(); //clear bad input flag
//					std::cin.ignore(std::numeric_limits<std::streamsize>::max(), '\n'); //discard input
	//				std::cout << "Invalid input; please re-enter.\n";
				}

				switch (inputK_Elements)
				{
				case '0':
				{

					file.open("TESTY.txt", ios::in);
					if (file.is_open()) {
						for (int i = 0; i < 10; i++) {
							file >> valueOfTheNewElement;
							linkedList.addAtTheEnd(valueOfTheNewElement);
						}
					}
					file.close();
					//	linkedList.numberOfElements = 10;
						//linkedList.fillElementsTakenFromFile("Elements_1K.txt", 1000); // FILENAME_1K_ELEMENT
					break;
				}
				case '1':
				{
					file.open("Elements_1K.txt", ios::in);
					if (file.is_open()) {
						for (int i = 0; i < 1000; i++) {
							file >> valueOfTheNewElement;
							linkedList.addAtTheBeginning(valueOfTheNewElement);
						}
					}
					file.close();
					//linkedList.fillElementsTakenFromFile("Elements_1K.txt", 1000); // FILENAME_1K_ELEMENT
					break;
				}
				case '2':
				{
					file.open("Elements_5K.txt", ios::in);
					if (file.is_open()) {
						for (int i = 0; i < 5000; i++) {
							file >> valueOfTheNewElement;
							linkedList.addAtTheBeginning(valueOfTheNewElement);
						}
					}
					file.close();
					//	linkedList.fillElementsTakenFromFile("Elements_5K.txt", 5000); // FILENAME_5K_ELEMENT
					break;
				}
				case '3':
				{
					file.open("Elements_15K.txt", ios::in);
					if (file.is_open()) {
						for (int i = 0; i < 15000; i++) {
							file >> valueOfTheNewElement;
							linkedList.addAtTheBeginning(valueOfTheNewElement);
						}
					}
					file.close();
					//linkedList.fillElementsTakenFromFile("Elements_50K.txt", 15000); // FILENAME_15K_ELEMENT
					break;
				}
				case '4':
				{
					file.open("Elements_50K.txt", ios::in);
					if (file.is_open()) {
						for (int i = 0; i < 50000; i++) {
							file >> valueOfTheNewElement;
							linkedList.addAtTheBeginning(valueOfTheNewElement);
						}
					}
					file.close();
					//linkedList.fillElementsTakenFromFile("Elements_50K.txt", 50000); // FILENAME_50K_ELEMENT
					break;
				}
				case '5':
				{
					file.open("Elements_100000K.txt", ios::in);
					if (file.is_open()) {
						for (int i = 0; i < 100000; i++) {
							file >> valueOfTheNewElement;
							linkedList.addAtTheBeginning(valueOfTheNewElement);
						}
					}
					file.close();
					//linkedList.fillElementsTakenFromFile("Elements_100K.txt", 100000); // FILENAME_100K_ELEMENT
					break;
				}
				default:
					cout << "ERROR";
					break;
				}

			}break;
			case 'A':  //		<< "Wypelniej liste losowymi (pseudolosowymi) liczbami
			{
				linkedList.~LinkedList();
				while (true)
				{
					cout << "Ilu elementowa liste stworzyc?" << endl;
					cin >> value;
					cout << endl;
					if (value <= 0) cout << "Licza elementow jest nieprawidlowa!" << endl;
					else break;
				}
				linkedListPointer = new LinkedList();
				linkedList.randomFillLinkedList(value);
			}break;
			case 'B':  //		<< "Wyjscie z danej struktury\t\t\t\t -> 'B'\n"
			{
				delete linkedListPointer;
				system("cls");
				linkedListloop = false;
			}break;

			}


		}// KONIEC PETLI 

	}break;
	// ________________________________________KOPIEC_____________________________________________________ 	
	case '3':
	{


		char heapInput;
		f = true;
		while (true)
		{
			printHeepInfoMessage(); // MENU KOPCA
			cin >> heapInput;
			switch (heapInput)
			{
				int heapValue;
			case '1':
				system("cls");
				h.show("", "", 0);
				break;
			case '2':
				system("cls");
				cout << "Podaj liczbe jaka chcesz dodac\n";
				cin >> heapValue;
				counterStart = 0; // NASTAWIAM LICZNIK
				startCounter(); // CZAS START 
				h.addNewElement(0, heapValue);
				getCounter(); // CZAS STOP
				break;
			case '3':
				system("cls");  //Usuwanie elementu ze szczytu kopca – pop
				counterStart = 0; // NASTAWIAM LICZNIK
				startCounter(); // CZAS START 
				h.remove(0);
				getCounter(); // CZAS STOP
				break;

			case '4':
				system("cls");
				cout << "Podaj liczbe jaka chcesz znalesc\n";
				cin >> heapValue;
				//				counterStart = 0; // NASTAWIAM LICZNIK
				//				startCounter(); // CZAS START 
				if (h.search(heapValue)) { cout << "Szukany element znajduje sie w kopcu"; }
				else { cout << "Szukany element nie znajduje sie w kopcu"; }
				//				getCounter(); // CZAS STOP
				break;

			case '5':
				h.fillElementsTakenFromFile("KOPIEC.txt");
				file.close();

				break;
			case '6':
				cout << "Podaj rozmiar kopca: ";
				int size;
				cin >> size;
				heap = new  Heap(size);
				heap->show("", "", 0);
				break;
			case '7':
				f = false;
				break;
			default:
				cout << "ERROR";
				break;
			}
			if (f == false)
				break;

		}
		break;
		break;
	}

	// _______________________________________R_B_TREE____________________________________________________
	case '4':
	{
		f = true;
		char loopforrbtree;
		while (true)
		{
			printRB_TREEInfoMessage();
			cin >> loopforrbtree;
			switch (loopforrbtree)
			{
				int element;
			case '1':
				system("cls");
				tree.Show();
				break;
			case '2':
				system("cls");
				cout << "Podaj liczbe jaka chcesz dodac\n";
				cin >> element;
				counterStart = 0; // NASTAWIAM LICZNIK
				startCounter(); // CZAS START 
				tree.InsertNode(0, element);
				getCounter(); // CZAS STOP
				tree.Show();
				break;
			case '3':
				system("cls");
				cout << "Podaj liczbe jaka chcesz usunac\n";
				cin >> element;
				counterStart = 0; // NASTAWIAM LICZNIK
				startCounter(); // CZAS START 
				RedBlackNode *auxiliaryTree;
				auxiliaryTree = tree.Search(element);
				if (auxiliaryTree != NULL)
				{
					tree.Remove(auxiliaryTree);
				}
				getCounter(); // CZAS STOP
				tree.Show();

				break;
			case '4':
				system("cls");
				cout << "Podaj liczbe jaka chcesz znalesc\n";
				cin >> element;
				if (tree.Search(element)) { cout << "Szukany element znajduje sie w drzewie"; }
				else { cout << "Szukany element nie znajduje sie w drzewie"; }
				break;

			case '5':
			
				tree.FillElementsTakenFromFile("TREE.txt");
				tree.Show();
				file.close();

				break;
			case '6':
				cout << "Podaj rozmiar : ";
				int size;
				cin >> size;
				RB_Tree = new RedBlackTree();
				RB_Tree->RandomFillRedBlack(size);
				RB_Tree->Show();
				break;
			case '7':
				f = false;
				break;
			default:
				cout << "ERROR";
				break;

			}
			if (f == false)
				break;
		}

	}
	case '5':
	{
		cout << "EXIT";
		loop = false;
		break;
	}
	default:
	{
		cout << "ERROR";
		break;
	}
	}
	return 0;
}


int main()
{
	/*
			PANEL POMIAROWY
	*/
	/*
	int k = 0;
	for (int j = 0; j < 1000; j++)
	{
		k++;


	}

	for (int i = 0; i < 100; i++)
	{

		counterStart = 0; // NASTAWIAM LICZNIK
		startCounter(); // CZAS START

		getCounter();

	}
	getchar();
	*/

	do
	{
		system("cls");
		printInfoMessage();
		printInfoMenuMessage();
		cin >> input;
		MenuDataStructures(input);
	} while (loop);

	return 0;
}

