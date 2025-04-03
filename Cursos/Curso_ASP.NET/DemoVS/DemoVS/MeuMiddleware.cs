using Serilog;
using System.Diagnostics;

namespace DemoVS
{
    public class LogTempoMiddleware
    {
        // Essa classe chama e espera retorno dela Request e delegate
        private readonly RequestDelegate _next;

        public LogTempoMiddleware(RequestDelegate next)
        {
            _next = next;
        }

        //HttpContext contém a chamada feita na web
        public async Task InvokeAsync(HttpContext httpContext)
        {
            // Faz algo antes
            var sw = Stopwatch.StartNew();

            // Chama o próprio middleware no pipeline
            await _next(httpContext);

            sw.Stop();

            Log.Logger = new LoggerConfiguration().WriteTo.Console().CreateLogger();
            Log.Information($"A execução demorou {sw.Elapsed.TotalMilliseconds}ms ({sw.Elapsed.TotalSeconds}) segundos");

            // Faz algo depois
        }
    }

    public class MeuMiddleware
    {
        // Essa classe chama e espera retorno dela Request e delegate
        private readonly RequestDelegate _next;

        public MeuMiddleware(RequestDelegate next)
        {
            _next = next;
        }

        //HttpContext contém a chamada feita na web
        public async Task InvokeAsync(HttpContext httpContext)
        {
            // Faz algo antes

            // Chama o próprio middleware no pipeline
            await _next(httpContext);

            Log.Logger = new LoggerConfiguration().WriteTo.Console().CreateLogger();
            Log.Information("Meu segundo Middleware");

            // Faz algo depois
        }
    }

    public static class SerilogExtensions
    {
        public static void AddSerilog(this WebApplicationBuilder builder)
        {
            builder.Host.UseSerilog();
        }
    }

    public static class LogTempoMiddlewareExtensios
    {
        public static void UseLogTempo(this WebApplication app)
        {
            app.UseMiddleware<LogTempoMiddleware>();
        }
    }
}
