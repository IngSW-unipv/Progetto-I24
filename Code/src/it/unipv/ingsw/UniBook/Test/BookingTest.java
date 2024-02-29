package it.unipv.ingsw.UniBook.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.*;

import it.unipv.ingsw.UniBook.Model.Login;
import it.unipv.ingsw.UniBook.Model.SingletonManager;
import it.unipv.ingsw.UniBook.Model.Booking;
import it.unipv.ingsw.UniBook.Model.User;
import it.unipv.ingsw.UniBook.Model.Resource;

import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

@RunWith(BlockJUnit4ClassRunner.class)

public class BookingTest {

	private Booking b[];
	private Resource r1;
	private Resource r2;
	private Resource r3;
	private User u1;

	@Before
	public void initTest() {
		b = new Booking[3];
		u1 = new User("S500816", "", "Studente", "", "", "", "");
		r1 = new Resource(16, "Postazione C1", "Postazione laboratorio C elettronica", "ELE", "P", 5, null);
		r2 = new Resource(6, "Postazione A22", "Postazione laboratorio A2 informatica", "INF", "P", 2, null);
		r3 = new Resource(17, "Postazione C2", "Postazione laboratorio C elettronica", "ELE", "P", 5, null);

	}

	@Test
	public void testBookingOk() {

		b[0] = new Booking(r1, u1, "06/05/2021", "08:00:00", 1);
		b[1] = new Booking(r2, u1, "07/05/2021", "12:00:00", 2);
		b[2] = new Booking(r3, u1, "07/05/2021", "15:00:00", 3);

		for (Booking booking : b) {
			assertTrue(booking.tryToBook());
		}

		clear();

	}

	@Test
	public void testBookingNotOk1() {

		b[0] = new Booking(r1, u1, "06/05/2024", "09:00:00", 3);
		b[1] = new Booking(r2, u1, "07/05/2024", "08:00:00", 1);
		b[2] = new Booking(r3, u1, "06/05/2024", "09:00:00", 3);

		for (Booking booking : b) {
			assertFalse(booking.tryToBook());
		}

	}

	public void clear() {

		b[0] = new Booking(r1, u1, "2021-05-06", "08:00:00", 1);
		b[1] = new Booking(r2, u1, "2021-05-07", "12:00:00", 2);
		b[2] = new Booking(r3, u1, "2021-05-07", "15:00:00", 3);

		for (Booking booking : b) {
			SingletonManager.getInstance().getBookingDAO().deleteSelectedBooking(booking);
		}
	}

}
