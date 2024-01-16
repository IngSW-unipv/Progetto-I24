package it.unipv.ingsw.UniBook.Model;

public class Prenotazione {

	private String data;
	private String ora;
	private String nome;
	private int durata;

	public Prenotazione() {

	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getOra() {
		return ora;
	}

	public void setOra(String ora) {
		this.ora = ora;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getDurata() {
		return durata;
	}

	public void setDurata(int durata) {
		this.durata = durata;
	}

	public String getFinePrenotazione() {
		String oraToInt = ora.substring(0, 2);
		int oraInt = Integer.parseInt(oraToInt) + durata;
		return oraInt + ":00";
	}

	public boolean isDurataValida() {

		boolean b = true;

		int orarioChiusura = 19;

		int oraInizioPrenotazione = Integer.parseInt(ora.substring(0, 2));

		if (durata > (orarioChiusura - oraInizioPrenotazione))
			return false;

		return true;

	}

}
