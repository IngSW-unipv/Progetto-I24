package model;
// incapsulamento
public class Booking {
    private Professor professor;
    private Student student;

    private String date;
    private String time;
    private boolean isAccepted;
    
    public Booking(Professor professor, String date, String time, Student student) { // costruttore per creare prenotazione
    	
    	this.date = date;
    	this.time = time;
    	this.professor = professor;
    	
    	this.isAccepted = false; // 0 di base poi cambia
    	
    	this.student = student;
    	
    }
    
    public Booking(Professor professor, String date, String time, Student student, boolean ac) {
    	
    	this.date = date;
    	this.time = time;
    	this.professor = professor;
    	
    	this.isAccepted = ac;
    	
    	this.student = student;
    	
    }

    // Metodi getter e setter per accedere a attributi priivati
    public Professor getProfessor() {
        return professor;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public boolean getIsAccepted() {
    	return isAccepted;
    }
    
    public void setAccepted(boolean t) {
    	isAccepted = t;
    }
    
    public Student getStudent() {
    	return student;
    }

}
