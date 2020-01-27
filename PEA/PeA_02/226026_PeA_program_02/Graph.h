#pragma once
#include <string>
#include <vector>

class Graph
{
private:

public:
	Graph();
	~Graph();

	std::vector<std::vector<int>> CityMatrix;
	int MatrixSize = 0;

	void LoadGraph(std::string fileName);
	void DrawMatrix();
	void DrawMatrixSize();
};

