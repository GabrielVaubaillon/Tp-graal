package tpgraal;

/*
 * Type enumere Direction : contient 8 directions
 */
public enum Direction {
	HAUT,HAUTDROITE,DROITE,BASDROITE,BAS,BASGAUCHE,GAUCHE,HAUTGAUCHE;
	
	/*
	 * randomDirection : Renvoie une direction au hasard parmi les 8 existentes
	 */
	public static Direction randomDirection() {
		int indiceRandom = (int)(Math.random()*8);
		return values()[indiceRandom];
	}
}
