using System;
using System.Globalization;

namespace Exercicios5
{
    class Program
    {
        static void Main(string[] args)
        {
            //Retangulo r = new Retangulo();

            //Console.WriteLine("Entre a largura e altura do retângulo:");
            //r.Largura = double.Parse(Console.ReadLine(), CultureInfo.InvariantCulture);
            //r.Altura = double.Parse(Console.ReadLine(), CultureInfo.InvariantCulture);

            //Console.WriteLine(r);

            //Funcionario f = new Funcionario();

            //Console.Write("Nome: ");
            //f.Nome = Console.ReadLine();

            //Console.Write("Salário Bruto: ");
            //f.SalarioBruto = double.Parse(Console.ReadLine(), CultureInfo.InvariantCulture);

            //Console.Write("Imposto: ");
            //f.Imposto = double.Parse(Console.ReadLine(), CultureInfo.InvariantCulture);

            //Console.WriteLine();
            //Console.WriteLine(f);

            //Console.WriteLine();
            //Console.Write("Digite a porcentagem para aumentar o salário: ");
            //f.AumentarSalario(double.Parse(Console.ReadLine(), CultureInfo.InvariantCulture));

            //Console.WriteLine();
            //Console.WriteLine(f);

            Aluno a = new Aluno();

            Console.Write("Nome do Aluno: ");
            a.Nome = Console.ReadLine();

            Console.Write("N1: ");
            a.N1 = double.Parse(Console.ReadLine(), CultureInfo.InvariantCulture);

            Console.Write("N2: ");
            a.N2 = double.Parse(Console.ReadLine(), CultureInfo.InvariantCulture);

            Console.Write("N3: ");
            a.N3 = double.Parse(Console.ReadLine(), CultureInfo.InvariantCulture);

            Console.WriteLine(a.AprovacaoAluno());
        }
    }
}