package it.unipv.ingsw.UniBook.Model;

public class Resource {

	private int id;
	private String nome;
	private String descrizione;
	private String tipo;

	public Resource() {

	}

	public Resource(int id, String nome, String descrizione, String tipo) {
		this.id=id;
		this.nome=nome;
		this.descrizione=descrizione;
		this.tipo=tipo;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String Tipo) {
		this.tipo = Tipo;
	}

}
