import java.util.*;
import java.awt.Color;
//Created by Michael Jake Sumaylo (2019)
//note: I did everything
public class Main {

	public static void main(String[] args) {
		EZ.initialize(960, 720); // initialize 960x760 screen
		Player Reimu = new Player("reimusprite.png", 320, 530); // spawn player character
		Boss Rumia = new Boss("rumia t pose.png", 5000, 320, 120); // spawn boss character
		EZImage background = EZ.addImage("screenwrapv2.png", 960 / 2, 720 / 2); // place hud background
		background.pullToFront(); // bring hud background to front
		EZSound dieSound = EZ.addSound("pldead00.wav"); // load sound effects &
		EZSound ost = EZ.addSound("[03] Youma Yakou.wav"); // load bgm
		EZSound hitSound = EZ.addSound("damage00.wav");
		ost.loop(); // set bgm to loop
		ArrayList<Bullet> bulletList = new ArrayList<Bullet>(); // array lists to spawn bullets
		ArrayList<PlayerBullet> pBulletList = new ArrayList<PlayerBullet>(); // array list for player shots
		int canPlay = 0; // ints to set if game keeps going or not
		int die = 1;
		int win = 2;
		int isPlaying = canPlay;
		int fontsize = 20; // font setup for flavor text and hp counter
		EZText text = EZ.addText(800, 200, "eliminate the t-pose!", Color.WHITE, fontsize);
		text.setFont("tf2build.ttf");
		EZText hpCount = EZ.addText(800, 220, "Health:" + Rumia.getHealth(), Color.WHITE, fontsize);
		hpCount.setFont("tf2build.ttf");
		while (isPlaying == canPlay) { // starting the game itself
			Reimu.ControlIt(); // giving player control of Reimu
			// System.out.println(isPlaying);
			EZ.refreshScreen(); // required to make screen actually refresh
			Bullet aBullet = new Bullet(); // spawn enemy bullet
			bulletList.add(aBullet); // add to array list
			if (Reimu.getX() > 620) { // makes sure Reimu doesn't run off play area
				Reimu.setPostion(620, Reimu.getY());
			}
			if (Reimu.getX() < 50) {
				Reimu.setPostion(50, Reimu.getY());
			}
			if (Reimu.getY() < 55) {
				Reimu.setPostion(Reimu.getX(), 55);
			}
			if (Reimu.getY() > 685) {
				Reimu.setPostion(Reimu.getX(), 685);
			}

			for (int i = 0; i < bulletList.size(); i++) {
				Bullet eachBullet;
				eachBullet = bulletList.get(i);
				if (eachBullet.shoot() == false) {
					bulletList.remove(eachBullet);
				}
				// hit detection to see if Reimu got hit by bullet
				if ((eachBullet.isInside(Reimu.getX() - 2, Reimu.getY() - 2)
						|| (eachBullet.isInside(Reimu.getX() + 2, Reimu.getY() - 2)
								|| (eachBullet.isInside(Reimu.getX() - 2, Reimu.getY() + 2)
										|| (eachBullet.isInside(Reimu.getX() + 2, Reimu.getY() + 2)))))) {
					// System.out.println("die");
					isPlaying = die; // dead Reimu
					ost.stop();
					dieSound.play();
				}
			}
			if (EZInteraction.isKeyDown("z")) { // setup for player shots
				PlayerBullet pBullet = new PlayerBullet();
				pBullet.x = Reimu.getX();
				pBullet.y = Reimu.getY();
				pBulletList.add(pBullet);
			}
			for (int i = 0; i < pBulletList.size(); i++) {
				PlayerBullet eachPBullet;
				eachPBullet = pBulletList.get(i);
				// detect if Rumia is hit by player shots
				if ((eachPBullet.isInside(Rumia.getX() - 15, Rumia.getY() - 23)
						|| (eachPBullet.isInside(Rumia.getX() + 15, Rumia.getY() - 23)
								|| (eachPBullet.isInside(Rumia.getX() - 15, Rumia.getY() + 23)
										|| (eachPBullet.isInside(Rumia.getX() + 15, Rumia.getY() + 23)
												|| (eachPBullet.isInside(Rumia.getX(), Rumia.getY()))))))) {
					Rumia.takeDamage(10); // make Rumia take damage from shots
					// System.out.println(Rumia.getHealth());
					hitSound.play(); // play hitsound
					hpCount.setMsg("Health: " + Rumia.getHealth()); // refresh hp counter
					eachPBullet.hitTarget();

				}
				if (eachPBullet.shoot() == false) { // remove shots once they hit the target
					pBulletList.remove(eachPBullet);
				}
			}
			if (isPlaying == die) { // end game and change text when hit
				text.setMsg("get Rumia'ed");
			}

			if (Rumia.getHealth() == 0) { // end game and change text when boss defeated
				text.setMsg("T-pose Rumia defeated!");
				isPlaying = win;
			}

		}

	}

}
