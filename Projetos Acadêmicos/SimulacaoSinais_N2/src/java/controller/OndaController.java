/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.OndaModel;

/**
 *
 * @author Johns
 */
@WebServlet("/OndaController")
public class OndaController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //verificacao de campos
        if(request.getParameter("freqFundamental").isBlank() ||
                request.getParameter("freqCorteSuperior").isBlank() || 
                (request.getParameter("tipoCanal").equals("passaFaixas") && request.getParameter("freqCorteInferior").isBlank())) {
            request.getRequestDispatcher("home.html").forward(request, response);
        }
        
        OndaModel model = new OndaModel();
        //campos da home
        int freqFundamental = Integer.parseInt(request.getParameter("freqFundamental"));
        String tipoSinal = request.getParameter("tipoSinal");
        String tipoCanal = request.getParameter("tipoCanal");
        int freqCorteSuperior = Integer.parseInt(request.getParameter("freqCorteSuperior"));
        int freqCorteInferior = 0;
        
        if (tipoCanal.equals("passaFaixas")) {
            freqCorteInferior = Integer.parseInt(request.getParameter("freqCorteInferior"));
        }
        
        model.setTipoSinal(tipoSinal);
        model.setFreqFundamental(freqFundamental);
        model.setTipoCanal(tipoCanal);
        model.setFreqCorteSuperior(freqCorteSuperior);
        model.setFreqCorteInferior(freqCorteInferior);

        model.calcularOndas();
        
        //variaveis da home
        switch(model.getTipoSinal()) {
            case "senoidalRetificada":
                request.setAttribute("tipoSinal", "Onda senoidal retificada");
                //model.calcularOndaSenoidal();
                break;
            case "quadrada":
                request.setAttribute("tipoSinal", "Onda quadrada");
                //model.calcularOndaQuadrada();
                break;
            case "triangular":
                request.setAttribute("tipoSinal", "Onda triangular");
                //model.calcularOndaTriangular();
                break;
            case "denteDeSerra":
                request.setAttribute("tipoSinal", "Onda dente-de-serra");
                //model.calcularOndaDenteSerra();
                break;
            default:
                break;
        }
        
        request.setAttribute("freqFundamental", model.getFreqFundamental());
        
        request.setAttribute("tipoCanal", model.getTipoCanal());
        switch(model.getTipoCanal()) {
            case "passaBaixas":
                request.setAttribute("tipoCanal", "Canal Passa-Baixas");
                break;
            case "passaFaixas":
                request.setAttribute("tipoCanal", "Canal Passa-Faixas");
                break;
            default:
                return;
        }
        
        request.setAttribute("freqCorteSuperior", model.getFreqCorteSuperior());
        request.setAttribute("freqCorteInferior", model.getFreqCorteInferior()); 
        
        //variaveis dos calculos
        request.setAttribute("tempo", model.formatList(model.getTempo()));
        request.setAttribute("sinalEntrada", model.formatList(model.getSinalEntrada()));
        request.setAttribute("sinalSaida", model.formatList(model.getSinalSaida()));
        request.setAttribute("freqSinalEntrada", model.formatList(model.getFreqSinalEntrada()));
        request.setAttribute("amplitudeEntrada", model.formatList(model.getAmplitudeEntrada()));
        request.setAttribute("amplitudeSaida", model.formatList(model.getAmplitudeSaida()));
        request.setAttribute("faseEntrada", model.formatList(model.getFaseEntrada()));
        request.setAttribute("faseSaida", model.formatList(model.getFaseSaida()));
        request.setAttribute("ganhoAmplitude", model.formatList(model.getGanhoAmplitude()));
        request.setAttribute("contribuicaoFase", model.formatList(model.getContribuicaoFase()));

        RequestDispatcher dispatcher = request.getRequestDispatcher("grafico.jsp");
        dispatcher.forward(request, response);
    }

}
