// Configurações Builder
using DemoVS;

var builder = WebApplication.CreateBuilder(args);

//Configurações do Pipeline
builder.AddSerilog();

builder.Services.AddControllersWithViews();

//Configuração da App
var app = builder.Build();

app.UseLogTempo();
app.UseMiddleware<MeuMiddleware>();

app.MapGet("/", () => "Hello World!");
app.MapGet("/teste", () => 
{
    Thread.Sleep(1500);
    return "Teste 2";
});

app.Run();
