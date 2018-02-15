using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

/// <summary>
///    AUTOR: PAWEŁ SZYNAL 
///    NR ALBUMU: 226026
///    PRZEDMIOT: Projektowanie efektywnych algorytmów (PEA)
///    PROWADZĄCY: dr inż. Łukasz Jeleń
/// </summary>
/// 
namespace TSP_PEA
{
    public class ConsoleAutoStopWatch : IDisposable
    {
        private readonly Stopwatch _stopWatch;

        public ConsoleAutoStopWatch()
        {
            _stopWatch = new Stopwatch();
            _stopWatch.Start();
        }

        public void RestartClock()
        {
            _stopWatch.Restart();
        }

        ~ConsoleAutoStopWatch() { }

        public void Dispose()
        {
            _stopWatch.Stop();
            TimeSpan ts = _stopWatch.Elapsed;

            string _elapsedTime = String.Format("{0:00}:{1:00}:{2:00}.{3:00}",
                                               ts.Hours, ts.Minutes, ts.Seconds,
                                               ts.Milliseconds / 10);
            Console.WriteLine(_elapsedTime, "RunTime");
        }
    }
}
