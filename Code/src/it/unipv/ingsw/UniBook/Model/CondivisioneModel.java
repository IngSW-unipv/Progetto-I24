package it.unipv.ingsw.UniBook.Model;

import java.io.File;


import javax.swing.JOptionPane;
import it.unipv.ingsw.UniBook.Exception.PopupManager;

public class CondivisioneModel {


	public CondivisioneModel() {

	}

	public void tryToUploadFile(File selectedFile, String fileNameWithoutExtension, String fileExtension) {

		try {

			if (!fileNameWithoutExtension.equals(".")) {

				// Verifica se l'estensione del file è supportata
				if (!verificaEstensione(fileExtension)) {
					throw new Exception("Estensione del file non supportata.");
				}

				int result = JOptionPane.showConfirmDialog(null, "Sei sicuro di voler caricare il file?",
						"Conferma Upload", JOptionPane.YES_NO_OPTION);
				if (result == JOptionPane.YES_OPTION) {

					// Creazione dell'oggetto Resource con i dati del file
					Resource resource = new Resource();
					resource.setNome(fileNameWithoutExtension);
					resource.setDescrizione(fileExtension);
					resource.setIndirizzo("");
					resource.setTipo("F");

					User loggedUser = SingletonManager.getInstance().getLoggedUser();
					if (loggedUser != null) {
						resource.setIndirizzo(loggedUser.getCorso());
						resource.setMatricola_inserimento(loggedUser.getId());
					} else {
						throw new Exception("Utente non loggato.");
					}

					boolean insertSuccess = SingletonManager.getInstance().getResourceDAO().insertFile(resource);

					if (insertSuccess) {
						PopupManager.showPopup("File inserito con successo!");
					} else {
						JOptionPane.showMessageDialog(null, "Errore durante l'inserimento del file.", "Errore",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			} else {
				throw new Exception("Nessun file selezionato.");
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Errore durante il caricamento del file: " + e.getMessage(), "Errore",
					JOptionPane.ERROR_MESSAGE);
		}

	}

	// Metodo per vedere se il file selezionato é del formato giusto
	private boolean verificaEstensione(String fileExtension) {

		return fileExtension.equals("txt") || fileExtension.equals("pdf") || fileExtension.equals("docx")
				|| fileExtension.equals("pptx");

	}

	// Metodo per ottenere l'estensione del file
	public String getFileExtension(String fileName) {
		int dotIndex = fileName.lastIndexOf('.');
		if (dotIndex > 0 && dotIndex < fileName.length() - 1) {
			return fileName.substring(dotIndex + 1).toLowerCase();
		} else {
			return "";
		}
	}

	// Metodo per rimuovere l'estensione del file dal nome del file
	public String removeFileExtension(String fileName) {
		int dotIndex = fileName.lastIndexOf('.');
		if (dotIndex > 0 && dotIndex < fileName.length() - 1) {
			return fileName.substring(0, dotIndex);
		} else {
			return fileName;
		}
	}
}