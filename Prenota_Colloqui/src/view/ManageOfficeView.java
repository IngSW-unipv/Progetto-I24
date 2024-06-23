package view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.BorderLayout;

public class ManageOfficeView extends JPanel {
    private JButton confirmButton;
    private JTextField nomestudente;
    private JButton indietro;
    private JLabel bookings;

    public ManageOfficeView() {

    	this.setLayout(new BorderLayout());
    	
    	nomestudente = new JTextField(20);
        confirmButton = new JButton("Conferma Prenotazione");

        JPanel panel = new JPanel();
        panel.add(nomestudente);
        panel.add(confirmButton);
        
        JPanel panelcentrale = new JPanel();
        bookings = new JLabel("<html>- Prenotazione 1 da studente ... <br> - Prenotazione 2 ...</html>");
        panelcentrale.add(bookings);
        
        JPanel panelfinale = new JPanel();
        indietro = new JButton("Indietro");
        panelfinale.add(indietro);

        add(panel, BorderLayout.NORTH);
        add(panelcentrale, BorderLayout.CENTER);
        add(panelfinale, BorderLayout.SOUTH);
    }

    public JButton getConfirmButton() {
        return confirmButton;
    }
    
    public JTextField getNomeStudent() {
    	return nomestudente;
    }
    
    public JLabel getBookingsLabel() {
    	return bookings;
    }
    
    public JButton getIndietro() {
    	return indietro;
    }

}
