using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Exercicios6
{
    public class ConversorDeMoeda
    {
        public static double Dolar { get; set; }
        public static double Real { get; set; }
        static double iof = 0.06;

        public static double ConversaoDolarReal()
        {
            Dolar += Dolar * iof;
            return Dolar * Real;
        }
    }
}
