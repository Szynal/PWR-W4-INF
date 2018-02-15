using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.IO;
using System.Text;
using System.Windows.Forms;

namespace TSP
{
    public partial class MainForm : Form
    {
        private bool randomTSP;
        private double[] X;
        private double[] Y;
        private int N, seed;
        private Algorithms algoritm = new Algorithms();
        private List<City> lCity;

        public MainForm()
        {
            InitializeComponent();
            comboBoxMethods.SelectedIndex = 0;
            checkBoxRandom.Enabled = true;
            checkBoxRandom.Checked = true;
            buttonOpenFile.Enabled = false;
            buttonClear.Enabled = false;
        }

        private void ButtonOpenFile_Click(object sender, EventArgs e)
        {
            openFileDialog1.FileName = "";

            if (openFileDialog1.ShowDialog() == DialogResult.OK)
            {
                int number = 0;
                StreamReader sr = new StreamReader(
                    openFileDialog1.FileName);

                string line = sr.ReadLine();

                while (!sr.EndOfStream)
                {
                    if (line.Contains("DIMENSION"))
                    {
                        StringBuilder sb = new StringBuilder();

                        for (int i = 0; i < line.Length; i++)
                        {
                            char c = line[i];

                            if (c >= '0' && c <= '9')
                                sb.Append(c);
                        }

                        number = int.Parse(sb.ToString());

                        break;
                    }

                    line = sr.ReadLine();
                }

                X = new double[number];
                Y = new double[number];

                int count = 0;

                line = sr.ReadLine();

                while (line != null && line.Length != 0)
                {
                    if (line[0] >= '0' && line[0] <= '9')
                    {
                        StringBuilder sb = new StringBuilder();
                        int i, j;

                        for (i = 0; i < line.Length; i++)
                        {
                            char c = line[i];

                            if (c >= '0' && c <= '9')
                                sb.Append(c);

                            else
                                break;
                        }

                        sb = new StringBuilder();

                        for (j = i + 1; j < line.Length; j++)
                        {
                            char c = line[j];

                            if (c >= '0' && c <= '9' || c == '.')
                                sb.Append(c);

                            else
                                break;
                        }

                        X[count] = double.Parse(sb.ToString());

                        sb = new StringBuilder();

                        for (i = j + 1; i < line.Length; i++)
                        {
                            char c = line[i];

                            if (c >= '0' && c <= '9' || c == '.')
                                sb.Append(c);

                            else
                                break;
                        }

                        Y[count++] = double.Parse(sb.ToString());
                    }

                    line = sr.ReadLine();
                }

                sr.Close();

                N = number;
                buttonOpenFile.Enabled = false;
                buttonSolve.Enabled = true;
                lCity = new List<City>(N);

                for (int i = 0; i < N; i++)
                    lCity.Add(new City(X[i], Y[i], i));
            }
        }

        private void PrintTime(Stopwatch sw)
        {
            TimeSpan ts = sw.Elapsed;

            textBox.Text += ts.Hours.ToString("D2") + ":";
            textBox.Text += ts.Minutes.ToString("D2") + ":";
            textBox.Text += ts.Seconds.ToString("D2") + ".";
            textBox.Text += ts.Milliseconds.ToString("D3") + "\r\n";
        }
        /// <summary>
        ///  Przycisk Rozwiazania problemu
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void ButtonSolve_Click(object sender, EventArgs e)
        {

            Stopwatch sw = new Stopwatch();
            sw.Start();
            buttonSolve.Enabled = false;
            buttonClear.Enabled = false;

            if (randomTSP)
            {
                City[] initial = algoritm.GenerateRandomInitial(N, seed); // LOsowanie miast

                lCity = new List<City>(initial);
            }

            double fS = 0; // aktualna dlugosc trasy
            double bestfS = double.MaxValue; //  Najlepsza dlugosc trasy

            int method = comboBoxMethods.SelectedIndex;
            int restarts = (int)numericUpDownResterts.Value;
            double[] fitness = null;

            City[] best;
            City[] result; // Najlepsze rozwiazanie
            City[,] chromosome = null;


            List<City> S = new List<City>(lCity); // Lista miast
            List<City> SS = null; // Kopia listy miast

            textBox.Text += N.ToString() + "\r\n";
            textBox.Text += (string)comboBoxMethods.SelectedItem + "\r\n";

            switch (method)
            {

                case 0:
                    for (int i = 0; i < restarts; i++)
                    {
                        SS = algoritm.TabuSearch(N, 1000 * N, S, out fS);
                        S = new List<City>(SS);

                        if (fS < bestfS)
                        {
                            bestfS = fS;
                            best = S.ToArray();
                        }
                    }
                    break;


                case 1:
                    for (int i = 0; i < restarts; i++)
                    {
                        result = algoritm.GeneticAlgortihm(0.10, 1.00, N, 10000 * N, 100 * N, S.ToArray(), out fS, ref chromosome, ref fitness);
                        S = new List<City>(result);

                        if (fS < bestfS)
                        {
                            bestfS = fS;
                            best = S.ToArray();
                        }
                    }

                    break;

                default:
                    if (randomTSP)
                        best = algoritm.BruteForce(N, lCity.ToArray(), out bestfS);

                    else
                        MessageBox.Show("Random must be checked", "Warning Message",
                            MessageBoxButtons.OK, MessageBoxIcon.Warning);

                    break;
            }

            textBox.Text += bestfS.ToString("F2") + "\r\n";
            sw.Stop();
            PrintTime(sw);

            if (!randomTSP)
                buttonOpenFile.Enabled = true;

            buttonSolve.Enabled = true;
            buttonClear.Enabled = true;
        }

        private void ButtonClear_Click(object sender, EventArgs e)
        {
            textBox.Text = string.Empty;
        }

        private void CheckBoxRandom_CheckedChanged(object sender, EventArgs e)
        {
            if (checkBoxRandom.Checked)
            {
                N = (int)numericUpDownN.Value;
                seed = (int)numericUpDownSeed.Value;
                randomTSP = true;
                buttonOpenFile.Enabled = false;
                buttonSolve.Enabled = true;
                numericUpDownN.Enabled = true;
                numericUpDownSeed.Enabled = true;
            }

            else
            {
                randomTSP = false;
                buttonOpenFile.Enabled = true;
                buttonSolve.Enabled = false;
                numericUpDownN.Enabled = false;
                numericUpDownSeed.Enabled = false;
            }
        }

        private void NumericUpDownN_ValueChanged(object sender, EventArgs e)
        {
            N = (int)numericUpDownN.Value;
        }

        private void comboBoxMethods_SelectedIndexChanged(object sender, EventArgs e)
        {

        }

        private void NumericUpDownSeed_ValueChanged(object sender, EventArgs e)
        {
            seed = (int)numericUpDownN.Value;
        }
    }

}

