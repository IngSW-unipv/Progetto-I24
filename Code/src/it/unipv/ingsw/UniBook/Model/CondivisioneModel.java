package it.unipv.ingsw.UniBook.Model;

public class CondivisioneModel {

    public boolean verificaEstensione(String fileName) {

    	String extension = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
        return extension.equals("txt") || extension.equals("pdf") || extension.equals("docx") || extension.equals("pptx");
    }

   /* public boolean verificaDimensione(long fileSize) {
       
    	return fileSize <= 100 * 1024 * 1024; 
    }*/
  
    public void simulaDownload() throws InterruptedException {
       
    	Thread.sleep(2000); 
    }
}