using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.EntityFrameworkCore;
using DemoRazorPages.Models;

namespace DemoRazorPages.Data
{
    public class DemoRazorPagesContext : DbContext
    {
        public DemoRazorPagesContext (DbContextOptions<DemoRazorPagesContext> options)
            : base(options)
        {
        }

        public DbSet<DemoRazorPages.Models.Aluno> Aluno { get; set; } = default!;
    }
}
