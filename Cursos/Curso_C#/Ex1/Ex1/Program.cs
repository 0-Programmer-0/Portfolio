using System;
using System.Globalization;

namespace Ex1
{
    class Program
    {
        static void Main(string[] args)
        {
            Console.WriteLine("Entre com seu nome completo:");
            string nome = Console.ReadLine();

            Console.WriteLine("Quantos quartos tem na sua casa?");
            int qtd = int.Parse(Console.ReadLine());

            Console.WriteLine("Entre com o preço de um produto:");
            double preco = double.Parse(Console.ReadLine(), CultureInfo.InvariantCulture);

            Console.WriteLine("Entre seu último nome, idade e altura (mesma linha)");
            string[] split = Console.ReadLine().Split();
            string sobrenome = split[0];
            int idade = int.Parse(split[1]);
            double altura = double.Parse(split[2], CultureInfo.InvariantCulture);

            Console.WriteLine("Dados:");
            Console.WriteLine(nome);
            Console.WriteLine(qtd);
            Console.WriteLine(preco.ToString("F2"));
            Console.WriteLine(sobrenome);
            Console.WriteLine(idade);
            Console.WriteLine(altura.ToString("F2"));
        }
    }
}