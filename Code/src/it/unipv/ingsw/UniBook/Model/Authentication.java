package it.unipv.ingsw.UniBook.Model;

public class Authentication {

	protected User u;
	
	public Authentication(User u) {
		this.u=u;
	}
	
	
	
	protected void setTypeOfUser() {
		
		String tipo = u.getTipo();
		switch (tipo) {
		case "Studente":
			System.out.println("TIPO: "+tipo);
			SingletonManager.getInstance().setLoggedUser(new User(u.getId(), u.getNome(), u.getCognome(), u.getTipo(),
					u.getEmail(), u.getCorso(), String.valueOf(u.getPassword())));
			break;
		case "Professore":
			SingletonManager.getInstance().setLoggedUser(new Professor(u.getId(), u.getNome(), u.getCognome(), u.getTipo(),
					u.getEmail(), u.getCorso(), String.valueOf(u.getPassword())));
			break;
		case "Ricercatore":
			SingletonManager.getInstance().setLoggedUser(new Researcher(u.getId(), u.getNome(), u.getCognome(), u.getTipo(),
					u.getEmail(), u.getCorso(), String.valueOf(u.getPassword())));
			break;
		}
	}
	
}
