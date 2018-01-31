package Assignment2Files;
import java.util.Comparator;
public class DistanceComparator implements Comparator<User>{
	
	public int compare(User user1, User user2) {
		return Double.compare(user1.getDistance(), user2.getDistance());
		
	}
}


