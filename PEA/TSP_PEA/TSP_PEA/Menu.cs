using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Reflection;
using System.Text;
using System.Text.RegularExpressions;
using System.Threading.Tasks;
/// <summary>
///    AUTOR: PAWEŁ SZYNAL 
///    NR ALBUMU: 226026
///    PRZEDMIOT: Projektowanie efektywnych algorytmów (PEA)
///    PROWADZĄCY: dr inż. Łukasz Jeleń
/// </summary>
namespace TSP_PEA
{
    class Menu
    {
        int GetInput()
        {
            int choice;
            return choice = Console.Read();
        }

        void DisplayMainMenu()
        {
            System.Console.WriteLine("Main Menu\n");
            System.Console.WriteLine("AUTOR: PAWEŁ SZYNAL");
            System.Console.WriteLine("NR ALBUMU: 226026");
            System.Console.WriteLine("PRZEDMIOT: Projektowanie efektywnych algorytmów (PEA)");
            System.Console.WriteLine("PROWADZĄCY: dr inż. Łukasz Jeleń\n\n");
            System.Console.WriteLine("1 - Problem Komiwojazera");
            System.Console.WriteLine("2 - Quit\n");
            System.Console.WriteLine("Selection: ");
        }

        void DisplayLoadData()
        {
            System.Console.WriteLine("Wczytaj dane");
            System.Console.WriteLine("1 - Miasto z pliku");
            System.Console.WriteLine("2 - Wygeneruj losowo miasto");
            System.Console.WriteLine("3 - Powrot");
            System.Console.WriteLine("Selection: ");
        }


        void DisplayTravellingSalesman()
        {
            System.Console.WriteLine("Problem Komiwojazera");
            System.Console.WriteLine("1 - Metoda podziału i ograniczeń dla problemu komiwojazera.");
            System.Console.WriteLine("2 - Powrot");
            System.Console.WriteLine("Selection: ");
        }

        public void MainMenu()
        {

            string choice = "";
            const string path = @"C:\Users\Pawel Szynal\Documents\Visual Studio 2017\Projects\TSP_PEA\TSP_PEA\TSPLib\";
            string fileName = "";
            XMLReader xmlReader = new XMLReader();
            ConsoleAutoStopWatch watch = new ConsoleAutoStopWatch();
            do
            {
                Console.Clear();
                DisplayMainMenu();
                choice = Console.ReadLine();
                switch (choice)
                {
                    case "1":
                        {
                            string choiceTSP;
                            do
                            {
                                Console.Clear();
                                DisplayTravellingSalesman();
                                choiceTSP = Console.ReadLine();

                                switch (choiceTSP)
                                {
                                    case "1":
                                        {
                                            string choiceLoadData;
                                            do
                                            {
                                                Console.Clear();
                                                DisplayLoadData();
                                                choiceLoadData = Console.ReadLine();

                                                switch (choiceLoadData)
                                                {
                                                    case "1":
                                                        {
                                                            Console.Clear();
                                                            Console.WriteLine("Wpisz nazwe pliku: ");
                                                            fileName = Console.ReadLine();
                                                            float[,] town = xmlReader.GenerateTownFromFile(path + fileName + ".xml");
                                                            Algorithm tsp = new Algorithm(town);
                                                            tsp.DisplayCity();

                                                            watch.RestartClock();
                                                            tsp.TSP(town);
                                                            watch.Dispose();

                                                            tsp.DisplayShortestPath();
                                                            tsp.DisplayPath();
                                                            Console.ReadKey();
                                                        }
                                                        break;
                                                    case "2":
                                                        {
                                                            Console.Clear();
                                                            int numberOftown = 0;
                                                            Console.WriteLine("Podaj ilość miast: ");
                                                            numberOftown = Int32.Parse(Regex.Match(Console.ReadLine(), @"\d+").Value);
                                                            float[,] town = TownGenerator.GenerateTown(numberOftown, 1, 100);
                                                            Algorithm tsp = new Algorithm(town);
                                                            tsp.DisplayCity();

                                                            watch.RestartClock();
                                                            tsp.TSP(town);
                                                            watch.Dispose();

                                                            tsp.DisplayShortestPath();
                                                            tsp.DisplayPath();
                                                            Console.ReadKey();
                                                        }
                                                        break;
                                                    default:
                                                        break;

                                                }
                                            } while (choiceLoadData != "3");
                                        }
                                        break;
                                    default:
                                        break;
                                }
                            } while (choiceTSP != "2");
                        }
                        break;
                    default:
                        break;
                }
            } while (choice != "2");
        }
    }
}
