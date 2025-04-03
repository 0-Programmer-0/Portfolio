/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import jakarta.servlet.annotation.WebServlet;

@WebServlet(urlPatterns = {"/OndaDenteDeSerra"})
public class OndaDenteDeSerra {

    public static double[] generateSawtoothWave(int frequency, int sampleRate, int duration) {
        int numSamples = sampleRate * duration;
        double[] wave = new double[numSamples];
        double period = (double) sampleRate / frequency;

        for (int i = 0; i < numSamples; i++) {
            wave[i] = 2 * (i % period) / period - 1;  // Normaliza entre -1 e 1
        }
        return wave;
    }

    public static void main(String[] args) {
        int frequency = 440; // Frequência em Hz
        int sampleRate = 44100; // Taxa de amostragem em Hz
        int duration = 2; // Duração em segundos

        double[] sawtoothWave = generateSawtoothWave(frequency, sampleRate, duration);

        // Exemplo de saída para verificação
        for (int i = 0; i < 100; i++) {
            System.out.println(sawtoothWave[i]);
        }
    }
}
