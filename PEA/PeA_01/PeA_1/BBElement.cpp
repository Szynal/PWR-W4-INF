#include "pch.h"
#include "BBElement.h"


BBElement::BBElement(int _vertex, BBElement * _from,int _lb, std::vector<std::vector<int>> * _matrixCost)
{
	vertex = _vertex;
	from = _from;
	upperBound = _lb;
	done = false;
	

	matrixCost = _matrixCost;

	
	if (from==nullptr)
	{
		route.push_back(vertex);
	}
	else
	{
		for (int _route : from->route)
		{
			route.push_back(_route);
		}
		route.push_back(vertex);
	}

}


BBElement::~BBElement()
{
}
