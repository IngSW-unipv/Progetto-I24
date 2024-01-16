package it.unipv.ingsw.UniBook.Model;

public class Risorsa {

	private String Nome;
	private String Descrizione;
	private String Tipo;

	public Risorsa() {

	}

	public Risorsa(String Nome, String Descrizione, String Tipo) {
		this.Nome=Nome;
		this.Descrizione=Descrizione;
		this.Tipo=Tipo;
	}
	
	public String getNome() {
		return Nome;
	}

	public void setNome(String nome) {
		this.Nome = nome;
	}

	public String getDescrizione() {
		return Descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.Descrizione = descrizione;
	}

	public String getTipo() {
		return Tipo;
	}

	public void setTipo(String Tipo) {
		this.Tipo = Tipo;
	}

}
