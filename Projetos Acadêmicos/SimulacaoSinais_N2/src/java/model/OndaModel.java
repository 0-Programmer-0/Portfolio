package model;

import java.util.ArrayList;
import java.util.List;

public class OndaModel {

    //campos da home
    private int freqFundamental;
    private String tipoSinal;
    private String tipoCanal;
    private int freqCorteSuperior;
    private int freqCorteInferior;

    //variaveis dos calculos
    private List<Double> tempo = new ArrayList<>();
    private List<Double> sinalEntrada = new ArrayList<>();
    private List<Double> sinalSaida = new ArrayList<>();
    private List<Double> freqSinalEntrada = new ArrayList<>();
    private List<Double> amplitudeEntrada = new ArrayList<>();
    private List<Double> amplitudeSaida = new ArrayList<>();
    private List<Double> faseEntrada = new ArrayList<>();
    private List<Double> faseSaida = new ArrayList<>();
    private List<Double> ganhoAmplitude = new ArrayList<>();
    private List<Double> contribuicaoFase = new ArrayList<>();

    // Getters and Setters  
    public int getFreqFundamental() {
        return freqFundamental;
    }

    public void setFreqFundamental(int freqFundamental) {
        this.freqFundamental = freqFundamental;
    }

    public int getFreqCorteSuperior() {
        return freqCorteSuperior;
    }

    public void setFreqCorteSuperior(int freqCorteSuperior) {
        this.freqCorteSuperior = freqCorteSuperior;
    }

    public String getTipoSinal() {
        return tipoSinal;
    }

    public void setTipoSinal(String tipoSinal) {
        this.tipoSinal = tipoSinal;
    }

    public String getTipoCanal() {
        return tipoCanal;
    }

    public void setTipoCanal(String tipoCanal) {
        this.tipoCanal = tipoCanal;
    }

    public int getFreqCorteInferior() {
        return freqCorteInferior;
    }

    public void setFreqCorteInferior(int freqCorteInferior) {
        this.freqCorteInferior = freqCorteInferior;
    }

    public List<Double> getTempo() {
        return tempo;
    }

    public List<Double> getSinalEntrada() {
        return sinalEntrada;
    }

    public List<Double> getSinalSaida() {
        return sinalSaida;
    }

    public List<Double> getFreqSinalEntrada() {
        return freqSinalEntrada;
    }

    public List<Double> getAmplitudeEntrada() {
        return amplitudeEntrada;
    }

    public List<Double> getAmplitudeSaida() {
        return amplitudeSaida;
    }

    public List<Double> getFaseEntrada() {
        return faseEntrada;
    }

    public List<Double> getFaseSaida() {
        return faseSaida;
    }

    public List<Double> getGanhoAmplitude() {
        return ganhoAmplitude;
    }

    public List<Double> getContribuicaoFase() {
        return contribuicaoFase;
    }

