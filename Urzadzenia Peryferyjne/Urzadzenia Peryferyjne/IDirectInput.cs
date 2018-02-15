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
        DirectInput input;
        List<Joystick> sticks;
        Joystick joystick;
        public IDirectInput()
        {
            input = new DirectInput();
            sticks = new List<Joystick>();
        }
        public string GetName()
        {
            foreach (DeviceInstance device in input.GetDevices(DeviceClass.GameControl, DeviceEnumerationFlags.AttachedOnly))
            {



                // Find a Joystick Guid
                var joystickGuid = Guid.Empty;

                foreach (var deviceInstance in input.GetDevices(DeviceType.Gamepad,
                            DeviceEnumerationFlags.AllDevices))
                    joystickGuid = deviceInstance.InstanceGuid;

                // If Gamepad not found, look for a Joystick
                if (joystickGuid == Guid.Empty)
                    foreach (var deviceInstance in input.GetDevices(DeviceType.Joystick,
                            DeviceEnumerationFlags.AllDevices))
                        joystickGuid = deviceInstance.InstanceGuid;

                // If Joystick not found, throws an error
                if (joystickGuid == Guid.Empty)
                {
                    Console.WriteLine("No joystick/Gamepad found.");
                    Console.ReadKey();
                    Environment.Exit(1);
                }
                // Instantiate the joystick
                joystick = new Joystick(input, joystickGuid);
                if (joystick != null)
                {
                    joystick.Properties.BufferSize = 10;
                    return joystick.Information.ProductName.ToString();

                }

                return "";
            }
            return "";
        }

        public string GetStateString()
        {
            try
            {
                // Set BufferSize in order to use buffered data.
                

                // Acquire the joystick
                joystick.Acquire();
                joystick.Poll();
                var state = joystick.GetCurrentState();
                return state.ToString() + Environment.NewLine;
            } catch (Exception err)
            {
                return err.Message;
            }

        }
        public int[] directions()
        {
            try
            {
                // Set BufferSize in order to use buffered data.


                // Acquire the joystick
                joystick.Acquire();
                joystick.Poll();
                var state = joystick.GetCurrentState();
                var stickPositionX = state.X;
                var stickPositionY = state.Y;
                int[] returnValue = new int[3];
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
            return null;
        }
    }

}


