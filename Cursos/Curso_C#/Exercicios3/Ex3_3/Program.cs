using System;

namespace Exercicios3
{
    class Program
    {
        static void Main(string[] args)
        {
            int contA = 0;
            int contG = 0;
            int contD = 0;

            int codigo = int.Parse(Console.ReadLine());

            while (codigo != 4)
            {
                if (codigo == 1)
                    contA++;
                else if (codigo == 2)
                    contG++;
                else if (codigo == 3)
                    contD++;

                codigo = int.Parse(Console.ReadLine());
            }

            Console.WriteLine("MUITO OBRIGADO");
            Console.WriteLine($"Álcool: {contA}");
            Console.WriteLine($"Gasolina: {contG}");
            Console.WriteLine($"Diesel: {contD}");
        }
    }
}