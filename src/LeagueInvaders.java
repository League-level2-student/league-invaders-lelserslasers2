import javax.swing.JFrame;

public class LeagueInvaders {
	
	JFrame frame;
	GamePanel panel;
	public static final int WIDTH = 500;
	public static final int HEIGHT = 800;
	
	LeagueInvaders(){
		frame = new JFrame();
		panel = new GamePanel();
		frame.addKeyListener(panel);
	}
	
	void setup() {
		frame.add(panel);
		
		frame.setSize(WIDTH, HEIGHT);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("!:():!");
	}
	
	public static void main(String[] args) {
		LeagueInvaders mainFrame = new LeagueInvaders();
		mainFrame.setup();
	}
}
