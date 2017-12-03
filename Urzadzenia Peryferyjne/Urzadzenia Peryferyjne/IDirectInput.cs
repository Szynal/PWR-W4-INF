using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using SharpDX.DirectInput;



namespace Urzadzenia_Peryferyjne
{
    public class IDirectInput
    {
        // Zadeklarowanie obiektu DirectInput
        DirectInput input;
        // Zadeklarowanie listy Joysticków
        List<Joystick> sticks;
        // Zadeklarowanie Joysticku
        Joystick joystick;
        
        // W konstruktorze definiujemy obiekt DirectInput oraz listę Joysticków
        public IDirectInput()
        {
            input = new DirectInput();
            sticks = new List<Joystick>();
        }

        // Funkcja pobierająca nazwę Joysticka
        public string GetName()
        {
            foreach (DeviceInstance device in input.GetDevices(DeviceClass.GameControl, DeviceEnumerationFlags.AttachedOnly))
            {
                // Zadeklarowanie oraz zdefiniowanie zmiennej przechowującej GUID (globally unique identifier) danego joysticka
                var joystickGuid = Guid.Empty;

                // Pętla przeszukuje Gamepady i przekazuje ich GUID do zmiennej
                foreach (var deviceInstance in input.GetDevices(DeviceType.Gamepad,
                            DeviceEnumerationFlags.AllDevices))
                    joystickGuid = deviceInstance.InstanceGuid;

                // Jeżeli nie znaleziono żadnego gamepada, następuje wyszukiwanie Joysticków
                if (joystickGuid == Guid.Empty)
                    foreach (var deviceInstance in input.GetDevices(DeviceType.Joystick,
                            DeviceEnumerationFlags.AllDevices))
                        joystickGuid = deviceInstance.InstanceGuid;

                // W przypadku braku Joysticka wyświetlany jest błąd
                if (joystickGuid == Guid.Empty)
                {
                    Console.WriteLine("Nie znaleziono joysticka");
                    Console.ReadKey();
                    Environment.Exit(1);
                }
                // Inicjalizacja Joysticka
                joystick = new Joystick(input, joystickGuid);
                if (joystick != null)
                {
                    // Ustawienie buffora stanów
                    joystick.Properties.BufferSize = 10;
                    // Zwrócenie nazwy Joysticka
                    return joystick.Information.ProductName.ToString();

                }

                return "";
            }
            return "";
        }

        // Funkcja pobiera obecny stan Joysticka
        public string GetStateString()
        {
            try
            {
                // Uzyskanie dostępu do urządzenia wejścia
                joystick.Acquire();
                // Pobranie danych z urządzenia
                joystick.Poll();
                var state = joystick.GetCurrentState();
                // Zwrócenie stanu
                return state.ToString() + Environment.NewLine;
            } catch (Exception err)
            {
                return err.Message;
            }

        }

        // Funkcja ta zwraca informację o kierunku w którym wychylony jest drążek oraz informację czy przycisk fire jest wciśnięty
        public int[] directions()
        {
            try
            {
                // Uzyskanie dostępu do Joysticka
                joystick.Acquire();
                // Pobranie informacji o stanie Joysticka
                joystick.Poll();
                var state = joystick.GetCurrentState();
                // Przypisanie wychylenia drążka do zmiennych
                var stickPositionX = state.X;
                var stickPositionY = state.Y;
                int[] returnValue = new int[3];
                // W zależności od wartości stanu, wybierany jest odpowiedni kierunek
                if (stickPositionX > 45000)
                {
                    returnValue[0] = 1;
                }
                else
                    if (stickPositionX < 32000)
                {
                    returnValue[0] = -1;
                }
                else
                    returnValue[0] = 0;
                if (stickPositionY < 27000)
                {
                    returnValue[1] = 1;
                }
                else
                    if (stickPositionY > 37000)
                {
                    returnValue[1] = -1;
                }
                else
                    returnValue[1] = 0;
                if (state.Buttons[0] == true)
                    returnValue[2] = 1;
                else
                    returnValue[2] = 0;
                return returnValue;
                
            }
            catch (Exception err)
            {
                return null;
            }
        }
    }

}


