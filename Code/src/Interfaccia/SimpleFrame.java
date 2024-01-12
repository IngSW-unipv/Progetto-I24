import java.awt.*;
import javax.swing.*;

public class SimpleFrame extends JFrame {

    public SimpleFrame() {

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width, screenSize.height);
        validate();

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
        constraints.insets = new Insets(10, 10, 10, 10);
        constraints.anchor = GridBagConstraints.WEST;

        topCenterPanel.add(label, constraints);

        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(new Color(217, 217, 217));
        leftPanel.setLayout(new GridLayout(5, 1, 15, 10)); // Changed to accommodate 5 buttons
        leftPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JButton button1 = new JButton("PRENOTA");
        JButton button2 = new JButton("AFFITTA");
        JButton button3 = new JButton("GESTIONE RISORSE");
        JButton button4 = new JButton("CHAT");
        JButton button5 = new JButton("CONDIVISIONE FILE"); 

        leftPanel.add(button1);
        leftPanel.add(button2);
        leftPanel.add(button3);
        leftPanel.add(button4);
        leftPanel.add(button5); 
        JButton topRightButton1 = new JButton("ESCI");
        JButton topRightButton2 = new JButton("LOGIN");

        constraints.anchor = GridBagConstraints.EAST;
        constraints.weightx = 0.1;
        constraints.gridx = 4;
        topCenterPanel.add(topRightButton1, constraints);
        constraints.gridx = 5;
        topCenterPanel.add(topRightButton2, constraints);

        add(topCenterPanel, BorderLayout.NORTH);
        add(leftPanel, BorderLayout.WEST);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SimpleFrame frame = new SimpleFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}

