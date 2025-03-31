using System;
using System.Globalization;

namespace Exercicios2
{
    class Program
    {
        static void Main(string[] args)
        {
            Console.Write("Código: ");
            int codigo = int.Parse(Console.ReadLine());

            Console.Write("Qtd: ");
            int qtd = int.Parse(Console.ReadLine());

            double preco = 0;

            if (codigo == 1) preco = 4 * qtd;
            else if (codigo == 2) preco = 4.5 * qtd;
            else if (codigo == 3) preco = 5 * qtd;
            else if (codigo == 4) preco = 2 * qtd;
            else if (codigo == 5) preco = 1.5 * qtd;

            Console.WriteLine($"Total: R$ {preco.ToString("F2", CultureInfo.InvariantCulture)}");
        }
    }
}