package br.edu.fesa.transformdados;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.*;
import java.util.Iterator;

public class ExcelScraper {
    public static void excelToCsv(File excelFile, File csvFile) throws IOException {
        try (FileInputStream fis = new FileInputStream(excelFile);
            FileWriter writer = new FileWriter(csvFile)) {
            
            Workbook workbook = null;

            // Verifica a extensão do arquivo para determinar se é .xls ou .xlsx
            if (excelFile.getName().endsWith(".xlsx")) {
                workbook = new XSSFWorkbook(fis);  // Para arquivos .xlsx
            } else if (excelFile.getName().endsWith(".xls")) {
                workbook = new HSSFWorkbook(fis);  // Para arquivos .xls antigos
            }

            // Verifica se o arquivo foi carregado corretamente
            if (workbook != null) {
                // Itera por todas as abas (sheets) do arquivo Excel
                for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
                    Sheet sheet = workbook.getSheetAt(i);  // Obtém a aba (sheet) atual

                    // Escreve o nome da aba como título no arquivo CSV
                    writer.write("=== " + sheet.getSheetName() + " ===\n");

                    // Itera pelas linhas da aba
                    Iterator<Row> rowIterator = sheet.iterator();
                    while (rowIterator.hasNext()) {
                        Row row = rowIterator.next();

                        // Itera pelas células da linha
                        Iterator<Cell> cellIterator = row.cellIterator();
                        StringBuilder rowData = new StringBuilder();

                        while (cellIterator.hasNext()) {
                            Cell cell = cellIterator.next();
                            
                            // Verifica o tipo de dado da célula e escreve no CSV
                            switch (cell.getCellType()) {
                                case STRING:
                                    rowData.append(cell.getStringCellValue()).append(";");
                                    break;
                                case NUMERIC:
                                    rowData.append(cell.getNumericCellValue()).append(";");
                                    break;
                                case BOOLEAN:
                                    rowData.append(cell.getBooleanCellValue()).append(";");
                                    break;
                                default:
                                    rowData.append("N/A;");
                            }
                        }

                        // Remove a última vírgula e escreve a linha no CSV
                        if (rowData.length() > 0) {
                            rowData.deleteCharAt(rowData.length() - 1);
                            writer.write(rowData.toString() + "\n");
                        }
                    }
                    writer.write("\n");  // Adiciona uma linha em branco entre as abas
                }

                workbook.close();  // Fecha o workbook após ler
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
    }
}
