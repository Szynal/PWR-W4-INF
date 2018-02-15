using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace TSP
{
    class City
    {
        /// <summary>
        /// Koordynat X
        /// </summary>
        public double X { get; set; }
        /// <summary>
        /// Koordynat Y 
        /// </summary>
        public double Y { get; set; }
        /// <summary>
        /// Identyfikator miasta 
        /// </summary>
        public int Id { get; set; }

        public City(double x, double y, int id)
        {
            X = x;
            Y = y;
            Id = id;
        }

        /// <summary>
        ///  Obliczanie odległości pomiędzy miastami 
        /// </summary>
        /// <param name="other"> </param>
        /// <returns></returns>
        public double Distance(City other)
        {
            double dx = this.X - other.X;
            double dy = this.Y - other.Y;

            return Math.Sqrt(dx * dx + dy * dy);
        }
    }
}
