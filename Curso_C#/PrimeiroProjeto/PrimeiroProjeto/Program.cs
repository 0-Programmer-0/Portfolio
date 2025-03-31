using System;
using System.Globalization;

namespace PrimeiroProjeto
{
    class Program
    {
        static void Main(string[] args)
        {
            double saldo = 10.12345;
            int idade = 21;
            string nome = "João";

            Console.WriteLine(saldo.ToString("F2"));
            Console.WriteLine(saldo.ToString("F4", CultureInfo.InvariantCulture));
            Console.WriteLine("{0} tem {1} anos, e seu saldo é de R${2:f2}", nome, idade, saldo);
            Console.WriteLine($"{nome} tem {idade} anos, e seu saldo é de R${saldo:f2}");

        }
    }
}