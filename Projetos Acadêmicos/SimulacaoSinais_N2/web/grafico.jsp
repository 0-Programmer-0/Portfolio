<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Simulador de Canal de Comunicações</title>
        <!-- Inclui os links para os arquivos Bootstrap CSS e JS -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <link rel="stylesheet" href="css/style.css"> <!-- Adiciona o arquivo de estilo personalizado -->
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap"></script>
        <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
        <script src="js/scripts.js"></script>
    </head>

    <header>
        <nav id="navBar" class="navbar navbar-expand-lg navbar-dark fixed-top">
            <a class="navbar-brand" href="#">Simulador de Canal de Comunicações</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav"
                    aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav ml-auto">
                    <li class="nav-item">
                        <a class="nav-link" href="menu.html">Home</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="home.html">Simulador</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="sobre.html">Sobre</a>
                    </li>  
                    <li class="nav-item">
                        <a class="nav-link" id="toggle-mode">Alternar Modo</a>
                    </li>
                </ul>
            </div>
        </nav>
    </header>
    
    <body>
        <div class="container mt-5" onload="habilitaFreqCorteInferior(tipoCanal)">
            <br>
            <br>
            <img style="width: 165px; height: 151px; margin-left: 43.5%" src="img/logo1.png" alt="AllKwanza"/>
            <br>
            <br>
            <form method="post">
                <div class="row ajusteCampos">
                    <div class="col-4 form-group ajusteCampos">
                        <label for="tipoSinal">Escolha o tipo de sinal:</label>
                        <input class="form-control" id="tipoSinal" name="tipoSinal" value="${tipoSinal}" disabled>
                    </div>

                    <div class="col-4 form-group ajusteCampos">
                        <label for="frequenciaFundamental">Frequência fundamental (kHz):</label>
                        <input type="number" class="form-control" id="freqFundamental"
                               name="freqFundamental" min="1" max="100" value="${freqFundamental}" disabled>
                    </div>

                </div>
                <div class="row ajusteCampos">
                    <div class="col-4 form-group ajusteCampos" id="campoTipoCanal">
                        <label for="tipoCanal">Escolha o tipo de canal:</label>
                        <input class="form-control" id="tipoCanal" name="tipoCanal" onchange="habilitaFreqCorteInferior(this);" value="${tipoCanal}" disabled>
                    </div>

                    <div class="col-4 form-group ajusteCampos" id="campoFreqCorteSuperior">
                        <label for="frequenciaCorte2" id="lblFreqCorteSuperior">Frequência de corte (kHz):</label>
                        <input type="number" class="form-control" id="freqCorteSuperior" name="freqCorteSuperior" min="1"
                               max="100" value="${freqCorteSuperior}" disabled>
                    </div>

                    <div class="col-4 form-group ajusteCampos" id="campoFreqCorteInferior" style="display:none;">
                        <label for="frequenciaCorte1">Frequência de corte inferior (kHz):</label>
                        <input type="number" class="form-control" id="freqCorteInferior" name="freqCorteInferior" min="1"
                               max="100" value="${freqCorteInferior}" disabled>
                    </div>
                </div>
            </form>

            <br>
        </div>

        <section class="chart-section row" style="margin: 2% 10% 2% 10%">
            <div class="side-by-side-charts col-6">
                <div>
                    <h2>Sinal Entrada</h2>
                    <canvas id="SinalEntrada" width="300" height="200"></canvas>
                </div>
            </div>

            <div class='side-by-side-charts col-6'>
                <div>
                    <h2>Sinal Saida</h2>
                    <canvas id="SinalSaida" width="300" height="200"></canvas>
                </div>
            </div>
        </section>

        <section class="chart-section row" style="margin: 2% 10% 2% 10%">
            <div class="side-by-side-charts col-4">
                <div>
                    <h2>Amplitude Entrada</h2>
                    <canvas id="AmplitudeEntrada" width="300" height="200"></canvas>
                </div>
                <div>
                    <h2>Fase Entrada</h2>
                    <canvas id="FaseEntrada" width="300" height="200"></canvas>
                </div>
            </div>

            <br>
            <br>

            <div class="side-by-side-charts col-4">
                <div>
                    <h2>Módulo X Frequência</h2>
                    <canvas id="GanhoAmplitude" width="300" height="200"></canvas>
                </div>
                <div>
                    <h2>Fase X Frequência</h2>
                    <canvas id="ContribuicaoFase" width="300" height="200"></canvas>
                </div>
            </div>

            <br>
            <br>

            <div class="side-by-side-charts col-4">
                <div>
                    <h2>Amplitude Saída</h2>
                    <canvas id="AmplitudeSaida" width="300" height="200"></canvas>
                </div>
                <div>
                    <h2>Fase Saída</h2>
                    <canvas id="FaseSaida" width="300" height="200"></canvas>
                </div>
            </div>
        </section>

        <script>
            //verifica o Tipo do Canal e habilita o campo de Frequencia de Corte Inferior
            document.addEventListener('DOMContentLoaded', function () {
            var campoFreqCorteInferior = document.getElementById('campoFreqCorteInferior');
            var tipoCanal = document.getElementById('tipoCanal');
            var labelFreqCorteSup = document.getElementById("lblFreqCorteSuperior");
            var campoTipoCanal = document.getElementById("campoTipoCanal");
            if (tipoCanal.value == "Canal Passa-Faixas") {
            campoFreqCorteInferior.style.display = 'block';
            labelFreqCorteSup.textContent = "Frequência de corte superior (kHz):";
            } else {
            campoFreqCorteInferior.style.display = 'none';
            labelFreqCorteSup.textContent = "Frequência de corte (kHz):";
            }
            });
            // Dados calculados no servidor
            var tempo = "${tempo}".split(',').map(Number);
            var sinalEntrada = "${sinalEntrada}".split(',').map(Number);
            var sinalSaida = "${sinalSaida}".split(',').map(Number);
            var freqSinalEntrada = "${freqSinalEntrada}".split(',').map(Number);
            var amplitudeEntrada = "${amplitudeEntrada}".split(',').map(Number);
            var amplitudeSaida = "${amplitudeSaida}".split(',').map(Number);
            var faseEntrada = "${faseEntrada}".split(',').map(Number);
            var faseSaida = "${faseSaida}".split(',').map(Number);
            var ganhoAmplitude = "${ganhoAmplitude}".split(',').map(Number);
            var contribuicaoFase = "${contribuicaoFase}".split(',').map(Number);
            // Gráfico - Sinal Entrada e Saida (tempo, Sinal)
