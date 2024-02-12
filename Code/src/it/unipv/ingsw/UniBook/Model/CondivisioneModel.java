package it.unipv.ingsw.UniBook.Model;

import java.util.Random;

public class CondivisioneModel {

    public boolean verificaDimensioniFile(String file) {
        // Simulazione: dimensione massima consentita
        int maxSize = 100; // Dimensione massima consentita in MB
        // Simulazione: dimensione casuale del file
        int fileSize = new Random().nextInt(150); // Dimensione del file in MB
        return fileSize <= maxSize;
    }

    public boolean verificaPermessiUtente(String utente) {
        // Simulazione: verifica se l'utente ha i permessi
        return utente.equals("admin");
    }

    public boolean verificaConnessione() {
        // Simulazione: verifica se la connessione è disponibile
        return new Random().nextBoolean();
    }

    public void simulaDownload() throws InterruptedException {
        // Simulazione: download che dura per qualche secondo
        Thread.sleep(3000);
    }
}
