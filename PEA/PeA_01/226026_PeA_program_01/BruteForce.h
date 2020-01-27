#pragma once
#include "Graph.h" 

class BruteForce
{
private:
	int tmpCost; 
	int* tmpRoute;

	bool* visitedCities;
	int startTop;

	int bestCost;
	int* bestRoute;

	int whichCity;
	int iteration;

	bool areAllCitiesVisited();

	Graph * G;
public:
	void Resolve(int v);
	void ShowRoute();
	BruteForce(Graph * _G);
	~BruteForce();
};

