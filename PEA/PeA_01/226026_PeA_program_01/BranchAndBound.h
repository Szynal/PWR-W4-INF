#pragma once
#include "Graph.h"
#include "BBElement.h"

class BranchAndBound
{
private:
	std::vector<BBElement> nodeArray;
	int lowerBound = INT32_MAX;
	Graph * G;
	int reduceMatrix(std::vector<std::vector<int>>* matrix_to_put, BBElement * from);
	void travel(std::vector<std::vector<int>>* matrix_to_put, int vertex, int vertex1);
	int getLowestCostNode();
	BBElement * createBBElement(int vertex, BBElement * from);
	bool everyNodeIsDone();
	bool vertexExistInRoute(int v, BBElement * element);
	BBElement * result = nullptr;
	int calcRootCost();
public:	
	void Resolve();
	void ShowRoute();
	BranchAndBound(Graph * _G);
	~BranchAndBound();
};

