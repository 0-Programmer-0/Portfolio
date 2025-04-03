using System;

namespace Exercicios2
{
    class Program
    {
        static void Main(string[] args)
        {
            Console.Write("Hora inicial: ");
            int horaI = int.Parse(Console.ReadLine());
            Console.Write("Hora final: ");
            int horaF = int.Parse(Console.ReadLine());
            int duracao = 0;

            if (horaI < horaF)
                duracao = horaF - horaI;
            else if (horaI > horaF)
                duracao = horaF + (24 - horaI);
            else
                duracao = 24;

            Console.WriteLine($"O JOGO DUROU {duracao} HORA(S)");
        }
    }
}