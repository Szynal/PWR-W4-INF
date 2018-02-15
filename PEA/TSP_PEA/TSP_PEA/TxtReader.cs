using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace TSP_PEA
{
    class TxtReader
    {
        public float[,] GenerateTownFromFile(String fileName)
        {
            int numberOfTown = 17;

            StreamReader sr = new StreamReader(fileName);

            // Read the stream to a string, and write the string to the console.
            //numberOfTown = int.Parse(sr.ReadLine());
            float[,] towns = new float[numberOfTown, numberOfTown];

            string[] plik = File.ReadAllLines(fileName);
            for (int i = 0; i < numberOfTown; i++)
            {
                string[] tmp = plik[i].Split(new char[] { ' ' }, StringSplitOptions.RemoveEmptyEntries);
                for (int j = 0; j < numberOfTown; j++)
                {
                    towns[i, j] = float.Parse(tmp[j]);
                }
            }
            return towns;
        }
    }
}
