using System;
using System.Collections.Generic;
using System.Globalization;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Exercicios5
{
    public class Aluno
    {
        public string Nome { get; set; }
        public double N1 { get; set; }
        public double N2 { get; set; }
        public double N3 { get; set; }

        public string AprovacaoAluno()
        {
            double nf = N1 + N2 + N3;
            if (nf >= 60)
                return $"NOTA FINAL = {nf.ToString("F2", CultureInfo.InvariantCulture)} \nAPROVADO";
            else
                return $"NOTA FINAL = {nf.ToString("F2", CultureInfo.InvariantCulture)} \nREPROVADO \nFALTARAM {(60 - nf).ToString("F2", CultureInfo.InvariantCulture)} PONTOS";
        }
    }
}
