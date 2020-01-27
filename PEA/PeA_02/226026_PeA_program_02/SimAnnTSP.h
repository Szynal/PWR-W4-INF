#pragma once
#include <vector>
#include "Graph.h"

class SimAnnTSP
{
private:

	Graph * G;

	double Temp;
	double minT;
	double TempConst;

	int Solution;

	std::vector<int> firstPermutation;
	std::vector<int> secondPermutation;

	void generatePermutation(std::vector<int> & perm);

	void showSolution();

	int calcSolutionCost(std::vector<int> solutionToCalc);

	int probability(int a, int b);
	
public:
	SimAnnTSP(Graph * _g);
	~SimAnnTSP();
	void Resolve();
};

