using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Xml;
using System.IO;
using System.Text.RegularExpressions;
/// <summary>
///    AUTOR: PAWEŁ SZYNAL 
///    NR ALBUMU: 226026
///    PRZEDMIOT: Projektowanie efektywnych algorytmów (PEA)
///    PROWADZĄCY: dr inż. Łukasz Jeleń
/// </summary>
namespace TSP_PEA
{
    public class XMLReader
    {/// <summary>
     /// 
     ///  Funkcja wczytuje miasta z plików XML  pobranych ze strony TSPLIB (TSPLIB is a library of sample instances for the TSP (and related problems) from various sources and of various types.)
     ///  http://comopt.ifi.uni-heidelberg.de/software/TSPLIB95/
     /// </summary>
     /// <param name="fileName"></param>
     /// <returns></returns>
        public float[,] GenerateTownFromFile(String fileName)
        {
            XmlTextReader reader = new XmlTextReader(fileName);

            int resultString = 0;
            int numberOfTown = 0;

            Console.Write("\n\n");

            try
            {
                reader.Read();
            }
            catch (System.IO.FileNotFoundException)
            {
                Console.WriteLine("Nie odnaleziono pliku o podanej nazwie");
            }
            //
            while (reader.Read() && reader.Name != "graph")
            {
                switch (reader.NodeType)
                {
                    case XmlNodeType.Element: // The node is an element.
                        Console.Write(reader.Name + ": ");
                        break;
                    case XmlNodeType.Text: //Display the text in each element.
                        resultString++;
                        if (resultString == 1)
                        {
                            try
                            {
                                numberOfTown = Int32.Parse(Regex.Match(reader.Value, @"\d+").Value); // Z nazwy pliku pobieram liczbe miast
                            }
                            catch (System.FormatException)
                            {
                                Console.WriteLine("Nieprawidłowy format ciagu wejsciowego.");
                            }
                        }
                        Console.WriteLine(reader.Value + "\n\n");
                        break;
                }
            }

            float[,] towns = new float[numberOfTown, numberOfTown];

            int vertex = -1;
            int edge = 0;

            while (reader.Read())
            {
                if (reader.Name == "vertex" && reader.NodeType != XmlNodeType.EndElement)
                {
                    vertex++;
                    edge = -1;
                    if (vertex == 0) edge = 0;
                }
                if (reader.Name == "edge" && reader.NodeType != XmlNodeType.EndElement)
                {
                    edge++;
                }

                if (edge == vertex && edge != 0 && vertex != 0)
                {
                    towns[edge, vertex] = 0;
                    edge++;
                }
                if (reader.NodeType == XmlNodeType.Element)
                {
                    if (reader.HasAttributes && edge != vertex)
                    {
                        towns[edge, vertex] = float.Parse(reader.GetAttribute("cost"), System.Globalization.CultureInfo.InvariantCulture);
                    }
                }
            }
            return towns;
        }


    }
}
