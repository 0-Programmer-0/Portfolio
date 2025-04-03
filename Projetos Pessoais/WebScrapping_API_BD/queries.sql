-- Tbl Operadoras de plano de saúde ativas
CREATE TABLE operadoras (
    id_operadora INT AUTO_INCREMENT PRIMARY KEY,
    registro_ans VARCHAR(20) UNIQUE NOT NULL,
    cnpj VARCHAR(14) UNIQUE NOT NULL,
    razao_social VARCHAR(255),
    nome_fantasia VARCHAR(255),
    modalidade VARCHAR(100),
    logradouro VARCHAR(255),
    numero VARCHAR(10),
    complemento VARCHAR(100),
    bairro VARCHAR(100),
    cidade VARCHAR(100),
    uf CHAR(2),
    cep VARCHAR(8),
    ddd VARCHAR(3),
    telefone VARCHAR(15),
    fax VARCHAR(15),
    endereco_eletronico VARCHAR(255),
    representante VARCHAR(255),
    cargo_representante VARCHAR(100),
    regiao_de_comercializacao VARCHAR(100),
    data_registro_ans DATE
);

-- Tbl demosntrações contábeis
CREATE TABLE demonstracoes_contabeis (
    id_demonstracao INT AUTO_INCREMENT PRIMARY KEY,
    data_demonstracao DATE NOT NULL,
    reg_ans VARCHAR(20),
    cd_conta_contabil VARCHAR(20),
    descricao VARCHAR(255),
    vl_saldo_inicial DECIMAL(15, 2),
    vl_saldo_final DECIMAL(15, 2),
    FOREIGN KEY (reg_ans) REFERENCES operadoras(registro_ans)
);

-- Carrega csv operadoras
LOAD DATA INFILE 'C:/ProgramData/MySQL/MySQL Server 8.0/Data/Relatorio_cadop.csv'
INTO TABLE operadoras
FIELDS TERMINATED BY ';'
ENCLOSED BY '"'
LINES TERMINATED BY '\n'
IGNORE 1 ROWS
(registro_ans, cnpj, razao_social, nome_fantasia, modalidade, logradouro, numero, complemento, 
bairro, cidade, uf, cep, ddd, telefone, fax, endereco_eletronico, representante, cargo_representante, 
regiao_de_comercializacao, data_registro_ans);

-- Carrega csv demonstrações contábeis - 1 para cada arquivo

-- Demonstrações contábeis 2023
LOAD DATA INFILE 'C:/ProgramData/MySQL/MySQL Server 8.0/Data/1T2023.csv'
INTO TABLE demonstracoes_contabeis
FIELDS TERMINATED BY ';'
ENCLOSED BY '"'
LINES TERMINATED BY '\n'
IGNORE 1 ROWS
(data_demonstracao, reg_ans, cd_conta_contabil, descricao, vl_saldo_inicial, vl_saldo_final);

LOAD DATA INFILE 'C:/ProgramData/MySQL/MySQL Server 8.0/Data/2T2023.csv'
INTO TABLE demonstracoes_contabeis
FIELDS TERMINATED BY ';'
ENCLOSED BY '"'
LINES TERMINATED BY '\n'
IGNORE 1 ROWS
(data_demonstracao, reg_ans, cd_conta_contabil, descricao, vl_saldo_inicial, vl_saldo_final);

LOAD DATA INFILE 'C:/ProgramData/MySQL/MySQL Server 8.0/Data/3T2023.csv'
INTO TABLE demonstracoes_contabeis
FIELDS TERMINATED BY ';'
ENCLOSED BY '"'
LINES TERMINATED BY '\n'
IGNORE 1 ROWS
(data_demonstracao, reg_ans, cd_conta_contabil, descricao, vl_saldo_inicial, vl_saldo_final);

LOAD DATA INFILE 'C:/ProgramData/MySQL/MySQL Server 8.0/Data/4T2023.csv'
INTO TABLE demonstracoes_contabeis
FIELDS TERMINATED BY ';'
ENCLOSED BY '"'
LINES TERMINATED BY '\n'
IGNORE 1 ROWS
(data_demonstracao, reg_ans, cd_conta_contabil, descricao, vl_saldo_inicial, vl_saldo_final);

-- Demonstrações contábeis 2024
LOAD DATA INFILE 'C:/ProgramData/MySQL/MySQL Server 8.0/Data/1T2024.csv'
INTO TABLE demonstracoes_contabeis
FIELDS TERMINATED BY ';'
ENCLOSED BY '"'
LINES TERMINATED BY '\n'
IGNORE 1 ROWS
(data_demonstracao, reg_ans, cd_conta_contabil, descricao, vl_saldo_inicial, vl_saldo_final);

LOAD DATA INFILE 'C:/ProgramData/MySQL/MySQL Server 8.0/Data/2T2024.csv'
INTO TABLE demonstracoes_contabeis
FIELDS TERMINATED BY ';'
ENCLOSED BY '"'
LINES TERMINATED BY '\n'
IGNORE 1 ROWS
(data_demonstracao, reg_ans, cd_conta_contabil, descricao, vl_saldo_inicial, vl_saldo_final);

LOAD DATA INFILE 'C:/ProgramData/MySQL/MySQL Server 8.0/Data/3T2024.csv'
INTO TABLE demonstracoes_contabeis
FIELDS TERMINATED BY ';'
ENCLOSED BY '"'
LINES TERMINATED BY '\n'
IGNORE 1 ROWS
(data_demonstracao, reg_ans, cd_conta_contabil, descricao, vl_saldo_inicial, vl_saldo_final);

LOAD DATA INFILE 'C:/ProgramData/MySQL/MySQL Server 8.0/Data/4T2024.csv'
INTO TABLE demonstracoes_contabeis
FIELDS TERMINATED BY ';'
ENCLOSED BY '"'
LINES TERMINATED BY '\n'
IGNORE 1 ROWS
(data_demonstracao, reg_ans, cd_conta_contabil, descricao, vl_saldo_inicial, vl_saldo_final);

-- Top 10 operadoras com maiores despesas em "EVENTOS/ SINISTROS CONHECIDOS OU AVISADOS DE ASSISTÊNCIA A SAÚDE MEDICO HOSPITALAR" no último trimestre
SELECT o.nome_fantasia, SUM(dc.vl_saldo_final) AS total_despesas
FROM demonstracoes_contabeis dc
JOIN operadoras o ON dc.reg_ans = o.registro_ans
WHERE 1=1
AND dc.descricao = 'EVENTOS/ SINISTROS CONHECIDOS OU AVISADOS DE ASSISTÊNCIA A SAÚDE MEDICO HOSPITALAR'
AND dc.data_demonstracao >= '2024-10-01' AND dc.data_demonstracao <= '2024-12-31'
GROUP BY o.registro_ans
ORDER BY total_despesas DESC
LIMIT 10;

-- Top 10 operadoras com maiores despesas em "EVENTOS/ SINISTROS CONHECIDOS OU AVISADOS DE ASSISTÊNCIA A SAÚDE MEDICO HOSPITALAR" no último ano
SELECT o.nome_fantasia, SUM(dc.vl_saldo_final) AS total_despesas
FROM demonstracoes_contabeis dc
JOIN operadoras o ON dc.reg_ans = o.registro_ans
WHERE 1=1
AND dc.descricao = 'EVENTOS/ SINISTROS CONHECIDOS OU AVISADOS DE ASSISTÊNCIA A SAÚDE MEDICO HOSPITALAR'
AND YEAR(dc.data_demonstracao) = 2024
GROUP BY o.registro_ans
ORDER BY total_despesas DESC
LIMIT 10;
