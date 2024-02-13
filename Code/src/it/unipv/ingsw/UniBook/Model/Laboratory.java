package it.unipv.ingsw.UniBook.Model;

public class Laboratory extends Resource{

	private int id;
	private int N_posti;
	private String Nome;
	
	public Laboratory(int id, int n_posti) {
		this.id = id;
		N_posti = n_posti;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getN_posti() {
		return N_posti;
	}
	public void setN_posti(int n_posti) {
		N_posti = n_posti;
	}
	public String getNomeLab() {
		return Nome;
	}
	public void setNomeLab(String Nome) {
		this.Nome = Nome;
	}
	
	
	
}
