package it.unipv.ingsw.UniBook.Controller;

import it.unipv.ingsw.UniBook.Model.CondivisioneModel;
import it.unipv.ingsw.UniBook.Model.Resource;
import it.unipv.ingsw.UniBook.Model.SingletonManager;
import it.unipv.ingsw.UniBook.View.CondivisioneView;
import it.unipv.ingsw.UniBook.View.FileDownloadFrame;
import it.unipv.ingsw.UniBook.View.FileSelectionFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

public class CondivisioneController {

	private CondivisioneView view;
	private CondivisioneModel model;
	private FileSelectionFrame fs;
	private FileDownloadFrame df;

	public CondivisioneController(CondivisioneView view, CondivisioneModel model, FileSelectionFrame fs,
			FileDownloadFrame df) {
		this.view = view;
		this.model = model;
		this.fs = fs;
		this.df = df;

		initComponents();
	}

	private void initComponents() {

		ActionListener UploadButtonListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				manageAction();
			}

			private void manageAction() {

				showFileSelectionFrame();

			}
		};

		view.getUploadButton().addActionListener(UploadButtonListener);

		ActionListener DownloadButtonListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				manageAction();
			}

			private void manageAction() {

				tryToDownloadFile();

			}
		};

		view.getDownloadButton().addActionListener(DownloadButtonListener);

		ActionListener InsertFile = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				manageAction();
			}

			private void manageAction() {

				File selectedFile = fs.getSelectedFile();
				inserisciFile(selectedFile);

			}
		};

		fs.getUploadButton().addActionListener(InsertFile);

		ActionListener SelectFileToUpload = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				manageAction();
			}

			private void manageAction() {

				File selectedFile = fs.showFileChooser();

				// Popola i campi del file selezionato nei campi di testo nella CondivisioneView
				if (selectedFile != null) {
					String fileName = selectedFile.getName();
					String fileExtension = model.getFileExtension(fileName);
				}

			}
		};

		fs.getSelectButton().addActionListener(SelectFileToUpload);

		ActionListener dw = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				manageAction();
			}

			private void manageAction() {

				df.confermaDownload();

			}
		};

		df.getDownloadFrameButton().addActionListener(dw);
		;

	}

	private void showFileSelectionFrame() {
		fs.setVisible(true);
	}

	// Metodo per provare a caricare il file nel modello
	private void inserisciFile(File selectedFile) {
		String fileName = selectedFile.getName();
		String fileNameWithoutExtension = model.removeFileExtension(fileName);
		String fileExtension = model.getFileExtension(fileName);

		// Passa il nome del file e l'estensione al modello
		model.tryToUploadFile(selectedFile, fileNameWithoutExtension, fileExtension);
	}

	public void tryToDownloadFile() {
		ArrayList<Resource> fileResources = SingletonManager.getInstance().getResourceDAO().getResourceFile();
		df.setVisible(true);
	}

}