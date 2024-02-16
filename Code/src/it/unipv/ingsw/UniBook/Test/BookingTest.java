package it.unipv.ingsw.UniBook.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.*;

import it.unipv.ingsw.UniBook.Model.Login;
import it.unipv.ingsw.UniBook.Model.SingletonManager;
import it.unipv.ingsw.UniBook.Model.Booking;
import it.unipv.ingsw.UniBook.Model.User;
import it.unipv.ingsw.UniBook.Model.Resource;

public class BookingTest {

	
	private Booking b[];
	private Resource r1;
	private Resource r2;
	private Resource r3;
	private User u1;
	private User u2;
	
	@Before
	public void initTest() {
		b = new Booking[3];
		u1 = new User("S500816","","Studente","", "", "", "");
		u2 = new User("P501934","","Professore","", "", "", "");
		r1 = new Resource(8, "Postazione A24", "Postazione laboratorio A2 informatica", "INF", "P", 2, null);
		r2 = new Resource(18, "Postazione C3", "Postazione laboratorio C elettronica", "ELE", "P", 4, null);
		r3 = new Resource(5, "Postazione A21", "Postazione laboratorio A2 informatica", "INF", "P", 2, null);
	
	}

	@Test
	public void testBookingOk() {

		b[0] = new Booking(r1, u1, "2024-05-07", "17:00:00", 1);
		b[1] = new Booking(r2, u1, "2024-05-07", "12:00:00", 1);
		b[0] = new Booking(r3, u2, "2024-05-07", "08:00:00", 2);
		

		for (Booking booking : b) {
			assertTrue(SingletonManager.getInstance().getBookingDAO().insertBooking(booking));
		}

	}
	
	/*
	
	//Stessa ora
	@Test
	public void testBookingNotOk1() {

		b[0] = new Booking(r1, u1, "2024-05-07", "17:00:00", 1);
		b[1] = new Booking(r1, u1, "2024-05-07", "17:00:00", 1);
		b[0] = new Booking(r3, u2, "2024-05-07", "08:00:00", 2);
		

		for (Booking booking : b) {
			assertFalse(tryToBook());
			//assertFalse(SingletonManager.getInstance().getBookingDAO().insertBooking(booking));
		}

	}
	
	//Studente che prenota labo
	@Test
	public void testBookingNotOk1() {

		b[0] = new Booking(r1, u1, "2024-05-07", "17:00:00", 1);
		b[1] = new Booking(r1, u1, "2024-05-07", "17:00:00", 1);
		b[0] = new Booking(r3, u2, "2024-05-07", "08:00:00", 2);
		

		for (Booking booking : b) {
			assertFalse(SingletonManager.getInstance().getBookingDAO().insertBooking(booking));
		}

	}
	
	*/

	
}
