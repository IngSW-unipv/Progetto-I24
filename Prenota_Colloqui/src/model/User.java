package model;

public class User {
	
	private String nome, password;

	public User(String nome, String password) {
		
		this.nome = nome;
		this.password = password;
		
	}
	
	public String getNome() {
		return nome;
	}
	
	public String getPassword() {
		return password;
	}
	
	public boolean isStudent() {
		return false;
	}
	
	public boolean isProfessor() {
		return false;
	}
	
}
