#include "pch.h"
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
			delete BF;
			BF = new BruteForce(_graph);
			BF->Resolve(0);
			BF->ShowRoute();
			break;
		case 4:
			delete BB;
			BB = new BranchAndBound(_graph);
			BB->Resolve();
			BB->ShowRoute();
			break;
		case 5:
			Time = 0;
			srand(time(NULL));
			std::cout << " 1. BruteForce \n 2. BranchAndBound \n " << std::endl;
			std::cin >> decision;
			switch (decision)
			{
			case 1:
				for (int i = 0; i < 10; i++)
				{
					delete BF;
					BF = new BruteForce(_graph);
					StartCounter();
					BF->Resolve(0);
					Time += GetCounter();
				}

				break;
			case 2:
				for (int i = 0; i < 10; i++)
				{
					delete BB;
					BB = new BranchAndBound(_graph);
					StartCounter();
					BB->Resolve();
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
			std::cout << "zly wybor xD" << std::endl;
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

	BF = new BruteForce(_graph);

	BB = new BranchAndBound(_graph);

	mainMenu();
}


menu::~menu()
{
}

