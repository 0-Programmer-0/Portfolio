using System;
using System.Collections.Generic;
using System.Globalization;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Exercicio7
{
    public class Conta
    {
        public int NumConta { get; private set; }
        public string TitularConta { get; set; }
        public double Saldo { get; private set; }

        public Conta(int numConta, string titular, double saldo)
        {
            TitularConta = titular;
            NumConta = numConta;
            Saldo = saldo;
        }

        public void Deposito(double valor)
        {
            Saldo += valor;
        }

        public void Saque(double valor)
        {
            Saldo -= valor + 5.00; //taxa de saque do banco
        }

        public override string ToString()
        {
            return $"\nDados da conta:" +
                   $"\nConta {NumConta}, Titular: {TitularConta}, Saldo: ${Saldo.ToString("F2", CultureInfo.InvariantCulture)}";
        }
    }
}
