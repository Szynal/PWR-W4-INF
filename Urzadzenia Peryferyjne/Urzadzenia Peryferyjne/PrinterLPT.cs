using Microsoft.Win32.SafeHandles;
using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Runtime.InteropServices;
using System.Text;
using System.Threading.Tasks;

namespace Urzadzenia_Peryferyjne
{
    public class PrinterLPT
    {
        public const short FILE_ATTRIBUTE_NORMAL = 0x80;
        public const short INVALID_HANDLE_VALUE = -1;
        public const uint GENERIC_READ = 0x80000000;
        public const uint GENERIC_WRITE = 0x40000000;
        public const uint CREATE_NEW = 1;
        public const uint CREATE_ALWAYS = 2;
        public const uint OPEN_EXISTING = 3;
        public const char esc = (char)0x1B;
        IntPtr ptr;
        SafeFileHandle ptrHandle;
        FileStream lpt;


        [System.Runtime.InteropServices.DllImport("kernel32.dll", SetLastError = true)]
        static extern IntPtr CreateFile(string lpFileName, uint dwDesiredAccess,
            uint dwShareMode, IntPtr lpSecurityAttributes, uint dwCreationDisposition,
            uint dwFlagsAndAttributes, IntPtr hTemplateFile);

        public bool InitLpt()
        {
            ptr = CreateFile("LPT3", GENERIC_WRITE, 0,
                     IntPtr.Zero, OPEN_EXISTING, 0, IntPtr.Zero);
            ptrHandle = new SafeFileHandle(ptr, true);
            if (ptr.ToInt32() == -1)
            {
                /* ask the framework to marshall the win32 error code to an exception */
                System.Runtime.InteropServices.Marshal.ThrowExceptionForHR(Marshal.GetHRForLastWin32Error());
                return false;
            }
            lpt = new FileStream(ptrHandle, FileAccess.ReadWrite);
            return true;
        }
        public void SendTextToLPT1(String receiptText, FileStream lpt)
        {
            
            Byte[] buffer = new Byte[2048];
            //Check to see if your printer support ASCII encoding or Unicode.
            //If unicode is supported, use the following:
            //buffer = System.Text.Encoding.Unicode.GetBytes(Temp);
            buffer = System.Text.Encoding.ASCII.GetBytes(receiptText);
            lpt.Write(buffer, 0, buffer.Length);
            
        }
        public void WriteText(string text, int font, int size, int color, bool vertical, bool bold, bool italic, bool underline)
        {
            
            //SendTextToLPT1(esc + "(s1p16901T", lpt);

            // Reset drukarki
            SendTextToLPT1(esc + "E", lpt);
            // Zawijanie wierszy
            SendTextToLPT1(esc + "&s0C", lpt);
            // Orientacja strony
            if (vertical)
                SendTextToLPT1(esc + "&l0O", lpt);
            else
                SendTextToLPT1(esc + "&l1O", lpt);
            // Jakość druku
            SendTextToLPT1(esc + "*o0M", lpt);
            // Rozmiar papieru A4
            SendTextToLPT1(esc + "&l26A", lpt);
            // Idź do kolumny 5
            SendTextToLPT1(esc + "&a5C", lpt);
            // Idź do wiersza 5
            SendTextToLPT1(esc + "&a4R", lpt);
            // Podkreślenie
            if (underline)
                SendTextToLPT1(esc + "&d3D", lpt);
            // Kodowanie windows 1250
            SendTextToLPT1(esc + "(9E", lpt);
            // Rozmiar czcionki
            SendTextToLPT1(esc + "(s100H", lpt);
            // Pochylenie
            if (italic)
                SendTextToLPT1(esc + "(s1S", lpt);
            // Pogrubienie
            if (bold)
                SendTextToLPT1(esc + "(s3B", lpt);
            switch (font)
            {
                case 0:
                    SendTextToLPT1(esc + "(s1p16901T", lpt);
                    break;
                case 1:
                    SendTextToLPT1(esc + "(s1p16602T", lpt);
                    break;
                case 2:
                    SendTextToLPT1(esc + "(s1p4362T", lpt);
                    break;
                case 3:
                    SendTextToLPT1(esc + "(s1p4297T", lpt);
                    break;
                default:
                    SendTextToLPT1(esc + "(s1p16901T", lpt);
                    break;

            }
            // Tryb koloru simple color
            SendTextToLPT1(esc + "*r-3U", lpt);
            // Wybór koloru
            //SendTextToLPT1(esc + "*v" + color.ToString() + "S", lpt);
            // Wysłanie textu
            SendTextToLPT1(esc + "&l10P", lpt);
            
            SendTextToLPT1(text, lpt);
            SendTextToLPT1(esc + "@", lpt);
            // Reset drukarki
            SendTextToLPT1(esc + "E", lpt);
            lpt.Close();
            
        }
        public void WriteRaster()
        {
            FileStream lpt = new FileStream(ptrHandle, FileAccess.ReadWrite);
            string Y = "08", F = "FF", N = "00";
            // Reset drukarki
            SendTextToLPT1(esc + "E", lpt);
            // Wybór trybu kolorów
            SendTextToLPT1(esc + "*r-3U", lpt);
            // Start raster
            SendTextToLPT1(esc + "*r0A", lpt);
            // Metoda kompresji
            SendTextToLPT1(esc + "*b1M", lpt);
            // Malowanie
            SendTextToLPT1(esc + "*b16V" + Y + F + Y + N + Y + N + Y + N + Y + F + Y + N + Y + F + Y + F, lpt);
            SendTextToLPT1(esc + "*b16V" + Y + N + Y + F + Y + N + Y + N + Y + F + Y + F + Y + N + Y + F, lpt);
            SendTextToLPT1(esc + "*b16W" + Y + N + Y + N + Y + F + Y + N + Y + F + Y + F + Y + F + Y + N, lpt);
            SendTextToLPT1(esc + "*b16V" + Y + F + Y + N + Y + N + Y + N + Y + F + Y + N + Y + F + Y + F, lpt);
            SendTextToLPT1(esc + "*b16V" + Y + N + Y + F + Y + N + Y + N + Y + F + Y + F + Y + N + Y + F, lpt);
            SendTextToLPT1(esc + "*b16W" + Y + N + Y + N + Y + F + Y + N + Y + F + Y + F + Y + F + Y + N, lpt);
            SendTextToLPT1(esc + "*b16V" + Y + F + Y + N + Y + N + Y + N + Y + F + Y + N + Y + F + Y + F, lpt);
            SendTextToLPT1(esc + "*b16V" + Y + N + Y + F + Y + N + Y + N + Y + F + Y + F + Y + N + Y + F, lpt);
            SendTextToLPT1(esc + "*b16W" + Y + N + Y + N + Y + F + Y + N + Y + F + Y + F + Y + F + Y + N, lpt);
            SendTextToLPT1(esc + "*b16V" + Y + F + Y + N + Y + N + Y + N + Y + F + Y + N + Y + F + Y + F, lpt);
            SendTextToLPT1(esc + "*b16V" + Y + N + Y + F + Y + N + Y + N + Y + F + Y + F + Y + N + Y + F, lpt);
            SendTextToLPT1(esc + "*b16W" + Y + N + Y + N + Y + F + Y + N + Y + F + Y + F + Y + F + Y + N, lpt);
            SendTextToLPT1(esc + "*b16V" + Y + F + Y + N + Y + N + Y + N + Y + F + Y + N + Y + F + Y + F, lpt);
            SendTextToLPT1(esc + "*b16V" + Y + N + Y + F + Y + N + Y + N + Y + F + Y + F + Y + N + Y + F, lpt);
            SendTextToLPT1(esc + "*b16W" + Y + N + Y + N + Y + F + Y + N + Y + F + Y + F + Y + F + Y + N, lpt);
            SendTextToLPT1(esc + "*b16V" + Y + F + Y + N + Y + N + Y + N + Y + F + Y + N + Y + F + Y + F, lpt);
            SendTextToLPT1(esc + "*b16V" + Y + N + Y + F + Y + N + Y + N + Y + F + Y + F + Y + N + Y + F, lpt);
            SendTextToLPT1(esc + "*b16W" + Y + N + Y + N + Y + F + Y + N + Y + F + Y + F + Y + F + Y + N, lpt);
            SendTextToLPT1(esc + "*b16V" + Y + F + Y + N + Y + N + Y + N + Y + F + Y + N + Y + F + Y + F, lpt);
            SendTextToLPT1(esc + "*b16V" + Y + N + Y + F + Y + N + Y + N + Y + F + Y + F + Y + N + Y + F, lpt);
            SendTextToLPT1(esc + "*b16W" + Y + N + Y + N + Y + F + Y + N + Y + F + Y + F + Y + F + Y + N, lpt);
            SendTextToLPT1(esc + "*b16V" + Y + F + Y + N + Y + N + Y + N + Y + F + Y + N + Y + F + Y + F, lpt);
            SendTextToLPT1(esc + "*b16V" + Y + N + Y + F + Y + N + Y + N + Y + F + Y + F + Y + N + Y + F, lpt);
            SendTextToLPT1(esc + "*b16W" + Y + N + Y + N + Y + F + Y + N + Y + F + Y + F + Y + F + Y + N, lpt);
            SendTextToLPT1(esc + "*rC", lpt);
            SendTextToLPT1(esc + "E", lpt);
            lpt.Close();
        }
    }
}