//            var ctx_sinalEntradaSaida = document.getElementById('SinalEntradaSaida').getContext('2d');
//            var SinalEntradaSaida = new Chart(ctx_sinalEntradaSaida, {
//            type: 'line',
//                    data: {
//                    labels: tempo,
//                            datasets: [
//                            {
//                            label: 'Sinal Entrada',
//                                    data: sinalEntrada,
//                                    backgroundColor: 'rgba(75, 192, 192, 0.2)',
//                                    borderColor: 'rgba(75, 192, 192, 1)',
//                                    borderWidth: 1
//                            },
//                            {
//                            label: 'Sinal Saida',
//                                    data: sinalSaida,
//                                    backgroundColor: 'rgba(255, 0, 0, 0.66)',
//                                    borderColor: 'rgba(255, 0, 0, 0.66)',
//                                    borderWidth: 1
//                            }
//                            ]
//                    },
//                    options: {
//                    scales: {
//                    x: {
//                    type: 'linear',
//                            position: 'bottom',
//                            title: {
//                            display: true,
//                                    text: 'Tempo'
//                            }
//                    },
//                            y: {
//                            type: 'linear',
//                                    position: 'left',
//                                    title: {
//                                    display: true,
//                                            text: 'Amplitude'
//                                    }
//                            }
//                    }
//                    }
//            });
            // Gráfico - Amplitude Entrada
            var ctx_amplitudeEntrada = document.getElementById('AmplitudeEntrada').getContext('2d');
            var AmplitudeEntrada = new Chart(ctx_amplitudeEntrada, {
            type: 'bar',
                    data: {
                    labels: freqSinalEntrada,
                            datasets: [{
                            label: 'Amplitude Entrada',
                                    data: amplitudeEntrada,
                                    backgroundColor: 'rgba(75, 192, 192, 0.2)',
                                    borderColor: 'rgba(75, 192, 192, 1)',
                                    borderWidth: 1
                            }]
                    },
                    options: {
                    scales: {
                    x: {
                    type: 'linear',
                            position: 'bottom',
                            title: {
                            display: true,
                                    text: 'Frequência'
                            }
                    },
                            y: {
                            type: 'linear',
                                    position: 'left',
                                    title: {
                                    display: true,
                                            text: 'Amplitude'
                                    }
                            }
                    }
                    }
            });
            // Gráfico - Fase Entrada
            var ctx_faseEntrada = document.getElementById('FaseEntrada').getContext('2d');
            var FaseEntrada = new Chart(ctx_faseEntrada, {
            type: 'bar',
                    data: {
                    labels: freqSinalEntrada,
                            datasets: [{
                            label: 'Fase Entrada',
                                    data: faseEntrada,
                                    backgroundColor: 'rgba(75, 192, 192, 0.2)',
                                    borderColor: 'rgba(75, 192, 192, 1)',
                                    borderWidth: 1
                            }]
                    },
                    options: {
                    scales: {
                    x: {
                    type: 'linear',
                            position: 'bottom',
                            title: {
                            display: true,
                                    text: 'Frequência'
                            }
                    },
                            y: {
                            type: 'linear',
                                    position: 'left',
                                    title: {
                                    display: true,
                                            text: 'Fase'
                                    }
                            }
                    }
                    }
            });
            // Gráfico - Ganho Amplitude
            var ctx_ganhoAmplitude = document.getElementById('GanhoAmplitude').getContext('2d');
            var GanhoAmplitude = new Chart(ctx_ganhoAmplitude, {
            type: 'line',
                    data: {
                    labels: freqSinalEntrada,
                            datasets: [{
                            label: 'Módulo de Resposta',
                                    data: ganhoAmplitude,
                                    backgroundColor: 'rgba(211, 15, 214, 0.2)',
                                    borderColor: 'rgba(211, 15, 214, 1)',
                                    borderWidth: 1
                            }]
                    },
                    options: {
                    scales: {
                    x: {
                    type: 'linear',
                            position: 'bottom',
                            title: {
                            display: true,
                                    text: 'Frequência'
                            }
                    },
                            y: {
                            type: 'linear',
                                    position: 'left',
                                    title: {
                                    display: true,
                                            text: 'Módulo de Resposta'
                                    }
                            }
                    }
                    }
            });
            // Gráfico - Contribuição Fase
            var ctx_contribuicaoFase = document.getElementById('ContribuicaoFase').getContext('2d');
            var ContribuicaoFase = new Chart(ctx_contribuicaoFase, {
            type: 'line',
                    data: {
                    labels: freqSinalEntrada,
                            datasets: [{
                            label: 'Fase de Resposta',
                                    data: contribuicaoFase,
                                    backgroundColor: 'rgba(211, 15, 214, 0.2)',
                                    borderColor: 'rgba(211, 15, 214, 1)',
                                    borderWidth: 1
                            }]
                    },
                    options: {
                    scales: {
                    x: {
                    type: 'linear',
                            position: 'bottom',
                            title: {
                            display: true,
                                    text: 'Frequência'
                            }
                    },
                            y: {
                            type: 'linear',
                                    position: 'left',
                                    title: {
                                    display: true,
                                            text: 'Fase de Resposta'
                                    }
                            }
                    }
                    }
            });
            // Gráfico - Amplitude Saída
            var ctx_amplitudeSaida = document.getElementById('AmplitudeSaida').getContext('2d');
            var AmplitudeSaida = new Chart(ctx_amplitudeSaida, {
            type: 'bar',
                    data: {
                    labels: freqSinalEntrada,
                            datasets: [{
                            label: 'Amplitude Saída',
                                    data: amplitudeSaida,
                                    backgroundColor: 'rgba(255, 0, 0, 0.66)',
                                    borderColor: 'rgba(255, 0, 0, 0.66)',
                                    borderWidth: 1
                            }]
                    },
                    options: {
                    scales: {
                    x: {
                    type: 'linear',
                            position: 'bottom',
                            title: {
                            display: true,
                                    text: 'Frequência'
                            }
                    },
                            y: {
                            type: 'linear',
                                    position: 'left',
                                    title: {
                                    display: true,
                                            text: 'Amplitude'
                                    }
                            }
                    }
                    }
            });
            // Gráfico - Fase Saída
            var ctx_faseSaida = document.getElementById('FaseSaida').getContext('2d');
            var FaseSaida = new Chart(ctx_faseSaida, {
            type: 'bar',
                    data: {
                    labels: freqSinalEntrada,
                            datasets: [{
                            label: 'Fase Saída',
                                    data: faseSaida,
                                    backgroundColor: 'rgba(255, 0, 0, 0.66)',
                                    borderColor: 'rgba(255, 0, 0, 0.66)',
                                    borderWidth: 1
                            }]
                    },
                    options: {
                    scales: {
                    x: {
                    type: 'linear',
                            position: 'bottom',
                            title: {
                            display: true,
                                    text: 'Frequência'
                            }
                    },
                            y: {
                            type: 'linear',
                                    position: 'left',
                                    title: {
                                    display: true,
                                            text: 'Fase'
                                    }
                            }
                    }
                    }
            });
            // Gráfico - Sinal Entrada ao longo do tempo
            var ctx_sinalEntrada = document.getElementById('SinalEntrada').getContext('2d');
            var SinalEntrada = new Chart(ctx_sinalEntrada, {
            type: 'line',
                    data: {
                    labels: tempo,
                            datasets: [{
                            label: 'Sinal Entrada',
                                    data: sinalEntrada,
                                    backgroundColor: 'rgba(75, 192, 192, 0.2)',
                                    borderColor: 'rgba(75, 192, 192, 1)',
                                    borderWidth: 1
                            }]
                    },
                    options: {
                    scales: {
                    x: {
                    type: 'linear',
                            position: 'bottom',
                            title: {
                            display: true,
                                    text: 'Tempo'
                            }
                    },
                            y: {
                            type: 'linear',
                                    position: 'left',
                                    title: {
                                    display: true,
                                            text: 'Amplitude'
                                    }
                            }
                    }
                    }
            });
            // Gráfico - Sinal Saida ao longo do tempo
            var ctx_sinalSaida = document.getElementById('SinalSaida').getContext('2d');
            var SinalSaida = new Chart(ctx_sinalSaida, {
            type: 'line',
                    data: {
                    labels: tempo,
                            datasets: [{
                            label: 'Sinal Saida',
                                    data: sinalSaida,
                                    backgroundColor: 'rgba(255, 0, 0, 0.66)',
                                    borderColor: 'rgba(255, 0, 0, 0.66)',
                                    borderWidth: 1
                            }]
                    },
                    options: {
                    scales: {
                    x: {
                    type: 'linear',
                            position: 'bottom',
                            title: {
                            display: true,
                                    text: 'Tempo'
                            }
                    },
                            y: {
                            type: 'linear',
                                    position: 'left',
                                    title: {
                                    display: true,
                                            text: 'Amplitude'
                                    }
                            }
                    }
                    }
            });
        </script>

        <br>
        <br>
        
        <footer id="navBar" class="footer text-white text-center fixed-bottom">
            © 2024 Simulador de Canal de Comunicações
        </footer>

        <!-- scripts do Bootstrap -->
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap></script>
