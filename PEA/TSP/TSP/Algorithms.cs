using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace TSP
{
    class Algorithms
    {
        private Random random = new Random();

        public double TourLength(int N, List<City> lCity)
        {
            return TourLength(N, lCity.ToArray());
        }

        public double TourLength(int N, City[] city)
        {
            double length = 0;

            for (int i = 0; i < N - 1; i++)
                length += city[i].Distance(city[i + 1]);

            length += city[N - 1].Distance(city[0]);
            return length;
        }

        private void Swap(int N, ref List<City> lCity)
        {
            int i = random.Next(N);
            int j = random.Next(N);
            City t = lCity[i];

            lCity[i] = lCity[j];
            lCity[j] = t;
        }

        private void Swap(int N, ref City[] city)
        {
            int i = random.Next(N);
            int j = random.Next(N);
            City t = city[i];

            city[i] = city[j];
            city[j] = t;
        }
        /// <summary>
        /// Losowanie Miast 
        /// </summary>
        /// <param name="N">Illosc miast</param>
        /// <param name="seed"></param>
        /// <returns> Lista Miast </returns>
        public City[] GenerateRandomInitial(int N, int seed)
        {
            double cMin = 1000, cMax = 9999;
            double[] x = new double[N];
            City[] initial = new City[N];

            random = new Random(seed);

            // generate unique x-coordinates
            // DO WYWALENIA 
            x[0] = random.NextDouble() * cMax + cMin;

            for (int i = 1; i < N; i++)
            {
                bool done = false;

                while (!done)
                {
                    bool found = false;

                    x[i] = random.NextDouble() * cMax + cMin;

                    for (int j = 0; j < i; j++)
                        found = x[i] == x[j];

                    done = !found;
                }
            }
            // DO WYWALENIA  LOSOWANIE I POROWNYWANIE X
            for (int i = 0; i < N; i++)
                initial[i] = new City(x[i], random.NextDouble() * cMax + cMin, i);

            random = new Random();

            return initial;
        }

        public City[] BruteForce(int N, City[] initial, out double fS)
        {
            fS = 0;

            if (N <= 11)
            {
                PermutationGenerator gen = new PermutationGenerator();

                return gen.Generate(N, initial, out fS);
            }

            return null;
        }

        // Tabu search
        public List<City> TabuSearch(int N, int maxIters, List<City> S, out double fS)
        {
            List<int> M = new List<int>();

            for (
                int i = 0; i < N; i++)
                M.Add(0);

            int minTabu = maxIters / 10;
            int extraTabu = maxIters / 50;
            List<City> SS = new List<City>(S);
            List<City> SD = null;

            fS = TourLength(N, S);

            for (int k = 0; k < maxIters; k++)
            {
                double fD = double.MaxValue;
                int iD = 0;

                for (int i = 0; i < N; i++)
                {
                    List<City> Sp = new List<City>(SS); // tymczasowa 

                    Swap(N, ref Sp);

                    double fp = TourLength(N, Sp);

                    if (k >= M[i] || fp < fS)
                    {
                        if (fp < fD)
                        {
                            fD = fp;
                            SD = new List<City>(Sp);
                            iD = i;
                        }
                    }
                }

                M[iD] = k + minTabu + random.Next(extraTabu);

                if (fD < fS)
                {
                    SS = new List<City>(SD);
                    fS = fD;
                }
            }

            return SS;
        }

        /// <summary>
        /// Losowanie scieżki
        /// </summary>
        /// <param name="N"></param>
        /// <param name="inpCity"></param>
        /// <param name="outCity"></param>
        public void InitializeCityArray(int N, City[] inpCity, out City[] outCity)
        {
            int c, number = 0;
            bool[] used = new bool[N];

            for (int i = 0; i < N; i++)
                used[i] = false;

            outCity = new City[N];

            do
            {
                c = random.Next(N);

                if (used[c])
                    continue;

                used[c] = true;
                outCity[number++] = inpCity[c];
            } while (number < N);
        }



        // genetic algorithm (PMX: Partially - Mapped Crossover)
        /// <summary>
        /// 
        /// </summary>
        /// <param name="N">Liczba miast</param>
        /// <param name="cp1">Losowa liczba miasta < od cp2</param>
        /// <param name="cp2">Losowa liczba miasta</param>
        /// <param name="p1">Rodzic 1</param>
        /// <param name="p2">rodzic 2</param>
        /// <param name="o1">Potomek 1</param>
        /// <param name="o2">Potomek 2</param>
        private void PMX(int N, int cp1, int cp2, int[] p1, int[] p2, int[] o1, int[] o2)
        {
            for (int i = 0; i < N; i++)
                o1[i] = o2[i] = -1;
            
         ///----------------------------------------------
         ///  Wymiana  odcinka DNA
            for (int i = cp1; i <= cp2; i++)
            {
                o1[i] = p2[i];
                o2[i] = p1[i];
            }
          ///----------------------------------------------
          ///Szukanie powtarzających chromosomów (MAPOWANIE)
            for (int i = 0; i < cp1; i++)
            {
                bool found = false;
                int t = p1[i];

                for (int j = i + 1; !found && j < N; j++)
                    found = t == o1[j];

                if (!found)
                    o1[i] = t;
            }

            for (int i = cp2 + 1; i < N; i++)
            {
                bool found = false;
                int t = p1[i];

                for (int j = 0; !found && j < N; j++)
                    found = t == o1[j];

                if (!found)
                    o1[i] = t;
            }
            ///----------------------------------------------
            List<int> used = new List<int>();
            ///----------------------------------------------
            /// SPrawdzamy co zostało przypisane 
            for (int i = 0; i < N; i++)
                if (o1[i] != -1)
                    used.Add(o1[i]);

            for (int i = 0; i < N; i++)
            {
                if (o1[i] == -1)
                {
                    int x;

                    do
                    {
                        x = random.Next(N);
                    } while (used.Contains(x));

                    o1[i] = x;
                    used.Add(x);
                }
            }

            for (int i = 0; i < cp1; i++)
            {
                bool found = false;
                int t = p2[i];

                for (int j = i + 1; !found && j < N; j++)
                    found = t == o2[j];

                if (!found)
                    o2[i] = t;
            }

            for (int i = cp2 + 1; i < N; i++)
            {
                bool found = false;
                int t = p2[i];

                for (int j = 0; !found && j < N; j++)
                    found = t == o2[j];

                if (!found)
                    o2[i] = t;
            }

            used = new List<int>();

            for (int i = 0; i < N; i++)
                if (o2[i] != -1)
                    used.Add(o2[i]);

            for (int i = 0; i < N; i++)
            {
                if (o2[i] == -1)
                {
                    int x;

                    do
                    {
                        x = random.Next(N);
                    } while (used.Contains(x));

                    o2[i] = x;
                    used.Add(x);
                }
            }
        }
              
        /// <summary>
        /// 
        /// </summary>
        /// <param name="crossoverRate">0.10</param>
        /// <param name="MutationRate">1.00</param>
        /// <param name="N">Liczba miast</param>
        /// <param name="maxGenerations"> 10000 * N</param>
        /// <param name="population">100 * N (ilość powstałych dzieci)</param>
        /// <param name="city"></param>
        /// <param name="minFitness"> min Fitness evaluation - najmniejsza funkcja przystosowani a</param>
        /// <param name="chromosome"> Macierz [Populacja] X [Liczbę miast] gdzie wartościami są ich (dzieci) </param>
        /// <param name="fitness"> Fitness evaluation - funkcja przystosowani</param> 
        /// <returns></returns>
        public City[] GeneticAlgortihm(double crossoverRate, double MutationRate, int N, int maxGenerations, int population, City[] city, out double minFitness, ref City[,] chromosome, ref double[] fitness)
        {
            City[] best = new City[N];
            City[] child = null;

            minFitness = double.MaxValue;

            if (fitness == null)
            {
                fitness = new double[population];
                chromosome = new City[population, N];

                // calculate initial population and fitnesses

                for (int i = 0; i < population; i++)
                {
                    //Losowanie scieżki (dziecka)
                    InitializeCityArray(N, city, out child);

                    for (int j = 0; j < N; j++)
                        chromosome[i, j] = child[j];

                    fitness[i] = TourLength(N, child); // Wartosć ścieżki 

                    if (fitness[i] < minFitness)
                    {
                        minFitness = fitness[i];

                        //Wybieramy potencjalnie najlepsze dziecko
                        for (int j = 0; j < N; j++)

                            best[j] = child[j];
                    }
                }
            }

            // W celu uzyskania bardziej wairygodnegu wyniku możemy powtarzać x razy algorymt
            else
            {
                for (int i = 0; i < population / 2; i++)
                {
                    InitializeCityArray(N, city, out child);

                    for (int j = 0; j < N; j++)
                        chromosome[i, j] = child[j];

                    fitness[i] = TourLength(N, child);
                }

                for (int i = 0; i < population; i++)
                {
                    if (fitness[i] < minFitness)
                    {
                        minFitness = fitness[i];

                        for (int j = 0; j < N; j++)
                            best[j] = chromosome[i, j];
                    }
                }
            }

            for (int g = 0; g < maxGenerations; g++)
            {
                bool cross = false, mutate = false;

                // tournament selection with a tounament size of 2

                int parent1 = random.Next(population);
                int parent2 = random.Next(population);
                int parentA = fitness[parent1] < fitness[parent2] ? parent1 : parent2;

                parent1 = random.Next(population);
                parent2 = random.Next(population);

                int parentB = fitness[parent1] < fitness[parent2] ? parent1 : parent2;

                if (random.NextDouble() < crossoverRate)
                {
                    // crossover

                    int i;
                    int[] p1 = new int[N];
                    int[] p2 = new int[N];
                    int[] o1 = new int[N];
                    int[] o2 = new int[N];

                    for (i = 0; i < N; i++)
                    {
                        p1[i] = chromosome[parent1, i].Id;
                        p2[i] = chromosome[parent2, i].Id;
                    }

                    int cp1 = -1, cp2 = -1;

                    do
                    {
                        cp1 = random.Next(N);
                        cp2 = random.Next(N);
                    } while (cp1 == cp2 || cp1 > cp2);

                    PMX(N, cp1, cp2, p1, p2, o1, o2);

                    City[] o1City = new City[N];
                    City[] o2City = new City[N];

                    for (i = 0; i < N; i++)
                    {
                        o1City[i] = chromosome[parent1, o1[i]];
                        o2City[i] = chromosome[parent2, o2[i]];
                    }

                    double o1Fitness = TourLength(N, o1City);
                    double o2Fitness = TourLength(N, o2City);

                    if (o1Fitness < fitness[parent1])
                        for (i = 0; i < N; i++)
                            chromosome[parent1, i] = o1City[i];

                    if (o2Fitness < fitness[parent2])
                        for (i = 0; i < N; i++)
                            chromosome[parent2, i] = o2City[i];

                    for (int p = 0; p < population; p++)
                    {
                        if (fitness[p] < minFitness)
                        {
                            minFitness = fitness[p];

                            for (int n = 0; n < N; n++)
                                best[n] = chromosome[p, n];
                        }
                    }
                }

                else
                    for (int i = 0; i < N; i++)
                        child[i] = chromosome[parentA, i];

                if (random.NextDouble() < MutationRate)
                {
                    // mutate the child

                    Swap(N, ref child);
                    mutate = true;
                }

                if (!cross && !mutate)
                    continue;

                // replace maximum fitness with child

                double childFitness = TourLength(N, child);
                double maxFitness = int.MinValue;

                for (int i = 0; i < population; i++)
                    if (fitness[i] > maxFitness)
                        maxFitness = fitness[i];

                List<int> indices = new List<int>(); // Lista indeksów 

                for (int i = 0; i < population; i++)
                    if (fitness[i] == maxFitness)
                        indices.Add(i);

                int index = indices[random.Next(indices.Count)];

                for (int i = 0; i < N; i++)
                    chromosome[index, i] = child[i];

                fitness[index] = childFitness;

                if (childFitness < minFitness)
                {
                    minFitness = childFitness;

                    for (int i = 0; i < N; i++)
                        best[i] = child[i];
                }
            }

            return best;
        }



    }
}
