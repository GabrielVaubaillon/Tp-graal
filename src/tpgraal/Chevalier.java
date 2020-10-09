package tpgraal;

import java.util.ArrayList;

/*
 * Classe Chevalier : compose TableRonde; peut-être possede par Monde; peut posseder des Graal (classe fille d'Objet);
 * Un chevalier est defini par son nom
 */
public class Chevalier {
	//Attributs :
	private String nom; // nom du chevalier
	private int vie; // Quantite de points de vie
	private ArrayList<Graal> sac; // inventaire du chevalier
	
	//Constructeurs :
	
	/*
	 * Contructeur : on choisit le nom
	 * la vie est initialisee aleatoirement entre 1 et 100 (inclus)
	 * le sac est vide au depart
	 * la position n'est initialisee que lorsque le chevalier part en quete dans un Monde
	 */
	public Chevalier(String nom) {
		this.nom = nom;
		vie = (int)(Math.random() * 100) + 1;
		sac = new ArrayList<Graal>();	
	}
	
	//Methodes : 
	
	/*
	 * equals : On considère deux chevaliers egaux lorsqu'ils possèdent le même nom
	 */
	@Override
	public boolean equals(Object c) {
		boolean res = false;
		if (c != null && c instanceof Chevalier) {
			Chevalier chevalier = (Chevalier) c;
			res = (this.nom.equals(chevalier.nom));
		}
		return res;
	}
	
	/*
	 * toString : On affiche le chevalier :
	 * son nom, sa vie et le contenu de son inventaire
	 */
	@Override
	public String toString() {
		
		String res = nom + " : Niveau de vie : " + vie + "; Contenu du sac : ";
		
		for(int i = 0; i< sac.size(); i++) {
			res += sac.get(i);
			if (i < sac.size() - 1)
				res += " , ";
		}
		
		if (sac.size() == 0) { res += "vide";}
		
		return res;
	}
	
	public void ramasse(Graal g) {
		sac.add(g);
	}
	

	public void addVie(int pv) {
		vie += pv;
		if(vie < 0)
			vie = 0;
	}
	
	
	public int getVie() {
		return vie;
	}
	
	public String getNom() {
		return nom;
	}
	
	public boolean hasGraal() {
		return (sac.size() == 4);
	}
	
	public boolean isAlive() {
		return vie > 0;
	}
	
	/*
	 * poidsSac : retourne le poids du sac du chevalier (la somme du poids de chaque objet Graal contenu dans le sac)
	 * @return, int, la somme des poids des objets du sac
	 */
	public int poidsSac() {
		int poids = 0;
		for (int i = 0; i < sac.size(); i++) {
			poids += sac.get(i).getPoids();
		}
		return poids;
	}
}
