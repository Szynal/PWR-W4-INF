#include "Graph.h"
#include <iostream>
#include <fstream>
#include <sstream>


Graph::Graph()
{
	MatrixSize = 10;
	CityMatrix.resize(MatrixSize, std::vector<int>(MatrixSize, 0));
}


Graph::~Graph()
{
}


void Graph::LoadGraph(std::string fileName)
{
	using namespace std;

	ifstream file;
	file.open(fileName, ios::in | ios::out);

	if (!file.is_open()) {
		std::cout << "error reading file" << endl;
	}
	else
	{
		file >> MatrixSize;
		CityMatrix.resize(MatrixSize, std::vector<int>(MatrixSize, 0));
		for (int i = 0; i < MatrixSize; ++i)
		{
			CityMatrix[i].resize(MatrixSize);
		}


		for (int i = 0; i < MatrixSize; ++i)
		{
			for (int j = 0; j < MatrixSize; ++j)
			{
				file >> CityMatrix[i][j];
			}
		}
		std::cout << "OK!" << endl;
	}
}

void Graph::DrawMatrix()
{
	std::cout << "Tablica miast:" << std::endl;
	for (int i = 0; i < MatrixSize; ++i)
	{
		for (int j = 0; j < MatrixSize; ++j)
		{
			std::cout << CityMatrix[i][j] << " ";
		}
		std::cout << std::endl;
	}
}

void Graph::DrawMatrixSize()
{
	std::cout << "Matrix size: " << MatrixSize << std::endl;
}