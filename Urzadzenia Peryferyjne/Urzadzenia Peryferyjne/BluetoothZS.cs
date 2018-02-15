using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using InTheHand;
using InTheHand.Net.Bluetooth;
using InTheHand.Net.Ports;
using InTheHand.Net.Sockets;
using InTheHand.Net;

namespace Urzadzenia_Peryferyjne
{
    class BluetoothZS
    {

        // Klient bluetooth - zapewnia połączenie z klientem
        BluetoothClient bc; 
        // Tablica przechowująca informacje o dostępnych urządzeniach
        BluetoothDeviceInfo[] info; 

        public BluetoothZS()
        {
            // Inicjalizacja zmiennych
            bc = new BluetoothClient(); 
            info = null;
        }

        // Funkcja ta wyszukuje dostępne adaptery
        public void FindAdapters(ListBox adapters)
        {
            // Zapamiętanie wszystkich dostępnych adapterów
            BluetoothRadio[] radios = BluetoothRadio.AllRadios; 
            // Sprawdzenie stanów adapterów
            foreach (BluetoothRadio radio in radios)
            {
                // Jeżeli znaleziono adapter nieobsługiwany przez bibliotekę następuje przerwanie operacji
                if (!BluetoothRadio.IsSupported)
                {
                    MessageBox.Show("Wykryto nieobsługiwany adapter!");
                    break;
                }
                // Jeżeli znaleziony adapter ma wyłączone bluetooth, to zostaje ono włączone, lecz nie może być odkryte przez inne urządzenia
                if (radio.Mode == RadioMode.PowerOff)
                    radio.Mode = RadioMode.Connectable; 
                adapters.Items.Add(radio.Name.ToString() + "  " + radio.Mode.ToString());
            }
        }

        // Funkcja ta wyszukuje urządzenia widoczne dla danego adaptera
        public void FindDevices(ListBox devices)
        {
            // Pobranie informacji o dostępnych urządzeniach
            info = bc.DiscoverDevices(999);
            foreach (BluetoothDeviceInfo device in info)
            {
                devices.Items.Add(device.DeviceName + " - " + device.DeviceAddress); 
            }
        }

        private void HandleRequests(object that, BluetoothWin32AuthenticationEventArgs e)
        {
            e.Confirm = true;
        }

        // Funkcja która łączy adapter z urządzeniem bluetooth
        public void Connect(ListBox devices)
        {
            // Pobranie informacji o wybranym urządzeniu
            BluetoothDeviceInfo device = info[devices.SelectedIndex];
            // Aktualizacja nazwy urządzenia
            device.Update(); 
            // Odświeżenie informacji o urządzeniu
            device.Refresh(); 
            // Przygotowanie urządzenia do wysyłania danych binarnych z wykorzystaniem protokołu OBEX
            device.SetServiceState(BluetoothService.ObexObjectPush, true);

            EventHandler<BluetoothWin32AuthenticationEventArgs> handler = new EventHandler<BluetoothWin32AuthenticationEventArgs>(HandleRequests);
            BluetoothWin32Authentication auth = new BluetoothWin32Authentication(handler);
            // Jeżeli urządzenie nie jest sparowane zostaje wysłana prośba o sparowanie, musi zostać ona potwierdzona podaniem pinu - 123456
            if (!device.Authenticated)
            {
                BluetoothSecurity.PairRequest(device.DeviceAddress, "123456"); 
            }
        }

        // Funkcja ta wysyła plik do urządzenia bluetooth
        public void SendFile(ListBox devices)
        {
            // Pobranie informacji o wybranym urządzeniu
            BluetoothDeviceInfo device = info[devices.SelectedIndex];
            // Aktualizacja nazwy urządzenia
            device.Update();
            // Odświeżenie informacji o urządzeniu
            device.Refresh();
            // Przygotowanie urządzenia do wysyłania danych binarnych z wykorzystaniem protokołu OBEX
            device.SetServiceState(BluetoothService.ObexObjectPush, true);

            OpenFileDialog window = new OpenFileDialog();
            window.ShowDialog(); //wyświetlenie okienka do wyboru pliku
            var file = window.FileName;
            // Ustalenie ścieżki pliku 
            var uri = new Uri("obex://" + device.DeviceAddress + "/" + file); 
            // Stworzenie zapytania ze ścieżką pliku
            var request = new ObexWebRequest(uri); 
            // Przeczytanie zawartości pliku
            request.ReadFile(file); 
            // Wysłanie zapytania oraz pobranie odpowiedzi
            var response = request.GetResponse() as ObexWebResponse; 
            // Wyświetlenie komunikatu czy przesyłanie pliku przebiegło pomyślnie
            if (response.StatusCode.ToString() == "OK, Final")
            {
                MessageBox.Show("Plik przesłany pomyślnie");
            }
            else
            {
                MessageBox.Show("Błąd przesyłania pliku");
            }
            // Zakończenie wymiany informacji z urządzeniem
            response.Close();
        }
    }
}
