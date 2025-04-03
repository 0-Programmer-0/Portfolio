package br.edu.fesa.webscrapping;

// Biblioteca Jsoup - WebScrapping
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

// Biblioteca tratamento de exceção
import java.io.*;

public class WebScrapping {

    public static void main(String[] args) {
        String url = "https://www.gov.br/ans/pt-br/acesso-a-informacao/participacao-da-sociedade/atualizacao-do-rol-de-procedimentos";

        // Criação de pasta local
        File downloadDir = new File("downloads");
        if (!downloadDir.exists()) {
            downloadDir.mkdir();
        }
        
        try {
            // Conexão com a URL
            Document doc = Jsoup.connect(url).get();
            
            //Seleção apenas dos elementos que tenham a extensão .pdf
            Elements pdfs = doc.select("a[href$=.pdf]");
            
            // Análise e download de todos os PDFs "Anexo" encontrados
            for (Element pdf : pdfs) {
                if(pdf.text().toLowerCase().contains("anexo")) {                   
                    String pdfUrl = pdf.absUrl("href");
                    String fileName = pdfUrl.substring(pdfUrl.lastIndexOf("/") + 1);
                    Arquivo.downloadPdf(pdfUrl, new File (downloadDir, fileName));
                }  
            }
            
            Arquivo.zipFiles(downloadDir);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
