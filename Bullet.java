import java.util.Random;

public class Bullet { // projectile super class
	EZImage picture;
	int x, y;
	int speed;

	// constructor for bullet
	Bullet() {
		Random rg = new Random();
		int low = 50;
		x = rg.nextInt(620 - low) + low;
		y = -10;
		picture = EZ.addImage("rshot.png", x, y);
		picture.pushBackOneLayer(); // ensures that bullets don't overlap w/ hud graphics
		picture.pushBackOneLayer();
		picture.pushBackOneLayer();
		speed = 4;
	}

	// set position of bullets
	public void setPosition(int posx, int posy) {
		x = posx;
		y = posy;
		setBulletImagePosition(x, y);
	}

	// move bullets to specified x y
	private void setBulletImagePosition(int posx, int posy) {
		picture.translateTo(posx, posy);
	}

	boolean shoot() { // actual method for making them move
		y += speed;
		setPosition(x, y);

		if (y > 960) { // remove bullets if off screen
			finalize();
			return false;
		} else
			return true;
	}

	protected void finalize() {
		EZ.removeEZElement(picture);
	}

	public boolean isInside(int posx, int posy) { // check if element is in bullet
		return picture.isPointInElement(posx, posy);
	}
}
