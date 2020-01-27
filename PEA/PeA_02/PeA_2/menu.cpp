#include "menu.h"
#include <iostream>
#include <ctime>
#include <windows.h>

void menu::mainMenu()
{
	int decision = 0;
	int directed = 1;
	int verticle = 1;
	float density = 1;
	int rangeVal = 1;
	int startVal = 1;
	std::string fname = "";
	double Time = 0;
	
	do
	{
		std::cout << mainMenuStr << std::endl;
		std::cin >> decision;
		Time = 0;
		switch (decision)
		{
		case 1:
			std::cout << "Podaj nazwe pliku" << std::endl;
			std::cin >> fname;
			_graph->LoadGraph(fname);
			break;
		case 2:
			_graph->DrawMatrixSize();
			_graph->DrawMatrix();
			break;
		case 3:
			delete SimAnn;
			SimAnn = new SimAnnTSP(_graph);
			StartCounter();
			SimAnn->Resolve();
			Time += GetCounter();
			std::cout << std::endl << "Time: " << Time << std::endl;
			break;
		case 4:
			Time = 0;
			srand(time(NULL));
			std::cout << "1. TS \n2. SA \n" << std::endl;
			std::cin >> decision;
			switch (decision)
			{
			case 1:
				for (int i = 0; i < 10; i++)
				{
					delete SimAnn;
					SimAnn = new SimAnnTSP(_graph);
					StartCounter();
					SimAnn->Resolve();
					Time += GetCounter();
				}
				break;
			default:
				std::cout << "zly wybor xD" << std::endl;
				break;
			}

			std::cout << std::endl << "Time: " << Time / 10 << std::endl;

			break;
		default:
			std::cout << "zly wybor " << std::endl;
			break;
		}
	} while (decision);

}

void menu::StartCounter()
{
	LARGE_INTEGER li;
	if (!QueryPerformanceFrequency(&li))
		std::cout << "QueryPerformanceFrequency failed!\n";

	PCFreq = double(li.QuadPart) / 1000.0;

	QueryPerformanceCounter(&li);
	CounterStart = li.QuadPart;
}

double menu::GetCounter()
{
	LARGE_INTEGER li;
	QueryPerformanceCounter(&li);
	return double(li.QuadPart - CounterStart) / PCFreq;
}


menu::menu()
{

	_graph = new Graph;
	
	SimAnn = new SimAnnTSP(_graph);

	mainMenu();
}


menu::~menu()
{
}