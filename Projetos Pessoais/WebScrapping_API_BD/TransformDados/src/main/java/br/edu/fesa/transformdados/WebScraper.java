/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.fesa.transformdados;

import java.io.File;
import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author Johns
 */
public class WebScraper {
    public static void GetFiles(String url, String ext) {

        // Criação de pasta local
        File downloadDir = new File("downloads");
        if (!downloadDir.exists()) {
            downloadDir.mkdir();
        }
        
        try {
            // Conexão com a URL
            Document doc = Jsoup.connect(url).get();
            
            //Seleção apenas dos elementos que tenham a extensão .pdf
            Elements files = doc.select(ext);
            
            // Análise e download de todos os PDFs "Anexo" encontrados
            for (Element file : files) {
                if(file.text().toLowerCase().contains("anexo")) {                   
                    String fileUrl = file.absUrl("href");
                    String fileName = fileUrl.substring(fileUrl.lastIndexOf("/") + 1);
                    Arquivo.download(fileUrl, new File (downloadDir, fileName));
                    File dirInput = new File(downloadDir + "/" + fileName);  // Caminho do arquivo Excel baixado
                    
                    String newFileName = "";
                    if(fileName.contains(".xlsx"))
                        newFileName = fileName.replace(".xlsx", ".csv");
                    else if (fileName.contains(".xls"))
                        newFileName = fileName.replace(".xlsx", ".csv");
                    
                    File dirOutput = new File(downloadDir + "/" + newFileName);  // Caminho onde o arquivo CSV será salvo
                    ExcelScraper.excelToCsv(dirInput, dirOutput);
                }  
            }
            
            Arquivo.zipFiles(downloadDir);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
