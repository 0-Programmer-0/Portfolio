package br.edu.fesa.transformdados;

public class TransformDados {

    public static void main(String[] args) {              
        String url = "https://www.gov.br/ans/pt-br/acesso-a-informacao/participacao-da-sociedade/atualizacao-do-rol-de-procedimentos";
        String ext = "a[href$=.xls], a[href$=.xlsx]";
        WebScraper.GetFiles(url, ext);
        //ExcelScraper()
    }
}
