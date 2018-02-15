using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using System.IO.Ports;
using System.Diagnostics;
using System.Threading;

namespace Urzadzenia_Peryferyjne
{
    class GPSUP
    {
        bool stop = false;
        char[] receivedData;
        char[] time, latitude, longitude, satellitesNumber, deg1, deg2, status, horizontalDilution, altitude;
        string state;
        int i, minLatitude, minLongitude, latitudeA, longitudeA;
        double latit, longit;
        System.Windows.Forms.TextBox dataTextBox;
        System.Windows.Forms.Form forms;

        delegate void SetTextCallback(string text);
        public static SerialPort serialPort;

        public GPSUP(System.Windows.Forms.TextBox textBox, System.Windows.Forms.Form forms)
        {
            i = 0;
            receivedData = new char[512];
            time = new char[512];
            latitude = new char[512];
            longitude = new char[512];
            satellitesNumber = new char[512];
            deg1 = new char[512];
            deg2 = new char[512];
            status = new char[512];
            horizontalDilution = new char[512];
            altitude = new char[512];
            dataTextBox = textBox;
            this.forms = forms;

        }

        //Funkcja kopiująca poszczególne dane z wczytanej sekwencji do tablic
        void MoveData(char[] data, char[] newArray)
        {
            i++;
            int j = 0;
            for (; data[i] != ','; i++)
                newArray[j++] = data[i];
            //Znak końca łańcucha znaków
            newArray[j] = '\0';
        }

        //Otwarcie portu
        public void OpenConnection()
        {
            if (serialPort != null)
            {
                if (!serialPort.IsOpen)
                {
                    serialPort.Open();
                    serialPort.ReadTimeout = 100000;
                    serialPort.WriteTimeout = 10000;
                }
            }
        }

        //Obsługa danych przychodzących od odbiornika GPS
        private void DataReceived(object sender, SerialDataReceivedEventArgs e)
        {
            serialPort.Read(receivedData, 0, 512);
            StringBuilder stringBuilder = new StringBuilder();

            i = 0;
            while (true)
            {   //Wykrycie poszukiwanej sekwencji
                if (receivedData[i] == 'G' && receivedData[i + 1] == 'G' && receivedData[i + 2] == 'A')
                {
                    i += 3;
                    MoveData(receivedData, time);
                    MoveData(receivedData, latitude);
                    MoveData(receivedData, deg1);
                    MoveData(receivedData, longitude);
                    MoveData(receivedData, deg2);
                    MoveData(receivedData, status);
                    MoveData(receivedData, satellitesNumber);
                    MoveData(receivedData, horizontalDilution);
                    MoveData(receivedData, altitude);
                    break;
                }
                if (i == 509)
                    break;
                i++;

            }

            String strLatitude = new string(latitude);
            String strDeg1 = new string(deg1);
            String strLongitude = new string(longitude);
            String strDeg2 = new string(deg2);
            String strStatus = new string(status);
            String strSatellitesNumber = new string(satellitesNumber);
            String strAltitude = new string(altitude);

            stringBuilder.Append("Czas: " + time[0].ToString() + time[1].ToString() + ":" + time[2].ToString() + time[3].ToString() + ":" + time[4].ToString() + time[5].ToString());
            stringBuilder.Append(Environment.NewLine);
            stringBuilder.Append("Szerokość: " + strLatitude + " " + strDeg1);
            stringBuilder.Append(Environment.NewLine);
            stringBuilder.Append("Dlugość " + strLongitude + " " + strDeg2);
            stringBuilder.Append(Environment.NewLine);
            int statusInt = Int32.Parse(strStatus);
            if (statusInt == 1)
                state = " - fix GPS";
            if (statusInt == 0)
                state = " - brak fixa";

            stringBuilder.Append("Status fixa: " + strStatus + " " + state);
            stringBuilder.Append(Environment.NewLine);
            stringBuilder.Append("Liczba Satelitów: " + strSatellitesNumber);
            stringBuilder.Append(Environment.NewLine);
            stringBuilder.Append("Wysokość (m n.p.m.): " + strAltitude);
            stringBuilder.Append(Environment.NewLine);
            latit = Double.Parse(strLatitude, System.Globalization.CultureInfo.InvariantCulture);
            longit = Double.Parse(strLongitude, System.Globalization.CultureInfo.InvariantCulture);
            minLatitude = (int)Math.Floor(latit);
            minLongitude = (int)Math.Floor(longit);
            latitudeA = minLatitude;
            longitudeA = minLongitude;
            minLatitude %= 100;
            latitudeA /= 100;
            minLongitude %= 100;
            longitudeA /= 100;

            stringBuilder.Append("Szerokość geograficzna: " + latitudeA + " stopni " + minLatitude + "'" + (latit - (int)latit) * 60 + "\"");
            stringBuilder.Append(Environment.NewLine);
            stringBuilder.Append("Dlugosc geograficzna: " + longitudeA + " stopni " + minLongitude + "'" + (longit - (int)longit) * 60 + "\"");
            stringBuilder.Append(Environment.NewLine);

            if (stringBuilder.ToString() != null)
            {
                forms.BeginInvoke(new SetTextCallback((string text) => { dataTextBox.Text = text; }), new object[] { stringBuilder.ToString() });
            }
        }

        public void Start()
        {
            receivedData = new char[512];

            serialPort = new SerialPort("COM3", 9600, Parity.None, 8, StopBits.One);
            serialPort.Handshake = Handshake.None;
            serialPort.DataReceived += new SerialDataReceivedEventHandler(DataReceived);
            OpenConnection();
            stop = false;
        }

        public void Stop()
        {
            stop = true;
        }

        public void ShowLocation()
        {
            System.Globalization.CultureInfo customCulture = (System.Globalization.CultureInfo)System.Threading.Thread.CurrentThread.CurrentCulture.Clone();
            customCulture.NumberFormat.NumberDecimalSeparator = ".";

            System.Threading.Thread.CurrentThread.CurrentCulture = customCulture;
            //adres internetowy google maps
            string google = "https://www.google.pl/maps/@";
            double testLatitude = (((latit - (int)latit) + minLatitude) / 60) + latitudeA;
            google += testLatitude;
            google += ",";
            double testLongitude = (((longit - (int)longit) + minLongitude) / 60) + longitudeA;
            google += testLongitude;
            google += ",20z";
            Process.Start("chrome", google);
        }
    }
}
