using System;

namespace Comparativos
{
    class Program
    {
        static void Main(string[] args)
        {
            int a = 10;
            bool c1 = a < 10;
            bool c2 = a > 10;
            bool c3 = a <= 10;
            bool c4 = a >= 10;

            bool c5 = a <= 10 && a == 11;
            bool c6 = a <= 10 || a == 11;
            bool c7 = !(a > 11) && 4 != 5;

            Console.WriteLine(a.ToString() + " menor que 10: " + c1);
            Console.WriteLine(a.ToString() + " maior que 10: " + c2);
            Console.WriteLine(a.ToString() + " menor ou igual que 10: " + c3);
            Console.WriteLine(a.ToString() + " maior ou igual que 10: " + c4);

            Console.WriteLine("-----------------------------------------------------");

            Console.WriteLine("Operador lógico E: " + c5);
            Console.WriteLine("Operador lógico OU: " + c6);
            Console.WriteLine("Operador lógico NOT: " + c7);
        }
    }
}