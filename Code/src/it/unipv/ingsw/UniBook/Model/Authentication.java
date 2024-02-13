package it.unipv.ingsw.UniBook.Model;

public class Authentication {

	protected User u;
	
	public Authentication(User u) {
		//this.u =new User(u.getId(),u.getNome(),u.getCognome(),u.getTipo(),u.getCorso(),u.getEmail(),String.valueOf(u.getPassword()));
		this.u=u;
	}
	
	
	
	protected void setTypeOfUser() {
		//char tipoMatricola = this.u.getId().charAt(0);
		
		String tipo = u.getTipo();
		System.out.println("TIPO: "+tipo);
		switch (tipo) {
		case "Studente":
			SingletonManager.getInstance().setLoggedUser(new User(u.getId(), u.getNome(), u.getCognome(), u.getTipo(),
					u.getEmail(), u.getCorso(), String.valueOf(u.getPassword())));
			System.out.println("NEL SINGLETON "+u.getId());
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
