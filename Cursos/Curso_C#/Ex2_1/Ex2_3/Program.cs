using System;

namespace Exercicios2
{
    class Program
    {
        static void Main(string[] args)
        {
            int a = int.Parse(Console.ReadLine());
            int b = int.Parse(Console.ReadLine());
            int retorno = 0;

            if (a > b)
                retorno = a % b;
            else
                retorno = b % a;

            if (retorno == 0)
                Console.WriteLine("São Multiplos");
            else
                Console.WriteLine("Não são multiplos");
        }
    }
}