import java.util.ArrayList;

public class ObjectManager {
	Rocketship rocketshipTwo;
	
	ArrayList<Projectile> shotsList = new ArrayList<Projectile>();
	ArrayList<Alien> alienList = new ArrayList<Alien>();
	
	ObjectManager(Rocketship rocketshipTwo){
		this.rocketshipTwo = rocketshipTwo;
	}
}
