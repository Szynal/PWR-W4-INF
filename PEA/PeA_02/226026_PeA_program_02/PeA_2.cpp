#include "menu.h"
#include <iostream>
#include <ctime>
#include "SimAnnTSP.h"

int main()
{
	new menu;

	srand(time(NULL));

	// Graph* g = new Graph;
	// g->LoadGraph("tsp_48.txt");
	// g->DrawMatrixSize();
	// g->DrawMatrix();
	//
	//
	// SimAnnTSP* SimAnn = new SimAnnTSP(g);
	// SimAnn->Resolve();

	// TabuTSP* Tabu = new TabuTSP(g);
	// Tabu->Resolve();
	   
	int a;
	std::cin >> a;

	return 0;
}
