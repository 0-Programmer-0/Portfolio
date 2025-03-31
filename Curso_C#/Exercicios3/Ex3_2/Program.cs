using System;

namespace Exercicios3
{
    class Program
    {
        static void Main(string[] args)
        {
            string[] coord = Console.ReadLine().Split(' ');
            int x = int.Parse(coord[0]);
            int y = int.Parse(coord[1]);

            while (x != 0 || y != 0)
            {
                if (x > 0 && y > 0)
                    Console.WriteLine("primeiro");
                else if (x > 0 && y < 0)
                    Console.WriteLine("quarto");
                else if (x < 0 && y > 0)
                    Console.WriteLine("segundo");
                else if(x < 0 && y < 0)
                    Console.WriteLine("terceiro");

                coord = Console.ReadLine().Split(' ');
                x = int.Parse(coord[0]);
                y = int.Parse(coord[1]);
            }
        }
    }
}