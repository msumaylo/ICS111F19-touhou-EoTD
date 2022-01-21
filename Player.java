import java.awt.event.KeyEvent;

public class Player {
	private EZImage playerPicture;
	private EZImage hitBox;
	private int x;
	private int y;
	int step;

	// constructor for player character
	public Player(String filename, int posx, int posy) {
		playerPicture = EZ.addImage(filename, posx, posy);
		playerPicture.pushToBack();
		hitBox = EZ.addImage("reimufocus.png", posx, posy); // visual indicator of hitbox
		hitBox.hide();
		x = posx;
		y = posy;
		step = 7;
	}

	// getters and setters for use in main
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setPostion(int posx, int posy) {
		x = posx;
		y = posy;
		setPlayerImagePosition(x, y);
	}

	// moving the player character and hitbox indicator
	private void setPlayerImagePosition(int posx, int posy) {
		playerPicture.translateTo(posx, posy);
		hitBox.translateTo(posx, posy);
	}

	// methods for moving character
	public void moveLeft(int step) {
		x = x - step;
		setPlayerImagePosition(x, y);
	}

	public void moveRight(int step) {
		x = x + step;
		setPlayerImagePosition(x, y);
	}

	public void moveUp(int step) {
		y = y - step;
		setPlayerImagePosition(x, y);
	}

	public void moveDown(int step) {
		y = y + step;
		setPlayerImagePosition(x, y);
	}

	public void focus() {
		step = 3; // reduce movement speed
		hitBox.show(); // show hitbox indicator
	}

	public void ControlIt() { // setting up movement and focus controls
		if (EZInteraction.isKeyDown(KeyEvent.VK_UP)) {
			moveUp(step);
		}
		if (EZInteraction.isKeyDown(KeyEvent.VK_LEFT)) {
			moveLeft(step);
		}
		if (EZInteraction.isKeyDown(KeyEvent.VK_DOWN)) {
			moveDown(step);
		}
		if (EZInteraction.isKeyDown(KeyEvent.VK_RIGHT)) {
			moveRight(step);
		}

		if (EZInteraction.isKeyDown(KeyEvent.VK_SHIFT)) {
			focus();
		}
		if (EZInteraction.wasKeyReleased(KeyEvent.VK_SHIFT)) {
			step = 7;
			hitBox.hide();
		}
	}

}
