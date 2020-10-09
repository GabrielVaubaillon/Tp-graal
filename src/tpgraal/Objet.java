package tpgraal;

/*
 * Classe Objet : peuvent appartenir à Monde; classe mere de Graal et Obstacle;
 */
public class Objet{
	
	//Attributs :
	private String nom;
	private int vie; // la vie que rapporte l'objet lorsque l'on tombe sur lui (peut etre négatif)
	private char apparence;
	
	//Constructeurs :
	public Objet(String nom,char apparence, int vie) {
		this.nom = nom;
		this.apparence = apparence;
		this.vie = vie;
	}
	
	//Methodes :

	public String getNom() {
		return nom;
	}
	
	public int getVie() {
		return vie;
	}
	
	public char getApparence() {
		return apparence;
	}
}
