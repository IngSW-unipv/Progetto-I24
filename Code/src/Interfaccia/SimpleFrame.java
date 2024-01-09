package Interfaccia;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;

public class SimpleFrame extends JFrame {

	public SimpleFrame() {

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(screenSize.width, screenSize.height);
		validate();

	     // Create two panels
	        JPanel topCenterPanel = new JPanel();
	        JLabel label = new JLabel("UniBook");
	        topCenterPanel.setBackground(Color.CYAN);
	        topCenterPanel.setPreferredSize(new Dimension(400, 100));
	        
	        Font newFont = new Font(label.getFont().getName(), Font.PLAIN, 50);
	        label.setFont(newFont);
	        
	        topCenterPanel.setLayout(new GridBagLayout());

	        GridBagConstraints constraints = new GridBagConstraints();
	        constraints.fill = GridBagConstraints.HORIZONTAL;
	        constraints.gridx = 0;
	        constraints.gridy = 0;
	        constraints.weightx = 0.5;
	        constraints.insets = new Insets(10, 10, 10, 10); // Padding
	        constraints.anchor = GridBagConstraints.WEST;
	       
	        topCenterPanel.add(label, constraints);

	        JPanel leftPanel = new JPanel();
	        leftPanel.setBackground(new Color(217, 217, 217));
	        //leftPanel.setPreferredSize();
	        leftPanel.setSize(new Dimension(300, 120));

	     // Set the layout manager for the JFrame
	        leftPanel.setLayout(new BorderLayout());

	     // Add the panels to the JFrame in the specified positions
	        add(topCenterPanel, BorderLayout.NORTH);
	        add(leftPanel, BorderLayout.WEST);

	        
	     // Create buttons for left panel
	        JButton button1 = new JButton("PRENOTA");
	        button1.setPreferredSize(new Dimension(250, 100));
	        JButton button2 = new JButton("AFFITTA");
	        JButton button3 = new JButton("GESTIONE RISORSE");
	        JButton button4 = new JButton("CHAT");
	        
	        
	     // Add buttons to the left panel
	        leftPanel.setLayout(new GridLayout(4, 1, 15, 50));
	        leftPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
	        leftPanel.add(button1);
	        leftPanel.add(button2);
	        leftPanel.add(button3);
	        leftPanel.add(button4);
	        
	     // Create buttons for top right panel
	        JButton topRightButton1 = new JButton("ESCI");
	        JButton topRightButton2 = new JButton("LOGIN");

	     // Add buttons to the top right panel
	        constraints.anchor = GridBagConstraints.EAST;
	        constraints.weightx = 0.1;
	        constraints.gridx = 4;
	        topCenterPanel.add(topRightButton1,constraints);
	        constraints.gridx = 5;
	        topCenterPanel.add(topRightButton2,constraints);
	        
	    }

	}

