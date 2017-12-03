using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using PCSC;
using PCSC.Iso7816;

namespace Urzadzenia_Peryferyjne
{
    public partial class Form : System.Windows.Forms.Form
    {
        public Form()
        {
            InitializeComponent();
        }
        IDirectInput directInput;
        PrinterLPT printer;
        System.Drawing.Graphics formGraphics;
        // Funkcja tworząca sesje     
        private void ButtonPowerOnCard_Click(object sender, EventArgs e)
        {
            // Utworzenie sesji/kontekstu
            var contextFactory = ContextFactory.Instance;

            using (var context = contextFactory.Establish(SCardScope.System))
            {
                // Uzyskanie nazw czytnika
                var readerNames = context.GetReaders();

                if (NoReaderFound(readerNames))
                {
                    MessageBox.Show("Nie wykryto urządzenia.");
                    return;
                }
                // Wywołanie funkcji wyświetlającej ATR
                DisplayAtrs(context, readerNames);
            }
        }

        // Pobranie ATR danej karty
        private void DisplayAtrs(ISCardContext context, IEnumerable<string> readerNames)
        {
            foreach (var readerName in readerNames)
            {
                using (var reader = new SCardReader(context))
                {
                    if (!ConnectReader(reader, readerName))
                    {
                        continue;
                    }
                    // Wyświetlanie ATR
                    DisplayCardAtr(reader);
                    // Rozłączenie z czytnikiem
                    reader.Disconnect(SCardReaderDisposition.Leave);
                }
            }
        }

        // Połączenie z czytnikiem
        private static bool ConnectReader(ISCardReader reader, string name)
        {
            // Przypisanie połączenia do zmiennej        
            var rc = reader.Connect(name, SCardShareMode.Shared, SCardProtocol.Any);
            // Jeżeli połączono z sukcesem
            if (rc == SCardError.Success)
            {
                return true;
            }
            // Jeżeli nie udało się połączyć
            return false;
        }

        // Funkcja wyświetlająca ATR karty znajdującej się w czytniku
        private void DisplayCardAtr(ISCardReader reader)
        {
            byte[] atr;
            // Pobranie ATR
            var rc = reader.GetAttrib(SCardAttribute.AtrString, out atr);
            // W przypadku niepowodzenia
            if (rc != SCardError.Success)
            {

                textBoxATREquals.Text = "Nie udało się połączyć. " + SCardHelper.StringifyError(rc);
            }
            else
            {
                textBoxATREquals.Text = BitConverter.ToString(atr ?? new byte[] { });
            }
        }

        // Sprawdza dostępność czytników
        private static bool NoReaderFound(ICollection<string> readerNames)
        {
            return readerNames == null || readerNames.Count < 1;
        }

        // Obsługa przycisku wysłania komend
        private void ButtonTransmitCommand_Click(object sender, EventArgs e)
        {
            // Utworzenie sesji połączenia 
            var contextFactory = ContextFactory.Instance;
            using (var context = contextFactory.Establish(SCardScope.System))
            {
                // Pobranie nazw czytników
                var readerNames = context.GetReaders();
                if (NoReaderFound(readerNames))
                {
                    MessageBox.Show("Brak podłączonych urządzeń");
                    return;
                }

                // Przypisanie nazwy czytnika
                var readerName = readerNames[0];
                if (readerName == null)
                {
                    return;
                }

                using (var rfidReader = new SCardReader(context))
                {
                    //przypisanie połaczenia za pośrednictwerm RFID 
                    var sc = rfidReader.Connect(readerName, SCardShareMode.Shared, SCardProtocol.Any);
                    if (sc != SCardError.Success) // W przypadku niepowodzenia
                    {
                        MessageBox.Show("Brak połączenia" +
                            readerName +
                            SCardHelper.StringifyError(sc));

                        return;
                    }
                    // pobranie komendy z pola tekstowego
                    byte[] command = Encoding.ASCII.GetBytes(textBoxCommand.Text);

                    sc = rfidReader.BeginTransaction();
                    if (sc != SCardError.Success)
                    {
                        MessageBox.Show("");
                        return;
                    }

                    var receivePci = new SCardPCI(); // IO zwraca protocol control information.
                    var sendPci = SCardPCI.GetPci(rfidReader.ActiveProtocol);
                    var receiveBuffer = new byte[256];

                    sc = rfidReader.Transmit(
                        sendPci, // Protocol Control Information (T0, T1 or Raw)
                        command, // command APDU
                        receivePci, // returning Protocol Control Information
                        ref receiveBuffer); // data buffer

                    if (sc != SCardError.Success)
                    {
                        MessageBox.Show("BŁĄD: " + SCardHelper.StringifyError(sc));
                    }
                    // OPOWIEDZ APDU
                    var responseApdu = new ResponseApdu(receiveBuffer, IsoCase.Case2Short, rfidReader.ActiveProtocol);

                    textBoxAnswer.Text = responseApdu.SW1.ToString() + " " + responseApdu.SW2 + (responseApdu.HasData ? BitConverter.ToString(responseApdu.GetData()) : "");

                    rfidReader.EndTransaction(SCardReaderDisposition.Leave);
                    rfidReader.Disconnect(SCardReaderDisposition.Reset);


                }
            }
        }

