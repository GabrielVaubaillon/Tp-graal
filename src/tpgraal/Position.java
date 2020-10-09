package tpgraal;

public class Position {
	private int i;
	private int j;
	
	public Position(int i, int j) {
		this.i = i;
		this.j = j;
	}
	
	public boolean is(int x, int y) {
		return i == x && j == y;
	}
	
	public int getI() {
		return i;
	}
	
	public int getJ() {
		return j;
	}
	
	public void setNew(Direction direction, int taille) {
		if (direction == Direction.BAS) {
			i++;
		}
		else if (direction == Direction.HAUT) {
			i--;
		}
		else if (direction == Direction.DROITE) {
			j++;
		}
		else if (direction == Direction.GAUCHE) {
			j--;
		}
		else if (direction == Direction.HAUTDROITE) {
			i--;
			j++;
		}
		else if (direction == Direction.BASDROITE) {
			i++;
			j++;
		}
		else if (direction == Direction.BASGAUCHE) {
			i++;
			j--;
		}
		else if (direction == Direction.HAUTGAUCHE) {
			i--;
			j--;
		}
		//Quand le chevalier sort d'un cote du monde, il reapparait de l'autre :
		i = (i + taille) % taille;
		j = (j + taille) % taille;
	}
}
