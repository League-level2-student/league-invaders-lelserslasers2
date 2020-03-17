import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener, KeyListener {
	
	final int MENU = 0;
	final int GAME = 1;
	final int END = 2;
	int currentState = MENU;
	
	Font titleFont;
	Font writtingFont;
	
	Timer frameDraw;
	Timer alienSpawn;
	
	Rocketship rockship = new Rocketship(250,700, 50, 50);
	
	ObjectManager objectManager = new ObjectManager(rockship);
	
	GamePanel (){
	    titleFont = new Font("Arial", Font.PLAIN, 48);
	    writtingFont = new Font("Arial", Font.PLAIN, 25);
	    
	    frameDraw = new Timer(1000/60, this);
	    frameDraw.start();
	    
	}
	
	void startGame() {
		alienSpawn = new Timer(1000 , objectManager);
	    alienSpawn.start();
	}
	
	void updateMenuState() {  }
	
	void updateGameState() { 
		objectManager.update();
	}
	
	void updateEndState()  {  }
	
	void drawMenuState(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
		
		g.setFont(titleFont);
		g.setColor(Color.YELLOW);
		g.drawString("LEAGUE INVADERS", 20, 75);
		
		g.setFont(writtingFont);
		g.drawString("Press ENTER to start" , 125, 325);
		g.drawString("Press SPACE for info", 125, 425);
	}
	void drawGameState(Graphics g) {
		//g.setColor(Color.BLACK);
		//g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
		BufferedImage image = null;
		try {
			image = ImageIO.read(this.getClass().getResourceAsStream("space.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		g.drawImage(image, 0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT, null);
		
		objectManager.draw(g);
	}
	void drawEndState(Graphics g)  {
		g.setColor(Color.RED);
		g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
		
		g.setFont(titleFont);
		g.setColor(Color.BLACK);
		g.drawString("GAME OVER", 100, 75);
		
		g.setFont(writtingFont);
		g.drawString("You killed ? enemies" , 125, 325);
		g.drawString("Press ENTER to start", 125, 425);
	}
	
	@Override
	public void paintComponent(Graphics g){
		if(currentState == MENU){
		    drawMenuState(g);
		}else if(currentState == GAME){
		    drawGameState(g);
		}else if(currentState == END){
		    drawEndState(g);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(currentState == MENU){
		    updateMenuState();
		}else if(currentState == GAME){
		    updateGameState();
		}else if(currentState == END){
		    updateEndState();
		}
		repaint(); 
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode()==KeyEvent.VK_ENTER) {
		    if (currentState == END) {
		        currentState = MENU;
		        alienSpawn.stop();
		    } 
		    if (currentState == MENU) {
		    	currentState = GAME;
		    	startGame();
		    }
		    else {
		        currentState++;
		    }
		}
		if (e.getKeyCode()==KeyEvent.VK_SPACE) {
		    if (currentState == GAME) {
		    	objectManager.addProjectile(rockship.getProjectile());
		    }
		}
		if (e.getKeyCode()==KeyEvent.VK_UP) {
		    if (currentState == GAME) {
		    	if (rockship.y > 0) {
		    		System.out.println("UP, UP, and AWAY!");
		    		rockship.up();
		    	}
		    }
		}
		else if (e.getKeyCode()==KeyEvent.VK_DOWN) {
		    if (currentState == GAME) {
		    	if (rockship.y < 800 - 100) {
		    		rockship.down();
		    		System.out.println("DOWN, DOWN, and into the GROUND!");
		    	}
		    }
		}
		else if (e.getKeyCode()==KeyEvent.VK_LEFT) {
		    if (currentState == GAME) {
		    	if (rockship.x > 0) {
		    		rockship.left();
		    		System.out.println("I make a left turn here, right?");
		    	}
		    }
		}
		else if (e.getKeyCode()==KeyEvent.VK_RIGHT) {
		    if (currentState == GAME) {
		    	if (rockship.x < 500 - 75) {
		    		rockship.right();
		    		System.out.println("Right, I think so...");
		    	}
		    }
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
