package it.unipv.ingsw.UniBook.Exception;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.Date;

import javax.swing.JOptionPane;

import com.toedter.calendar.JDateChooser;

public class PopupManager {

	public static void showPopup(String message) {
		JOptionPane.showMessageDialog(null, message, "Sistema", JOptionPane.INFORMATION_MESSAGE);
	}

	public static int showChoosing(Object[] options) {
		return JOptionPane.showOptionDialog(null, "Scegli un'opzione", "Sistema", JOptionPane.DEFAULT_OPTION,
				JOptionPane.INFORMATION_MESSAGE, null, options, null);
	}

	public static void dateChoosing(JDateChooser jd, LocalDate data) {
		jd.setMinSelectableDate(Date.from(data.atStartOfDay(ZoneId.systemDefault()).toInstant().plus(1, ChronoUnit.DAYS)));
		JOptionPane.showConfirmDialog(null, jd, "Start date", JOptionPane.PLAIN_MESSAGE);
	}

}
