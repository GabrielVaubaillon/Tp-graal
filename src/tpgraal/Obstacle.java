package tpgraal;

/*
 * classe Obstacle : classe fille d'Objet
 * Les obstacles sont un type d'objet que le chevalier ne peut pas posseder
 * Ils font par convention perdre de la vie lorque l'on interagit avec eux
 */
public class Obstacle extends Objet {
	//Constructeur : 
	public Obstacle(String nom, char apparence, int vie) {
		super(nom,apparence, vie);
	}
}
