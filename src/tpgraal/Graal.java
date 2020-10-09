package tpgraal;

/*
 * Classe Graal : classe fille d'Objet, peut êter possédé par Chevalier
 */
public class Graal extends Objet{
	//Attributs : 
	private int poids;
	
	//Constructeur :
	public Graal(String nom, char apparence, int poids, int vie) {
		super(nom,apparence,vie);
		this.poids = poids;
	}
	
	//Méthodes :
	public int getPoids() {
		return poids;
	}
	@Override
	public String toString() {
		return super.getNom();
	}
}