        private void tabPage2_Click(object sender, EventArgs e)
        {

        }

        private void buttonLab2Init_Click(object sender, EventArgs e)
        {
            directInput = new IDirectInput();
            formGraphics = tabPage2.CreateGraphics();
            labelDevice.Text = directInput.GetName();
            timer1.Interval = 10;
            timer1.Start();
        }

        private void button1_Click(object sender, EventArgs e)
        {

        }

        private void timer1_Tick(object sender, EventArgs e)
        {

            labelState.Text = directInput.GetStateString();
            if (directInput.directions()[1] == 1)
            {
                // góra
                Console.WriteLine("góra");
                Cursor.Position = new Point(Cursor.Position.X, Cursor.Position.Y - 10);
            }
            if (directInput.directions()[1] == -1)
            {
                // dół
                Console.WriteLine("dół");
                Cursor.Position = new Point(Cursor.Position.X, Cursor.Position.Y + 10);
            }
            if (directInput.directions()[0] == -1)
            {
                //lewo
                Console.WriteLine("lewo");
                Cursor.Position = new Point(Cursor.Position.X - 10, Cursor.Position.Y);
            }
            if (directInput.directions()[0] == 1)
            {
                //prawo
                Console.WriteLine("prawo");
                Cursor.Position = new Point(Cursor.Position.X + 10, Cursor.Position.Y);
            }
            if (directInput.directions()[2] == 1)
            {
                Console.WriteLine("rysuje");
                formGraphics.FillRectangle(Brushes.Black, Cursor.Position.X - this.Location.X, Cursor.Position.Y - this.Location.Y - 20, 10, 10);
            }


        }

        private void tabPageMainPage_Click(object sender, EventArgs e)
        {

        }

        private void buttonPrint_Click(object sender, EventArgs e)
        {
            //printer.WriteRaster();
            bool vertical = true;
            if (radioButtonVertical.Checked)
                vertical = true;
            if (radioButtonHorizontal.Checked)
                vertical = false;
            printer.WriteText(textBoxTextToWrite.Text, comboBox1.SelectedIndex, comboBoxSize.SelectedIndex, 7, vertical, radioButtonBold.Checked, radioButtonItalic.Checked, radioButtonUnderline.Checked);
        }

        private void buttonInitPrinter_Click(object sender, EventArgs e)
        {
            printer = new PrinterLPT();
            printer.InitLpt();
        }

        private void radioButtonVertical_CheckedChanged(object sender, EventArgs e)
        {
            if (radioButtonVertical.Checked && radioButtonHorizontal.Checked)
            {
                radioButtonHorizontal.Checked = false;
            }
        }

        private void radioButtonHorizontal_CheckedChanged(object sender, EventArgs e)
        {
            if (radioButtonVertical.Checked && radioButtonHorizontal.Checked)
            {
                radioButtonVertical.Checked = false;
            }
        }
    }
}
