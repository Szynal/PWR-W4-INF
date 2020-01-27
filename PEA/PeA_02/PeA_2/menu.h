#pragma once
#include "Graph.h"
#include "SimAnnTSP.h"


class menu
{
private:
	std::string mainMenuStr = " 1. Wczytaj dane z pliku. \n 2. Wyswietl graf \n 3. Algorytm symulowanego wyzarzania (SA) \n 5. Testy!!!";

	Graph * _graph = nullptr;

	double PCFreq = 0.0;
	__int64 CounterStart = 0;

	void StartCounter();
	double GetCounter();

	void mainMenu();
	SimAnnTSP * SimAnn;

public:
	menu();
	~menu();
};
