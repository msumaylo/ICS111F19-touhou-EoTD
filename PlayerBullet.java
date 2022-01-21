
public class PlayerBullet extends Bullet { // class inherits from the bullet class

	// player bullet constructor
	PlayerBullet() {
		speed = 20;
		picture = EZ.addImage("reimushot.png", x, y);
		picture.pushBackOneLayer();
		picture.pushBackOneLayer();
		picture.pushBackOneLayer();
	}

	boolean shoot() {
		y -= speed;
		setPosition(x, y);
		if (y < 10) {
			finalize();
			return false;
		} else
			return true;
	}

	// remove bullets once they hit target
	boolean hitTarget() {
		finalize();
		return false;
	}

}
