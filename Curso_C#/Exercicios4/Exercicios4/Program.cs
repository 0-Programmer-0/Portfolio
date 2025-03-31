using System;

namespace Exercicios4
{
    class Program
    {
        static void Main(string[] args)
        {
            int x = int.Parse(Console.ReadLine());
            while(x < 1 || x > 100)
            {
                x = int.Parse(Console.ReadLine());
            }
            for (int i = 0; i <= x; i++)
            {
                if(i % 2 != 0)
                    Console.WriteLine(i);
            }
        }
    }
}