    public void calcularOndas() {
        double periodo = 1.0 / freqFundamental;
        double intervaloFinal = 4 * periodo;

        //melhora a precisao da onda
        double passo = periodo / 1000;

        int harmonicas = 100;
        double aZero = 0;
        double fase = -Math.PI / 2;

        switch (tipoSinal) {
            case "senoidalRetificada":
                aZero = 2.0 / Math.PI;
                fase = 0;
                break;
            default:
                aZero = 0;
                break;
        }

        for (double i = 0; i <= intervaloFinal; i += passo) {
            tempo.add(i);
        }

        for (int n = 0; n <= harmonicas; n++) {
            freqSinalEntrada.add(n * (double) freqFundamental);
        }

        for (double t : tempo) {
            double entrada = aZero;
            double saida = aZero;
            double amplitude = 0;

            //Amplitude na harmonica primordial - A0
            amplitudeEntrada.add(aZero);
            amplitudeSaida.add(aZero);
            ganhoAmplitude.add(1.0);

            //Fase na harmonica primordial - A0
            faseEntrada.add(0.0);
            faseSaida.add(0.0);
            contribuicaoFase.add(0.0);

            //comeca em 1, porque aZero ja representa uma harmonica
            for (int n = 1; n <= harmonicas; n++) {
                switch (tipoSinal) {
                    case "senoidalRetificada" ->
                        amplitude = calculaAnSenoidal(n);
                    case "quadrada" ->
                        amplitude = calculaAnQuadrado(n);
                    case "triangular" ->
                        amplitude = calculaAnTriangular(n);
                    case "denteDeSerra" ->
                        amplitude = calculaAnDenteSerra(n);
                    default -> {
                    }
                }

                amplitudeEntrada.add(amplitude);

                if (n % 2 != 0) {
                    faseEntrada.add(Math.toDegrees(fase));
                } else {
                    faseEntrada.add(0.0);
                }

                entrada += amplitude * Math.cos(2 * Math.PI * n * freqFundamental * t + fase);

                double moduloResposta;
                double faseResposta;

                if (getTipoCanal().equals("passaBaixas")) {
                    moduloResposta = calculoModuloRespostaFrequenciaPB(freqFundamental * n, freqCorteSuperior);
                    faseResposta = calculoFaseRespostaFrequenciaPB(freqFundamental * n, freqCorteSuperior);
                } else {
                    moduloResposta = calculoModuloRespostaFrequenciaPF(freqFundamental * n, freqCorteSuperior, freqCorteInferior);
                    faseResposta = calculoFaseRespostaFrequenciaPF(freqFundamental * n, freqCorteSuperior, freqCorteInferior);
                }

                saida += (amplitude * moduloResposta * Math.cos(2 * Math.PI * n * freqFundamental * t + fase + faseResposta));

                if (ganhoAmplitude.size() <= harmonicas) {
                    ganhoAmplitude.add(moduloResposta);
                }

                if (amplitudeSaida.size() <= harmonicas) {
                    amplitudeSaida.add(amplitude * moduloResposta);
                }

                if (faseSaida.size() <= harmonicas) {
                    if (n % 2 != 0) {
                        faseSaida.add(Math.toDegrees(fase + faseResposta));
                    } else {
                        faseSaida.add(0.0);
                    }
                }

                if (contribuicaoFase.size() <= harmonicas) {
                    contribuicaoFase.add(Math.toDegrees(faseResposta));
                }
            }
            sinalEntrada.add(entrada);
            sinalSaida.add(saida);
        }
    }

    private double calculaAnSenoidal(int n) {
        //return 4 / ((4 * Math.pow(n, 2) - 1) * Math.PI);
        if(n % 2 == 0)
            return 4 / (Math.PI * (1 - Math.pow(n, 2)));
        else
            return 0.0;
    }

    private double calculaAnQuadrado(int n) {
        return n % 2 == 0 ? 0 : 4 / (Math.PI * n);
    }

    private double calculaAnTriangular(int n) {
        if(n % 2 == 0) return 0;
        
        double amplitude = 8 / Math.pow((Math.PI*n), 2);
        
        if((n-1)/2 % 2 != 0)
            amplitude = -amplitude;
        
        return amplitude;
    }

    private double calculaAnDenteSerra(int n) {
        return (2 * Math.pow(-1, (n+1))) / (Math.PI * n);
    }

    //Passa-Baixas
    private double calculoModuloRespostaFrequenciaPB(double freqHarmonica, double freqCorte) {
        double moduloRespostaFrequencia = 0;
        moduloRespostaFrequencia = 1 / (Math.sqrt(1 + (Math.pow((freqHarmonica / freqCorte), 2))));
        return moduloRespostaFrequencia;
    }

    private double calculoFaseRespostaFrequenciaPB(double freqHarmonica, double freqCorte) {
        double faseRespostaFrequencia = 0;
        faseRespostaFrequencia = -Math.atan2(freqHarmonica, freqCorte);
        return faseRespostaFrequencia;
    }

    //Passa-Faixas
    private double calculoModuloRespostaFrequenciaPF(double freqHarmonica, double freqCorteSuperior, double freqCorteInferior) {
        double moduloRespostaFrequencia = 0;
        double termo1Raiz = 1 + Math.pow((freqHarmonica / freqCorteInferior), 2);
        double termo2Raiz = 1 + Math.pow((freqHarmonica / freqCorteSuperior), 2);

        moduloRespostaFrequencia = (1 / freqCorteInferior) * (freqHarmonica / Math.sqrt(termo1Raiz * termo2Raiz));
        return moduloRespostaFrequencia;
    }

    private double calculoFaseRespostaFrequenciaPF(double freqHarmonica, double freqCorteSuperior, double freqCorteInferior) {
        double faseRespostaFrequencia = 0;
        double numerador = freqHarmonica * (freqCorteInferior + freqCorteSuperior);
        double denominador = (freqCorteInferior * freqCorteSuperior) - Math.pow(freqHarmonica, 2);

        faseRespostaFrequencia = -(Math.PI / 2) - Math.atan2(numerador, denominador);
        return faseRespostaFrequencia;
    }

    public String formatList(List<Double> list) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i));
            if (i < list.size() - 1) {
                sb.append(",");
            }
        }
        return sb.toString();
    }
}
