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
namespace TSP_PEA
{
    public class Algorithm
    {
        /// <summary>
        /// Konstruktor który inicjalizuje parametry klasy
        /// </summary>
        /// <param name="towns"> Macierz kosztów </param>
        public Algorithm(float[,] towns)
        {
            this._Towns = towns;
            this._NumberOfTown = _Towns.GetLength(0);

            this.FinalPath = new int[_NumberOfTown + 1];
            this._Visited = new bool[_NumberOfTown];
        }
        /// <summary>
        ///  macierz kosztów
        /// </summary>
        private float[,] _Towns;

        /// <summary>
        /// Ilość miast
        /// </summary>
        private int _NumberOfTown;

        /// <summary>
        ///  Ograniczenie w postaci pseudo nieskonczoności . ZAŁOZENIE I
        /// </summary>
        private const float Restriction = float.MaxValue;

        /// <summary>
        /// Rozwiązanie w postaci najkrotszej scieżki Hamiltona
        /// </summary>
        private int[] FinalPath;

        /// <summary>
        ///  Trasa odwiedzonych wezłów drzewa 
        /// </summary>
        private bool[] _Visited;

        /// <summary>
        /// Przechowuje ostateczną wagę najkrótszej trasy
        /// </summary>
        private float ShortestPath = Restriction;

        /// <summary>
        /// Funkcja do kopiowania tymczasowego rozwiązania
        /// </summary>
        /// <param name="currentPath"> Bierząca scieżka </param>
        private void CopyFinalPath(int[] currentPath)
        {
            for (int i = 0; i < _NumberOfTown; i++)
            {
                FinalPath[i] = currentPath[i];
            }
            FinalPath[_NumberOfTown] = currentPath[0];
        }

        /// <summary>
        /// Funkcja znajduje najmniejszą wartość w danym wierszu
        /// </summary>
        /// <param name="towns"> Macierz miast </param>
        /// <param name=""></param>
        /// <param name="nr">Miasto numer: ..</param>
        /// <returns>Najkrótsze połączenie </returns>
        private float FirstMinimumValue(float[,] towns, int nr)
        {
            float min = Restriction;
            for (int k = 0; k < _NumberOfTown; k++)
            {
                if (towns[nr, k] < min && nr != k)
                {
                    min = towns[nr, k];
                }
            }
            return min;
        }

        /// <summary>
        ///  Funkcja znajdująca krawędź o drugiej najmniejszej wartośći w danym wierszu
        /// </summary>
        /// <param name="towns"></param>
        /// <param name=""></param>
        /// <param name="i"></param>
        /// <returns></returns>
        private float SecondMinimumValue(float[,] towns, int i)
        {
            float first = Restriction;
            float second = Restriction;

            for (int j = 0; j < _NumberOfTown; j++)
            {
                if (i == j)
                {
                    continue;
                }

                if (towns[i, j] <= first)
                {
                    second = first;
                    first = towns[i, j];
                }
                else if (towns[i, j] <= second && towns[i, j] != first)
                {
                    second = towns[i, j];
                }
            }
            return second;
        }

