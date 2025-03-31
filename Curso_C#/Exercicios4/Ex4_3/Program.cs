using System;
using System.Globalization;

namespace Exercicios4
{
    class Program
    {
        static void Main(string[] args)
        {
            string[] notas;
            double n1 = 0;
            double n2 = 0;
            double n3 = 0;
            double mp = 0;
            int p1 = 2;
            int p2 = 3;
            int p3 = 5;
            int qtd = int.Parse(Console.ReadLine());

            for(int i = 0; i < qtd; i++)
            {
                notas = Console.ReadLine().Split(' ');
                n1 = double.Parse(notas[0], CultureInfo.InvariantCulture);
                n2 = double.Parse(notas[1], CultureInfo.InvariantCulture);
                n3 = double.Parse(notas[2], CultureInfo.InvariantCulture);
                mp = ((n1 * p1) + (n2 * p2) + (n3 * p3)) / (p1 + p2 + p3);
                Console.WriteLine(mp.ToString("F2"));
            }
        }
    }
}