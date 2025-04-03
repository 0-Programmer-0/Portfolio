using System;
using System.Globalization;
using System.Threading.Channels;

namespace InputDados
{
    class Program
    {
        static void Main(string[] args)
        {
            Console.WriteLine("n1:");
            int n1 = int.Parse(Console.ReadLine());

            Console.WriteLine("ch:");
            char ch = char.Parse(Console.ReadLine());

            Console.WriteLine("n2:");
            double n2 = double.Parse(Console.ReadLine());

            Console.WriteLine("dados:");
            string[] split = Console.ReadLine().Split(' ');
            string nome = split[0];
            char sexo = char.Parse(split[1]);
            int idade = int.Parse(split[2]);
            double altura = double.Parse(split[3]);

            Console.WriteLine("\nVocê digitou:");
            Console.WriteLine(n1);
            Console.WriteLine(ch);
            Console.WriteLine(n2.ToString(), CultureInfo.InvariantCulture);
            Console.WriteLine(nome);
            Console.WriteLine(sexo);
            Console.WriteLine(idade);
            Console.WriteLine(altura.ToString(), CultureInfo.InvariantCulture);
        }
    }
}