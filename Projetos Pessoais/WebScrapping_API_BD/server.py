from fastapi import FastAPI, Query
import pandas as pd
from fastapi.middleware.cors import CORSMiddleware

app = FastAPI()

# Configuração de CORS
origins = [
    "http://localhost:5173",  # O domínio do seu frontend Vue.js
    # "https://example.com",  # Adicione outros domínios, se necessário
]

app.add_middleware(
    CORSMiddleware,
    allow_origins=origins,  # Permite as origens especificadas
    allow_credentials=True,
    allow_methods=["*"],  # Permite todos os métodos HTTP
    allow_headers=["*"],  # Permite todos os cabeçalhos
)

# Carregar os dados do CSV
df = pd.read_csv("Relatorio_cadop.csv", dtype=str, delimiter=";", on_bad_lines="skip", encoding="utf-8")

@app.get("/")
def read_root():
    return {"mensagem": "API de Operadoras Ativas"}

@app.get("/buscar")
async def buscar_operadora(nome: str = Query(None, min_length=3, description="Nome da operadora")):
    if nome:
        resultado = df[df['Nome_Fantasia'].str.contains(nome, case=False, na=False)]
        
        # Substituir valores NaN por uma string vazia ou outro valor que você preferir
        resultado = resultado.fillna("")  # Substitui NaN por uma string vazia
        
        # Verifica se há resultados
        if resultado.empty:
            return {"erro": "Nenhuma operadora encontrada com esse nome."}
        
        return resultado.to_dict(orient="records")
    
    return {"erro": "Informe um nome válido com pelo menos 3 caracteres."}

