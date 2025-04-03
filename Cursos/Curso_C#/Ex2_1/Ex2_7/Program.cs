using System;
using System.Globalization;

namespace Exercicios2
{
    class Program
    {
        static void Main(string[] args)
        {
            double x = double.Parse(Console.ReadLine(), CultureInfo.InvariantCulture);
            double y = double.Parse(Console.ReadLine(), CultureInfo.InvariantCulture);

            // ABSOLUTOS
            if(x == 0 && y == 0)
                Console.WriteLine("Origem");
            else if(x == 0 && y > 0)
                Console.WriteLine("Entre Q1 e Q2");
            else if (x == 0 && y < 0)
                Console.WriteLine("Entre Q3 e Q4");
            else if (x > 0 && y == 0)
                Console.WriteLine("Entre Q1 e Q4");
            else if (x < 0 && y == 0)
                Console.WriteLine("Entre Q2 e Q3");
            // QUADRANTES
            else if (x > 0 && y > 0)
                Console.WriteLine("Q1");
            else if (x > 0 && y < 0)
                Console.WriteLine("Q4");
            else if (x < 0 && y > 0)
                Console.WriteLine("Q2");
            else if (x < 0 && y < 0)
                Console.WriteLine("Q3");
        }
    }
}