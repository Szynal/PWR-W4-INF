#pragma once
#include "Graph.h"
#include "BranchAndBound.h"
#include "BruteForce.h"

class menu
{
private:
	std::string mainMenuStr = (std::string)" 1. Wczytaj dane z pliku. \n 2. Wyswietl graf \n 3. BruteForce \n 4. BranchAndBound \n 5. Testy!!!";

	Graph * _graph = nullptr;

	double PCFreq = 0.0;
	__int64 CounterStart = 0;

	void StartCounter();
	double GetCounter();

	void mainMenu();

	BranchAndBound * BB;
	BruteForce * BF;

public:
	menu();
	~menu();
};

