#include "pch.h"
#include "BranchAndBound.h"
#include <ostream>
#include <iostream>
#include <algorithm>

#define startVertex 0;

int BranchAndBound::reduceMatrix(std::vector<std::vector<int>>* matrix_to_put, BBElement * from)
{
	for (int i = 0; i < matrix_to_put->size(); ++i)
	{
		(*matrix_to_put)[i][i] = INT32_MAX;
	}

	int minimalX = 0;
	int minimalY = 0;

	for (int i = 0; i < matrix_to_put->size(); ++i)
	{
		int minimal=INT32_MAX;
		for (int j = 0; j < matrix_to_put->size(); ++j)
		{
			minimal = std::min(minimal, (*matrix_to_put)[i][j]);
		
		}
		if (minimal != 0 || minimal!=INT32_MAX)
		{
			for (int j = 0; j < matrix_to_put->size(); ++j)
			{
				if ((*matrix_to_put)[i][j] != INT32_MAX)
				{
					(*matrix_to_put)[i][j] -= minimal;
				}
			}
		}
		if (minimal!=INT32_MAX)
		{
			minimalX += minimal;
		}
	}

	for (int i = 0; i < matrix_to_put->size(); ++i)
	{
		int minimal= INT32_MAX;
		for (int j = 0; j < matrix_to_put->size(); ++j)
		{
			minimal = std::min(minimal, (*matrix_to_put)[j][i]);
		}
		if (minimal != 0 || minimal != INT32_MAX)
		{
			for (int j = 0; j < matrix_to_put->size(); ++j)
			{
				if ((*matrix_to_put)[j][i] != INT32_MAX)
				{
					(*matrix_to_put)[j][i] -= minimal;
				}

			}
		}
		if (minimal != INT32_MAX)
		{
			minimalY += minimal;
		}
	}

	return minimalX + minimalY;
}

void BranchAndBound::travel(std::vector<std::vector<int>>* matrix_to_put, int vertex, int vertex1)
{
	(*matrix_to_put)[vertex1][vertex] = INT32_MAX;
	for (int i = 0; i < matrix_to_put->size(); ++i)
	{
		(*matrix_to_put)[vertex][i] = INT32_MAX;
		(*matrix_to_put)[i][vertex1] = INT32_MAX;
	}
}

int BranchAndBound::getLowestCostNode()
{
	int tmp;
	for (int i = 0; i < nodeArray.size(); i++)
	{
		if(!nodeArray[i].done)
		{
			tmp = i;
			break;
		}
	}

	for (int i = 0; i<nodeArray.size();i++)
	{
		if (nodeArray[i].upperBound <= nodeArray[tmp].upperBound && !nodeArray[i].done)
		{
			tmp = i;
		}
		if (nodeArray[i].route.size() == G->MatrixSize)
		{
			nodeArray[i].done = true;
			lowerBound = nodeArray[i].upperBound;
			for (int j = 0; j < nodeArray.size(); j++)
			{
				if (nodeArray[j].upperBound>lowerBound && !nodeArray[j].done)
				{
					nodeArray[j].done = true;
					result = &nodeArray[i];
				}
			}
		}
		
	}

	return tmp;
}

BBElement * BranchAndBound::createBBElement(int vertex, BBElement * from)
{
	int loweBound=-1;
	std::vector<std::vector<int>> * matrixToPut = new std::vector<std::vector<int>>;
	if (from==nullptr)
	{
		matrixToPut->resize(G->MatrixSize, std::vector<int>(G->MatrixSize));
		*matrixToPut = G->CityMatrix;
		loweBound = reduceMatrix(matrixToPut, from);
	}
	else
	{
		matrixToPut->resize(G->MatrixSize, std::vector<int>(G->MatrixSize));
		*matrixToPut = *from->matrixCost;
		travel(matrixToPut,from->vertex,vertex);
		loweBound = reduceMatrix(matrixToPut,from)+(*nodeArray[0].matrixCost)[from->vertex][vertex]+from->upperBound;		
	}

	return new BBElement(vertex,from,loweBound, matrixToPut);
}

bool BranchAndBound::everyNodeIsDone()
{
	for (BBElement node_element : nodeArray)
	{
		if (!node_element.done)
		{
			return false;
		}
	}
	return true;
}

bool BranchAndBound::vertexExistInRoute(int v, BBElement * element)
{
	for (int route1 : element->route)
	{
		if (route1 == v)
		{
			return true;
		}
	}
	return false;
}


void BranchAndBound::Resolve()
{
	nodeArray.push_back(*createBBElement(0, nullptr));

	while (true)
	{
		int tmp = getLowestCostNode();
		if (everyNodeIsDone())
		{
			break;
		}
		std::vector<BBElement> tmpArr;
		for (int i = 0; i < G->MatrixSize; ++i)
		{
			if (!vertexExistInRoute(i, &nodeArray[tmp]))
			{
				tmpArr.push_back(*createBBElement(i, &nodeArray[tmp]));
			}
		}
		nodeArray[tmp].done = true;
		for (BBElement tmp_arr : tmpArr)
		{
			nodeArray.push_back(tmp_arr);
		}
	}

}

int BranchAndBound::calcRootCost()
{
	int cost = 0;
	for (int i = 0; i < (G->MatrixSize-1); ++i)
	{
		cost += G->CityMatrix[(*result).route[i]][(*result).route[i + 1]];
	}
	return cost + G->CityMatrix[(*result).route[G->MatrixSize - 1]][(*result).route[0]];
}

//for (int i = 0; i < (*result).route.size(); ++i)
//{
//	std::cout << (*result).route[i] << " - ";
//}
void BranchAndBound::ShowRoute()
{
	std::cout << "Najkrotszy cykl Hamiltonowski o koszcie(B&B): " << calcRootCost() << std::endl;
	for (int i = 0; i < G->MatrixSize; i++)
	{
		if (i < G->MatrixSize - 1)
			std::cout << (*result).route[i] << " -> ";
		else
			std::cout << (*result).route[i] << " -> " << (*result).route[0] << std::endl;
	}
}

BranchAndBound::BranchAndBound(Graph * _G)
{
	G = _G;
}


BranchAndBound::~BranchAndBound()
{
}
