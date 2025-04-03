using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Secao6
{
    struct Point
    {
        public double x;
        public double y;

        public override string ToString()
        {
            return $"({x}, {y})";
        }
    }
}
