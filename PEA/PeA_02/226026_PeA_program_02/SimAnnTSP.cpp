#include "SimAnnTSP.h"
#include <algorithm>
#include <iostream>


void SimAnnTSP::generatePermutation(std::vector<int>& perm)
{
	for (int i = 0; i < G->MatrixSize; ++i)
	{
		perm[i] = i;
	}

	std::random_shuffle(perm.begin(), perm.end());
}

void SimAnnTSP::showSolution()
{
	std::cout << "Cost of route: " << calcSolutionCost(firstPermutation) << std::endl;


	for (int i = 0; i < G->MatrixSize; i++)
	{
		if (i == G->MatrixSize - 1)
		{
			std::cout << firstPermutation[i] << std::endl;
		}
		else
		{
			std::cout << firstPermutation[i] << " -> ";
		}
	}
}

int SimAnnTSP::calcSolutionCost(std::vector<int> solutionToCalc)
{
	int cost = 0;

	for (int i = 0; i < G->MatrixSize; ++i)
	{
		if (i == G->MatrixSize - 1)
		{
			cost += G->CityMatrix[solutionToCalc[i]][solutionToCalc[0]];
		}
		else
		{
			cost += G->CityMatrix[solutionToCalc[i]][solutionToCalc[i + 1]];
		}
	}

	return cost;
}

int SimAnnTSP::probability(int a, int b)
{
	double q = pow(2.71828182845904523536, ((-1 * (b - a)) / Temp));	//wg wzoru
	double r = (double)rand() / RAND_MAX;
	if (r < q)
		return 1; // gorsza sciezka
	else
		return 0;
}


void SimAnnTSP::Resolve()
{
	double temporaryDifference, difference = 0;
	for (int i = 0; i < G->MatrixSize; i++) // tyle ile miast, wyznaczenie temperatury
	{
		generatePermutation(firstPermutation); // generowanie permutacji
		generatePermutation(secondPermutation);
		temporaryDifference = abs(calcSolutionCost(firstPermutation) - calcSolutionCost(secondPermutation)); // obliczanie roznicy odleglosci
		if (temporaryDifference > difference) // szuka najwiekszej i nadpisuje
			difference = temporaryDifference;
	}

	Temp = difference; // wyliczona temp

	int firstCity, secondCity, a, b;
	generatePermutation(firstPermutation); // genereuje losowa permutacje
	a = calcSolutionCost(firstPermutation); // liczymy sciezke dla permutacji
	for (int i = 0; i < G->MatrixSize; i++)
		secondPermutation[i] = firstPermutation[i]; // przepisuje zeby 


	while (Temp > minT) // dopoki temp bedzie wieksza od minimalnej
	{
		do {
			firstCity = rand() % G->MatrixSize; // losowanie miast do zamiany 
			secondCity = rand() % G->MatrixSize;
		} while (firstCity == secondCity);

		secondPermutation[secondCity] = firstPermutation[firstCity]; // zamiana miast
		secondPermutation[firstCity] = firstPermutation[secondCity];
		b = calcSolutionCost(secondPermutation); // liczymy 2 sciezke 

		if (b <= a || probability(a, b) == 1)  // sprawdzam czy b >= a lub obliczam prawdopodobienstwo
		{ // lcize ktora sciezka jest lepsza 
			a = b;
			if (a <= Solution) // sprawdzam czy sciezka jest lepsza niz obecne rozwiazanie
				Solution = a;
			firstPermutation[firstCity] = secondPermutation[firstCity];
			firstPermutation[secondCity] = secondPermutation[secondCity]; // przyjmujemy 2 permutacje jako kolejna wyjsciowa

			// std::cout << calcSolutionCost(firstPermutation) << std::endl;
		}
		else
		{
			secondPermutation[firstCity] = firstPermutation[firstCity]; // lub wracamy do stanu poczatkowego
			secondPermutation[secondCity] = firstPermutation[secondCity]; // brak lepszej sciezki 

			// std::cout << calcSolutionCost(secondPermutation) << std::endl;
		}
		Temp *= TempConst; // mnozenie razy wyznaczona stala
		
	}

	showSolution();
}

SimAnnTSP::SimAnnTSP(Graph * _g)
{
	G = _g;

	minT = 0.1;
	TempConst = 0.999;
	Solution = INT_MAX;

	firstPermutation.resize(G->MatrixSize);
	secondPermutation.resize(G->MatrixSize);

	Temp = 0;
}


SimAnnTSP::~SimAnnTSP()
{
}