package view;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainFrame extends JFrame{

	public static final int WIDTH = 750;
	public static final int HEIGHT = 500;
	
	private CardLayout c1;
	private JPanel cardPanel;
	
	private LoginView loginview;
	private HomeView homeview;
	private BookingView bookingview;
	private ManageOfficeView manageofficeview;
	
	public MainFrame() {
		
		this.setSize(WIDTH,HEIGHT);
		this.setLocationRelativeTo(null);
		this.setTitle("Prenotazioni");
		
		initComponents();
		
	}
	
	private void initComponents() {
		
		c1 = new CardLayout();
		cardPanel = new JPanel();
		cardPanel.setLayout(c1);
		
		loginview = new LoginView();
		homeview = new HomeView();
		bookingview = new BookingView();
		manageofficeview = new ManageOfficeView();
		
		
		cardPanel.add(loginview, "1");
		cardPanel.add(homeview, "2");
		cardPanel.add(bookingview, "3");
		cardPanel.add(manageofficeview, "4");
		
		
		c1.show(cardPanel, "1");
		this.add(cardPanel);
		
	}
	
	public void changeView(String n) {
		
		c1.show(cardPanel, n);
		
	}
	
	public LoginView getLoginview() {
		return loginview;
	}
	
	public HomeView getHomeView() {
		return homeview;
	}
	
	public BookingView getBookingView() {
		return bookingview;
	}
	
	public ManageOfficeView getManageOfficeView() {
		return manageofficeview;
	}
	
}
