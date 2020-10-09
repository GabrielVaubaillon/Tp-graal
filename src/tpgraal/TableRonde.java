package tpgraal;

import in.keyboard.Keyboard;

import java.util.ArrayList;

/*
 * Classe TableRonde : Classe constituee de Chevalier, elle permet de regroupper
 * les chevaliers, de les gerer
 */
public class TableRonde {
	//Attributs : 
	//La liste des chevaliers :
	private ArrayList<Chevalier> chevaliers;
	
	//Constructeurs :
	public TableRonde() {
		chevaliers = new ArrayList<Chevalier>();
	}
	
	//Methodes : 
	
	/*
	 * ajouter : Permet d'ajouter un chevalier a la table ronde;
	 * @chevalier, Chevalier, le chevalier que l'on souhaite ajouter
	 */
	public void ajouter(Chevalier chevalier) {
		if( !chevaliers.contains(chevalier) ) {
			chevaliers.add(chevalier);
		}
	}
	
	/*
	 * supprimer : Permet de supprimer un chevalier de la table ronde;
	 */
	public void supprimer(Chevalier chevalier) {
			chevaliers.remove(chevalier);
	}
	
	/*
	 * toString : affiche les chevaliers de la table
	 */
	@Override
	public String toString() {
		String res = "";
		
		for (int i = 0; i < chevaliers.size(); i++) {
			res += chevaliers.get(i).toString();
			res += '\n';
		}
		return res;
	}
	
	/*
	 * isEmpty : Permet de savoir si la table ronde est vide;
	 * @return, boolean, si la table ronde contient des chevaliers
	 */
	public boolean isEmpty() {
		return chevaliers.size() == 0;
	}
	
	public boolean contient(Chevalier c) {
		return chevaliers.contains(c);
	}
	
	/*
	 * choixChevalier : Affiche une liste des chevaliers et retourne celui que l'on choisit;
	 * @message, String, le message affiche avant le choix;
	 * @return, Chevalier, le chevalier choisit;
	 */
	public Chevalier choixChevalier(String message) {
		System.out.println(message);
		
		for(int i = 0; i < chevaliers.size(); i ++) {
			System.out.print(" - " + i + " : " + chevaliers.get(i).getNom() + '\n');
		}
		
		boolean entreeValide = false;
		int indice = 0;
		while (!entreeValide) {
			System.out.print("Choix : ");
			indice =  Keyboard.getInt();
			entreeValide = (indice >= 0 && indice < chevaliers.size());
		}
		
		return chevaliers.get(indice);
	}
}
