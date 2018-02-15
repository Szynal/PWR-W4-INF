using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
/// <summary>
///    AUTOR: PAWEŁ SZYNAL 
///    NR ALBUMU: 226026
///    PRZEDMIOT: Projektowanie efektywnych algorytmów (PEA)
///    PROWADZĄCY: dr inż. Łukasz Jeleń
/// </summary>
/// 

namespace TSP
{
    public class TownGenerator
    {
        public static float[,] GenerateTown(int numberOftown, int minimalCost, int maximumCost)
        {
            float[,] towns = new float[numberOftown, numberOftown];
            Random rand = new Random();

            for (int i = 0; i < numberOftown; i++)
            {
                for (int j = 0; j < numberOftown; j++)
                {
                    if (i == j) towns[i, j] = 0;
                    else towns[i, j] = rand.Next(minimalCost, maximumCost);
                }
            }
            return towns;
        }
    }
}
