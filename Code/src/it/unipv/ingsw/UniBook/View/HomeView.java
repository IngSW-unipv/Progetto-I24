package it.unipv.ingsw.UniBook.View;

import java.awt.*;
import javax.swing.*;


public class HomeView extends JFrame {
	
	private JButton buttonPR;
	private JButton buttonAF;
	private JButton buttonGR;
	private JButton buttonC;
	private JButton buttonF;
	private JButton exitButton;

	public HomeView() {

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(screenSize.width, screenSize.height);
		validate();

		JPanel topCenterPanel = new JPanel();
		JLabel label = new JLabel("UniBook");
		topCenterPanel.setBackground(Color.CYAN);
		topCenterPanel.setPreferredSize(new Dimension(400, 100));
		
		// Aggiungi un JLabel per l'immagine a sinistra
        JLabel imageLabel = new JLabel();
        ImageIcon imageIcon = new ImageIcon("C:\\Users\\Utente\\OneDrive\\Desktop\\Logo.jpeg"); 
        Image image = imageIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH); //Dimensione logo
        ImageIcon resizedIcon = new ImageIcon(image);
        imageLabel.setIcon(resizedIcon);

        // Aggiungi l'immagine a sinistra del pannello superiore
        topCenterPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        topCenterPanel.add(imageLabel);

		Font newFont = new Font(label.getFont().getName(), Font.PLAIN, 50);
		label.setFont(newFont);

		topCenterPanel.setLayout(new GridBagLayout());

		GridBagConstraints constraints1 = new GridBagConstraints();
		constraints1.fill = GridBagConstraints.HORIZONTAL;
		constraints1.gridx = 1;
		constraints1.gridy = 0;
		constraints1.weightx = 0.5;
		constraints1.insets = new Insets(10, 10, 10, 10);
		constraints1.anchor = GridBagConstraints.CENTER;

		topCenterPanel.add(label, constraints1);
		
		GridBagConstraints constraints2 = new GridBagConstraints();
		constraints2.fill = GridBagConstraints.HORIZONTAL;
		constraints2.gridx = 0;
		constraints2.gridy = 0;
		constraints2.weightx = 0.5;
		constraints2.insets = new Insets(10, 10, 10, 10);
		constraints2.anchor = GridBagConstraints.CENTER;
		topCenterPanel.add(imageLabel, constraints2);

		JPanel leftPanel = new JPanel();
		leftPanel.setBackground(new Color(217, 217, 217));
		leftPanel.setLayout(new GridLayout(5, 1, 15, 10));
		leftPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		buttonPR = new JButton("PRENOTA");
		buttonAF = new JButton("AFFITTA");
		buttonGR = new JButton("GESTIONE RISORSE");
		buttonC = new JButton("CHAT");
		buttonF = new JButton("CONDIVISIONE FILE");

		leftPanel.add(buttonPR);
		leftPanel.add(buttonAF);
		leftPanel.add(buttonGR);
		leftPanel.add(buttonC);
		leftPanel.add(buttonF);
		exitButton = new JButton("ESCI");
		
		// Pannello centrale (vuoto)
		JPanel centerPanel = new JPanel();
        centerPanel.setBackground(new Color(37, 150, 170));
		constraints1.anchor = GridBagConstraints.EAST;
		constraints1.weightx = 0.1;
		constraints1.gridx = 4;
		
		topCenterPanel.add(exitButton, constraints1);
		constraints1.gridx = 5;

		add(topCenterPanel, BorderLayout.NORTH);
		add(leftPanel, BorderLayout.WEST);
		add(centerPanel, BorderLayout.CENTER);
		
	}
	
	public JButton getButtonPR() {
		return buttonPR;
	}

	public JButton getButtonAF() {
		return buttonAF;
	}

	public JButton getButtonGR() {
		return buttonGR;
	}

	public JButton getButtonC() {
		return buttonC;
	}

	public JButton getButtonF() {
		return buttonF;
	}
	
	public JButton getExitButton() {
		return exitButton;
	}
	
	

}