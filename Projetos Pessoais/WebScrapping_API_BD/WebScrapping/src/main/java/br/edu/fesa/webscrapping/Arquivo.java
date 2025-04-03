package br.edu.fesa.webscrapping;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Arquivo {
    public static void downloadPdf(String pdfUrl, File destinationFile) {
        // Fechamento automático da conexão
        try (InputStream in = new URL(pdfUrl).openStream();
             OutputStream out = new FileOutputStream(destinationFile)) {

            // Leitura dos dados e download do arquivo
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void zipFiles(File folder) throws IOException {
        // Cria o ZIP dentro da pasta local criada para salvar os arquivos
        File zipFile = new File(folder, "anexos.zip");

        // Fechamento automático da conexão
        try (FileOutputStream fos = new FileOutputStream(zipFile);
             ZipOutputStream zipOut = new ZipOutputStream(fos)) {
            
            //Le todos os arquivos da pasta local
            File[] files = folder.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.getName().endsWith(".pdf")) {
                        // Introduz ao ZIP apenas arquivos PDFs
                        try (FileInputStream fis = new FileInputStream(file)) {
                            ZipEntry zipEntry = new ZipEntry(file.getName());
                            zipOut.putNextEntry(zipEntry);

                            // Compactação do arquivo
                            byte[] buffer = new byte[1024];
                            int len;
                            while ((len = fis.read(buffer)) > 0) {
                                zipOut.write(buffer, 0, len);
                            }

                            // Finaliza a inserção de um arquivo dentro do ZIP
                            zipOut.closeEntry();
                        }
                    }
                }
            }
        }
    }
}
