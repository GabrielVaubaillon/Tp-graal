package tpgraal;

import in.keyboard.Keyboard;

public class Main {
	
	/*
	 * retourLignes : permet de realiser un certain nombre n de retour a la ligne d'un seul coup
	 * @return : String
	 */
	public static String retourLignes(int n) {
		String res = "";
		for (int i=0; i<n; i++) {
			res += '\n';
		}
		return res;
	}
	
	/*
	 * Attendre : Permet de mettre le programme en pause pendant un certain temps
	 */
	public static void attendre(int millisecondes) {
		try {
		    Thread.sleep(millisecondes);
		}
		catch(InterruptedException ex) {
		    Thread.currentThread().interrupt();
		}
	}
	
	/*
	 * quete : Lance la quete, fait se deplacer le chevalier a chaque tour, et le fait interagir avec le monde
	 * @monde, Monde, le monde dans lequel se deroule la quête
	 * @return, true si le chevalier a trouve le Graal, false s'il est mort
	 */
	public static boolean quete(Monde monde,Chevalier chevalier) {
		System.out.println(chevalier);//retour a la ligne esthetique
		System.out.println(monde);//monde.afficher();
		System.out.println(retourLignes(10));
		int nombreTours = 0;
		while (chevalier.isAlive() && !chevalier.hasGraal()) {
			attendre(1500);//On attend un peu entre chaque tours, que le joueur puisse suivre le chevalier
			
			System.out.println(retourLignes(60));//On passe a l'ecran suivant
			nombreTours ++;
			System.out.println("Tour numero : " + nombreTours);
			
			String message = monde.nouveauTour(chevalier);//C'est ici que le tour se passe reelement, le reste c'est de l'esthetique
			
			System.out.println(message);
			System.out.println(chevalier);
			System.out.println(monde);//monde.afficher();
			System.out.println(retourLignes(10));
		}
		System.out.println(retourLignes(60));
		System.out.println(monde);//monde.afficher();
		
		//On regarde si le chevalier a reussi ou non la quete : 
		boolean reussie;
		if (!chevalier.isAlive()) {
			System.out.println(chevalier.getNom() + " est mort !!");
			reussie = false;
		}
		else {
			System.out.println(chevalier.getNom() + " a trouve le Graal !!!!!");
			attendre(500);
			System.out.println("Les chevaliers fetent le retour heroique de leur compagnon");
			attendre(500);
			System.out.println("La quete est maintenant finie");
			attendre(500);
			reussie = true;
		}
		System.out.println("Il a mis " + nombreTours + " deplacement avant la fin");
		return reussie;
	}
	
	/*
	 * effectuerAction : Lance la partie du programme correspondant au choix fait precedemment dans le menu;
	 * @choix, int, le numero correspondant a l'action voulue;
	 * @table, TableRonde, la table contenant les chevaliers; 
	 */
	public static boolean effectuerAction(int choix,TableRonde table) {
		boolean finJeu = false;
		if (choix == 0) {
			//action  : Quitter le jeu
			finJeu = true;
		}
		else if (choix == 1) {
			//action : ajouter chevalier
			Chevalier chevalier = keyboardChevalier("Entrez le nom du nouveau chevalier : ");
			
			if (!table.contient(chevalier)) {
				
				table.ajouter(chevalier);
				
				System.out.println(chevalier);
				System.out.println(chevalier.getNom() +" a rejoint la table ronde");
			}
			else {
				System.out.println("Le chevalier est deja present a la table ronde");
			}
		}
		else if (choix == 2) {
			//action : expulser chevalier
			if (!table.isEmpty()) {
				
				Chevalier chevalier = table.choixChevalier("Choisissez le chevalier que vous voulez expulser : ");
				System.out.println(chevalier.getNom() + " quitte la table ronde.");	
				table.supprimer(chevalier);				
			}
			else {
				
				System.out.println("La table ronde est vide");
			}
		}
		else if (choix == 3) {
			//action : afficher table ronde
			System.out.println(table);
		}
		else if (choix == 4) {
			//action : envoyer un chevalier en quete
			if (!table.isEmpty()) {
				
				Chevalier chevalier = table.choixChevalier("Choisissez le chevalier a envoyer en quête : ");
				
				Monde monde = new Monde();
				
				boolean reussie = quete(monde,chevalier);
				
				System.out.println(chevalier);
				
				if (!reussie) {
					table.supprimer(chevalier);
				}
				else {
					//Une fois que le Graal est trouve, on quitte le jeu
					finJeu = true;
				}
			}
			else {
				//Si la table ronde est vide, on ne peut pas envoyer de chevalier en quete
				System.out.println("La table ronde est vide");
			}
		}
		
		return finJeu;
	}
	
	/*
	 * keyboardChevalier : Permet de creer un chevalier en choisissant son nom;
	 * @message, String, Le message affiche avant que l'utilisateur choisisse le nom du chevalier;
	 * @return, Chevalier, Un nouveau chevalier avec le nom voulu;
	 */
	public static Chevalier keyboardChevalier(String message) {
		System.out.print(message);
		String nomChevalier = Keyboard.getString();
		return new Chevalier(nomChevalier);
	}
	
	/*
	 * menu : affiche le menu et prend le choix du joueur, puis lance effectuerAction();
	 * @table, TableRonde, la table ronde contenant les chevaliers;
	 * @return, boolean, Si on doit quitter le programme, sinon on re-affiche le menu au tour suivant;
	 */
	public static int menu() {
		
		System.out.println('\n'+"-- Menu --");
		System.out.println(" - 0 : Quitter le jeu");
		System.out.println(" - 1 : Ajouter un chevalier");
		System.out.println(" - 2 : Expulser le chevalier");
		System.out.println(" - 3 : Afficher les chevaliers de la table ronde");
		System.out.println(" - 4 : Envoyer un chevalier en quête");
		
		boolean entreeValide = false;
		int choix = 0;
		while (!entreeValide) {
			System.out.print("Votre Choix : ");
			choix = Keyboard.getInt();
			entreeValide = (choix >= 0 && choix <= 4);
		}
		
		System.out.println("");//retour a la ligne esthetique
		return choix;
	}
	
	/*
	 * main
	 */
	public static void main(String[] args) {
		TableRonde table = new TableRonde();
		
		boolean finJeu = false;
		while (!finJeu) {
			
			int choix = menu();
			
			finJeu = effectuerAction(choix,table);
		}
		System.out.println("Goodbye !!");
	}

}
