import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class ObjectManager implements ActionListener {
	
	Rocketship rocketshipTwo;
	
	ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
	ArrayList<Alien> aliens = new ArrayList<Alien>();
	
	Random random = new Random();
	
	int score;
	
	ObjectManager(Rocketship rocketshipTwo){
		this.rocketshipTwo = rocketshipTwo;
		score = 0;
	}
	
	void checkCollision() {
		for (int i = 0; i < aliens.size(); i++) {
			for (int j = 0; j < projectiles.size(); j++) {
				if (aliens.get(i).collisionBox.intersects(projectiles.get(j).collisionBox)) {
					aliens.get(i).isActive = false;
					projectiles.get(j).isActive = false;
					score = score + 1;
				}
			}
			if (aliens.get(i).collisionBox.intersects(rocketshipTwo.collisionBox)) {
				aliens.get(i).isActive = false;
				rocketshipTwo.isActive = false;
				System.out.println("OOF! You've been Hit!");
			}
		}
	}
	
	void addProjectile(Projectile p) {
		projectiles.add(p);
	}
	
	void draw(Graphics g) {
		rocketshipTwo.draw(g);
		for (int i = 0; i < projectiles.size(); i++) {
			projectiles.get(i).draw(g);
		}
		for (int i = 0; i < aliens.size(); i++) {
			aliens.get(i).draw(g);
		}
	}
	
	void purgeObjects() {
		for (int i = 0; i < projectiles.size(); i++) {
			if (projectiles.get(i).isActive == false) {
				projectiles.remove(i);
			}
		}
		for (int i = 0; i < aliens.size(); i++) {
			if (aliens.get(i).isActive == false) {
				aliens.remove(i);
			}
		}
	}
	
	void addAlien() {
		aliens.add(new Alien(random.nextInt(LeagueInvaders.WIDTH),0,50,50));
	}
	
	void update(){
		for (int i = 0; i < aliens.size(); i++) {
			aliens.get(i).update();
			if (aliens.get(i).y > LeagueInvaders.HEIGHT) {
				aliens.get(i).isActive = false;
			}
		}
		for (int i = 0; i < projectiles.size(); i++) {
			projectiles.get(i).update();
			if (projectiles.get(i).y < 0) {
				projectiles.get(i).isActive = false;
			}
		}
		checkCollision();
		purgeObjects();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		addAlien();
	}
}
