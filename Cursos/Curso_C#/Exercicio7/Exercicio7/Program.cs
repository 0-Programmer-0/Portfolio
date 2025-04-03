using System;
using System.Globalization;

namespace Exercicio7 {
    class Program
    {
        static void Main(string[] args)
        {
            double valor = 0;

            Console.Write("Número da conta: ");
            int numConta = int.Parse(Console.ReadLine());

            Console.Write("Titular da Conta: ");
            string titular = Console.ReadLine();

            Console.Write("Haverá depósito inicial (s/n): ");
            char depInicial = char.Parse(Console.ReadLine().ToLower());

            if(depInicial == 's')
            {
                Console.Write("Valor de depósito inicial: ");
                valor = double.Parse(Console.ReadLine(), CultureInfo.InvariantCulture);
            }

            Conta conta = new Conta(numConta, titular, valor);

            Console.WriteLine(conta);

            Console.Write("\nEntre um valor para depósito: ");
            conta.Deposito(double.Parse(Console.ReadLine(), CultureInfo.InvariantCulture));

            Console.WriteLine(conta);

            Console.Write("\nEntre um valor para saque: ");
            conta.Saque(double.Parse(Console.ReadLine(), CultureInfo.InvariantCulture));

            Console.WriteLine(conta);
        }
    }
}