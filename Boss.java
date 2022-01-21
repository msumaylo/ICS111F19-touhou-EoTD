
public class Boss {
	private EZImage bossPicture;
	private int health;
	private int x;
	private int y;

	// boss constructor
	public Boss(String filename, int startingHealth, int posX, int posY) {
		bossPicture = EZ.addImage(filename, posX, posY);
		x = posX;
		y = posY;
		health = startingHealth;
	}

	// getter for x y and health
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getHealth() {
		return health;
	}

	// check if something is inside of boss
	public boolean isInside(int posX, int posY) {
		return bossPicture.isPointInElement(posX, posY);
	}

	// makes boss take damage
	public void takeDamage(int dmg) {
		health = health - dmg;
	}

	// method to kill boss
	public void die() {
		bossPicture.hide();
	}

}
