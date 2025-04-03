using System;

namespace Exercicios4
{
    class Program
    {
        static void Main(string[] args)
        {
            int qtd = int.Parse(Console.ReadLine());
            int dentro = 0;
            int fora = 0;

            for (int i = 0; i < qtd; i++)
            {
                int num = int.Parse(Console.ReadLine());
                if (num >= 10 && num <= 20)
                    dentro++;
                else
                    fora++;
            }
            Console.WriteLine($"{dentro} in");
            Console.WriteLine($"{fora} out");
        }
    }
}