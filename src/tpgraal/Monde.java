package tpgraal;

import java.util.ArrayList;

/*
 * Classe Monde : peut posseder des Objet; peut posseder un Chevalier;
 * Il s'agit de l'environnement dans lequel les chevaliers evoluent lors de quêtes
 * L'espace est une matrice carree d'Objet, de côte taille
 */
public class Monde {
	//Attributs :
	private Objet[][] espace;
	private int taille;
	private Position posChevalier;
	
	//Constructeurs : 
	
	/*
	 * Constructeur Monde : 
	 * Choix des caracteristiques du monde : taille et probabilite des obstacles
	 * creation de l'espace
	 * creationd des quatres objets du graal
	 * Positionnement aleatoire des objets du graal
	 * Positionnement aleatoire du Chevalier sur une case vide
	 * creation des obstacles
	 * Positionnement des obstacles
	 */
	public Monde() {
		//Caracteristiques des Mondes :
		taille = 10;
		double proportionObstacle = 0.10;
		
		//On cree le plateau
		espace = new Objet[taille][taille];
		
		//Creation des objets du Graal :
		Graal[] graal = new Graal[4];
		graal[0] = new Graal("Excalibur", 'E', 5, 100);
		graal[1]  = new Graal("Pierre de Fal Lial",'P', 4 , 80);
		graal[2]  = new Graal("Lance de Lug",'L', 2, 40);
		graal[3]  = new Graal("Chaudron de la Connaissance",'C', 3, 50);
		
		//Positionnement des objets du Graal : 
		int randomx = 0,randomy = 0;
		boolean valide;
		for(int i = 0; i < 4; i++) {
			valide = false;
			while (!valide) {
				randomx = (int)(Math.random()* (taille));
				randomy = (int)(Math.random()* (taille));
				valide = espace[randomx][randomy] == null;
			}
			espace[randomx][randomy] = graal[i];
		}
		
		// On prépare la position d'arrivée du chevalier :
		randomx = 0;
		randomy = 0;
		valide = false;
		while (!valide) {
			randomx = (int)(Math.random()* (taille));
			randomy = (int)(Math.random()* (taille));
			valide = espace[randomx][randomy] == null;
		}
		posChevalier = new Position(randomx, randomy);
		
		//Creation des Obstacles : 
		ArrayList<Obstacle> obstaclesType = new ArrayList<Obstacle>(); //Pour pouvoir en ajouter plus facilement
		obstaclesType.add(new Obstacle("roche",'r',-20));
		obstaclesType.add(new Obstacle("puits",'t',-30));
		obstaclesType.add(new Obstacle("ronce",'n',-10));
		
		//Positionnement des obstacles :
		//On place les obstacles differement des autres elements. Ici, on parcourt chaque case du plateau
		// et chaque case libre a une certaine probabilite de contenir un obstacle
		for(int i = 0; i < taille; i++) {
			for(int j = 0; j < taille; j++) {
				
				if (espace[i][j] == null && getCase(posChevalier)==null && Math.random() < proportionObstacle) {
					
					int indiceTypeObstacle = (int)(Math.random()*(obstaclesType.size()));//On choisit un obstacle au hasard
					
					espace[i][j] = obstaclesType.get(indiceTypeObstacle);
				}	
			}
		}
	}
	
	//Methodes :
	@Override
	public String toString() {
		String res = "";
		for(int i = 0; i < taille; i++) {
			for(int j = 0; j < taille; j++) {
				
				res += "  ";
				if (posChevalier.is(i,j)) {
					res += "@";
				}
				else if (espace[i][j] != null){
					res += espace[i][j].getApparence();
				}
				else {
					res += "-";
				}
			}
			res += '\n';
		}
		return res;
	}
	
	public Objet getCase(Position p) {
		return espace[p.getI()][p.getJ()];
	}
	
	public void videCase(Position p ) {
		espace[p.getI()][p.getJ()] = null;
	}
	/*
	 * getTaille : renvoie la taille de l'espace du Monde
	 */
	public int getTaille() {
		return taille;
	}
	
	/*
	 * refresh : avance la quete d'un tour
	 */
	public String nouveauTour(Chevalier chevalier) {
		Direction direction = Direction.randomDirection();
		posChevalier.setNew(direction, taille);
		chevalier.addVie(-chevalier.poidsSac());
		String message = interaction(chevalier);
		return message;
	}
	
	public String interaction(Chevalier chevalier) {
		String res = "";
		Objet o = getCase(posChevalier);
		if (o!=null) {
			res = chevalier.getNom() + " a rencontré " + o.getNom() + ", il ";
			if(o.getVie() > 0) {res += "gagne";} else { res += "perd";}
			res += " " + Math.abs(o.getVie()) + " points de vie.";
			
			chevalier.addVie( o.getVie() );
			if (o instanceof Graal) {
				chevalier.ramasse((Graal)o);
				videCase(posChevalier);
			}
		}
		return res;
	}
}
