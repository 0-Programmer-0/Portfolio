/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.function.Function;
import java.util.ArrayList;
import java.util.List;
import java.util.function.DoubleUnaryOperator;

/**
 *
 * @author nzxtt
 */
public class OndaSenoidal {

    private double amplitude;
    private double frequencia;
    private double tempo;
    private double fase;
    private double periodo;
    //private double formulaparaintegrar;
    private double azero;
    private double aN;
    private double bN;
    private double faseN;
    private double AmplitudeN;
    public int numeroHarmonica;
    public double[] pontosPlotagem = new double[1000];
    
    List<OndaSenoidal> listaDeOndas = new ArrayList<>();

    //private Function<Double, Double> f;
    //DoubleUnaryOperator f1 = x -> Math.abs(Math.cos(2 * Math.PI * frequencia * tempo));
    //DoubleUnaryOperator f2 = x -> frequencia ; // f(x) = sin(x)
        
    
    void CalculaAzinho(OndaSenoidal onda){
        for(int i = 0; i <= 100; i++){
//            if(i = 0){
//                ;
//            }
        }
    }

    public OndaSenoidal(double amplitude, double frequencia, double tempo, double fase) {
        this.amplitude = amplitude;
        this.frequencia = frequencia;
        this.tempo = tempo;
        this.fase = fase;
        this.periodo = 1 / frequencia;
        //this.f = x -> Math.abs(Math.cos(2 * Math.PI * frequencia * tempo));
        for(int i = 0; i<= 100; i++){
            //listaDeOndas.add(amplitude, frequencia, tempo, fase);
            listaDeOndas.get(i).numeroHarmonica = i;
            listaDeOndas.get(i).CalculaAzinho(listaDeOndas.get(i));
        }
        
    }
    
        public double getPeriodo() {
        return periodo;
    }

    public void setPeriodo(double periodo) {
        this.periodo = periodo;
    }

//    public Function<Double, Double> getF() {
//        return f;
//    }
//
//    public void setF(Function<Double, Double> f) {
//        this.f = f;
//    }
    
    public double getAmplitude() {
        return amplitude;
    }

    public void setAmplitude(double amplitude) {
        this.amplitude = amplitude;
    }

    public double getFrequencia() {
        return frequencia;
    }

    public void setFrequencia(double frequencia) {
        this.frequencia = frequencia;
    }

    public double getTempo() {
        return tempo;
    }

    public void setTempo(double tempo) {
        this.tempo = tempo;
    }

    public double getFase() {
        return fase;
    }

    public void setFase(double fase) {
        this.fase = fase;
    }

    
    
}
