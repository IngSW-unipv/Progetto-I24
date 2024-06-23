package model;

public class Student extends User{

	public Student(String nome, String password) {
		
		super(nome,password);
		
		
	}
	
	@Override
	public boolean isStudent() {
		return true; //identifica che questo utente è studente
	}
	
	@Override
	public boolean isProfessor() {
		return false;
	}
	
}
