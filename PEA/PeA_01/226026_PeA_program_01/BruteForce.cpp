//Zaimplementowany zosta³ algorytm przeszukiwania w g³¹b wywo³ywany rekurencyjnie ze zmiennymi œledz¹cymi najkrótsz¹ drogê i koszt.
#include "pch.h"
#include "BruteForce.h"
#include <cstdint>
#include <iostream>


bool BruteForce::areAllCitiesVisited()
{
	for (int i = 0; i < G->MatrixSize; i++)
	{
		if (!visitedCities[i])
			return false;
	}
	return true;
}

void BruteForce::Resolve(int v)
{
	if (iteration == 0)
	{
		visitedCities[v] = true;
		tmpRoute[v] = 0;
	}
	iteration++;
	if (!areAllCitiesVisited())
	{
		visitedCities[v] = true;

		for (int c = 0; c < G->MatrixSize; c++)//czy dane miasto odwiedzone
		{
			if (!visitedCities[c] && G->CityMatrix[v][c] > 0 && c != v)
			{
				tmpCost += G->CityMatrix[v][c];
				visitedCities[c] = true;
				tmpRoute[whichCity++] = c;
				Resolve(c);

				tmpCost -= G->CityMatrix[v][c];//po odwiedzeniu miasta
				visitedCities[c] = false;
				whichCity--;
			}
		}
	}
	else
	{
		tmpCost += G->CityMatrix[tmpRoute[G->MatrixSize - 1]] [tmpRoute[0]];
		if (tmpCost < bestCost) //zapisanie najlepszej drogi i jej wartosci
		{
			bestCost = tmpCost;

			for (int i = 0; i < G->MatrixSize; i++)
				bestRoute[i] = tmpRoute[i];
		}

		tmpCost -= G->CityMatrix[tmpRoute[G->MatrixSize - 1]] [tmpRoute[0]];
	}
}

void BruteForce::ShowRoute()
{
	std::cout <<"Najkrotszy cykl Hamiltonowski o koszcie(BF): "<< bestCost<<std::endl;
	for (int i = 0; i < G->MatrixSize; i++)
	{
		if (i < G->MatrixSize - 1)
			std::cout << bestRoute[i] << " -> ";
		else
			std::cout<< bestRoute[i] <<" -> " << bestRoute[0] << std::endl;
	}
}

BruteForce::BruteForce(Graph * _G)
{
	G = _G;
	tmpRoute = new int[G->MatrixSize];
	visitedCities = new bool[G->MatrixSize];
	bestRoute = new int[G->MatrixSize];

	bestCost = INT32_MAX;
	startTop = 0;
	tmpCost = 0;
	whichCity = 1;
	iteration = 0;

	for (int i = 0; i < G->MatrixSize; ++i)
	{
		tmpRoute[i] = -1;
		visitedCities[i] = false;
		bestRoute[i] = -1;
	}
}


BruteForce::~BruteForce()
{
	delete[] visitedCities;
	delete[] bestRoute;
	delete[] tmpRoute;
}
