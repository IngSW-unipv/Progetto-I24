package it.unipv.ingsw.UniBook.Model;

public class User {

	private String matricola;
	private String nome;
	private String cognome;
	private String tipo;
	private String email;
	private String corso;
	private String password;

	//Costruttore per istanziare un user vuoto che verrà riempito poi in fase di autenticazione
	public User() {
		
	}
	
	public User(String matricola, String nome, String cognome, String tipo, String email, String corso, String password) {
		this.matricola = matricola;
		this.nome = nome;
		this.cognome = cognome;
		this.tipo = tipo;
		this.email = email;
		this.corso = corso;
		this.password = password;
	}
	
	public User(String matricola,String nome, String cognome) {
		
		this.matricola = matricola;
		this.nome = nome;
		this.cognome = cognome;
		
	}
	
	public User(String matricola, String password) {
		
		this.matricola = matricola;
		this.password = password;
		
	}

	public String getId() {
		return matricola;
	}

	public void setId(String matricola) {
		this.matricola = matricola;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCorso() {
		return corso;
	}

	public void setCorso(String corso) {
		this.corso = corso;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
    public String toString() {
        return matricola + " - " + nome + " " + cognome;
    }
}
