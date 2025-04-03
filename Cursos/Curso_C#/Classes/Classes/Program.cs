using System;
using System.Globalization;

namespace Classes
{
    class Program
    {
        static void Main(string[] args)
        {
            Pessoa p1 = new Pessoa();
            Pessoa p2 = new Pessoa();

            Funcionario f1 = new Funcionario();
            Funcionario f2 = new Funcionario();
            double media;

            //Console.WriteLine("Dados da 1° pessoa:");
            //Console.Write("Nome: ");
            //p1.Nome = Console.ReadLine();
            //Console.Write("Idade: ");
            //p1.Idade = int.Parse(Console.ReadLine());

            //Console.WriteLine("Dados da 2° pessoa:");
            //Console.Write("Nome: ");
            //p2.Nome = Console.ReadLine();
            //Console.Write("Idade: ");
            //p2.Idade = int.Parse(Console.ReadLine());

            //if(p1.Idade > p2.Idade)
            //    Console.WriteLine($"Pessoa mais velha: {p1.Nome}");
            //else
            //    Console.WriteLine($"Pessoa mais velha: {p2.Nome}");

            Console.WriteLine("Dados do 1° funcionário:");
            Console.Write("Nome: ");
            f1.nome = Console.ReadLine();
            Console.Write("Salário: ");
            f1.salario = double.Parse(Console.ReadLine(), CultureInfo.InvariantCulture);

            Console.WriteLine("Dados do 2° funcionário:");
            Console.Write("Nome: ");
            f2.nome = Console.ReadLine();
            Console.Write("Salário: ");
            f2.salario = double.Parse(Console.ReadLine(), CultureInfo.InvariantCulture);

            media = (f1.salario + f2.salario) / 2;
            Console.WriteLine($"Salário médio = {media.ToString("F2")}");
        }
    }
}