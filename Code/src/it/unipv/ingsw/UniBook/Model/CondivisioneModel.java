package it.unipv.ingsw.UniBook.Model;

public class CondivisioneModel {

    public boolean verificaEstensione(String fileName) {
        // Estrai l'estensione del file
        String extension = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
        
        // Verifica se l'estensione è consentita
        return extension.equals("txt") || extension.equals("pdf") || extension.equals("docx");
    }

    public boolean verificaDimensione(long fileSize) {
        // Verifica se la dimensione del file è minore o uguale a 100 MB
        return fileSize <= 100 * 1024 * 1024; // 100 MB in byte
    }

    public void simulaDownload() throws InterruptedException {
        // Simula il download del file
        Thread.sleep(2000); // Simula un ritardo di 2 secondi
    }
}