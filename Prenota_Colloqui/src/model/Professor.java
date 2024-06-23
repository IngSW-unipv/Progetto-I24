package model;

public class Professor extends User{

	public Professor(String nome, String password) {
		
		super(nome,password);
		
	}
	
	@Override
	public boolean isStudent() {
		return false;  
	}
	
	@Override
	public boolean isProfessor() { 
		return true; //identifica che questo utente è professore
	}
	
}