        /// <summary>
        /// Rekurenyjne rozwiązanie problemu
        /// </summary>
        /// <param name="towns">Macierz miast</param>
        /// <param name="currentLowerBound">Aktualne Dolne ograniczenie (LB)</param>
        /// <param name="currentWeight">Aktualna odległość</param>
        /// <param name="level">Poziom drzewa</param>
        /// <param name="currentPath">Dotychczasowe rozwiązanie problemu</param>
        private void TSPRecursion(float[,] towns, float currentLowerBound, float currentWeight, int level, int[] currentPath)
        {
            if (level == _NumberOfTown)
            {
                //Sprawdzamy czy jest mozliwość porotu do wierzchołka startowego
                if (towns[currentPath[level - 1], currentPath[0]] != 0)
                {
                    // Dodanie kosztu drogi powrotnej do dotychczasowego kosztu 
                    float curr_res = currentWeight + towns[currentPath[level - 1], currentPath[0]];

                    //Aktualizacja najlpeszego wyniku w przypadku znalezienia lepszego rozwiazania 
                    if (curr_res < ShortestPath)
                    {
                        CopyFinalPath(currentPath);
                        ShortestPath = curr_res;
                    }
                }
                return;
            }

            // Budowanie drzewa 
            // Przejścia przez wierzchołki grafu
            for (int i = 0; i < _NumberOfTown; i++)
            {
                if (towns[currentPath[level - 1], i] != 0 && _Visited[i] == false)
                {
                    // Aktualne dolne ograniczeniew w celu ewentualnego powrotu do wyższego poziomu
                    float temp = currentLowerBound;
                    currentWeight += towns[currentPath[level - 1], i];


                    if (level == 1)
                    {
                        currentLowerBound -= ((FirstMinimumValue(towns, currentPath[level - 1]) + FirstMinimumValue(towns, i)) / 2);
                    }
                    else
                    {
                        currentLowerBound -= ((SecondMinimumValue(towns, currentPath[level - 1]) + FirstMinimumValue(towns, i)) / 2);

                    }
                    // Czy opłaca się sprawdzać kolejne poziomy drzewa 
                    if ((currentLowerBound + currentWeight) < ShortestPath)
                    {
                        //AKTUALIZACJA 
                        currentPath[level] = i;
                        _Visited[i] = true;
                        // REKURENCJA dla kolejnego poziomu
                        TSPRecursion(towns, currentLowerBound, currentWeight, level + 1, currentPath);
                    }

                    // W przeciwnym przypadku musimy cofnąć się do wezła o wyższym poziomie 
                    currentWeight -= towns[currentPath[level - 1], i];
                    currentLowerBound = temp;

                    // Resetowanie tablicy odwiedzanych miast jakie byłby dotychczas odwiedzane 
                    _Visited = Enumerable.Repeat(false, _Visited.GetLength(0)).ToArray();
                    for (int j = 0; j <= level - 1; j++)
                    {
                        _Visited[currentPath[j]] = true;
                    }
                }
            }
        }

        /// <summary>
        /// Funkcja inicializująca zmienne w taki sposób aby rozpoczynać kolejne przeszukiwania drzewa od korzenia 
        /// </summary>
        /// <param name="towns"></param>
        public void TSP(float[,] towns)
        {
            int[] currentPath = new int[_NumberOfTown + 1];
            float currentLowerBound = 0.0F;

            // -1 oznacza, że aktualna droga jest pusta 
            currentPath = Enumerable.Repeat(-1, currentPath.GetLength(0)).ToArray();
            _Visited = Enumerable.Repeat(false, _Visited.GetLength(0)).ToArray();

            for (int i = 0; i < _NumberOfTown; i++)
            {
                currentLowerBound += (FirstMinimumValue(towns, i) + SecondMinimumValue(towns, i));
            }
            //curr_bound / 2 + 1 zaokrągla do góry potem zmienić na float-y
            currentLowerBound = (System.Convert.ToBoolean(currentLowerBound) & true) ? currentLowerBound / 2 + 1 : currentLowerBound / 2;

            //Zaczynamy od pierszego miasta :)
            _Visited[0] = true;
            currentPath[0] = 0;

            TSPRecursion(towns, currentLowerBound, 0, 1, currentPath);
        }


        /// <summary>
        /// Wyświetlanie macierzy
        /// </summary>
        public void DisplayCity()
        {
            System.Console.WriteLine();
            System.Console.WriteLine();
            for (int w = 0; w < _NumberOfTown; w++)
            {
                for (int k = 0; k < _NumberOfTown; k++)
                {
                    if (_Towns[w, k] > 99)
                    {
                        System.Console.Write(_Towns[w, k] + " ");
                    }
                    if (_Towns[w, k] > 9 && _Towns[w, k] < 100)
                    {
                        System.Console.Write(_Towns[w, k] + "  ");
                    }
                    if (_Towns[w, k] <=9)
                    {
                        System.Console.Write(_Towns[w, k] + "   ");
                    }
                }
                System.Console.WriteLine();
            }
            System.Console.WriteLine();
            System.Console.WriteLine();
        }
        /// <summary>
        ///  Wyświetlanie Minimanego kosztu
        /// </summary>
        public void DisplayShortestPath()
        {
            Console.WriteLine("Minimum cost: " + ShortestPath);
        }
        /// <summary>
        /// Wyświetlanie ścieżki problemu komiwojażera
        /// </summary>
        public void DisplayPath()
        {
            Console.WriteLine("Path Taken: ");

            for (int i = 0; i <= _NumberOfTown; i++)
            {
                Console.Write(" -> " + FinalPath[i]);
            }
            Console.WriteLine();
        }

    }
}


