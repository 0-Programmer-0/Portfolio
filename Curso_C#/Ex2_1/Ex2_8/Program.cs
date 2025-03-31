using System;
using System.Globalization;

namespace Exercicios2
{
    class Program
    {
        static void Main(string[] args)
        {
            double renda = double.Parse(Console.ReadLine(), CultureInfo.InvariantCulture);
            double imposto = 0;

            if(renda > 0 && renda <= 2000)
                Console.WriteLine("Isento");
            else if(renda <= 3000)
            {
                double calc = renda - 2000;
                imposto = calc * 0.08;
                Console.WriteLine($"R$ {imposto}");
            }
            else if (renda <= 4500)
            {
                double calc = renda - 3000;
                imposto = (1000 * 0.08) + (calc * 0.18);
                Console.WriteLine($"R$ {imposto}");
            }
            else if (renda > 4500)
            {
                double calc = renda - 4500;
                imposto = (1000 * 0.08) + (1500 * 0.18) + (calc * 0.28);
                Console.WriteLine($"R$ {imposto}");
            }
            else
                Console.WriteLine("Isento");
        }
    }
}