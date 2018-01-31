package Assignment2Files;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Collections;
import java.util.List;
public class Common extends User {
Common (String firstName, String LastName, String id, char gender, String LookingFor, double longitude, double latitude) {
super (firstName, LastName, id, gender, LookingFor, longitude, latitude, 10);

}

public String nearMeCalc (Data database) {
	this.tempList = new ArrayList<User> ();
	for (String key: database.getUserList().keySet()) {
		if (this.getFriendsList().contains(key) == false && key != this.getId()) {
			if (database.getUser(key).getLookingFor().equals("Both")) {
			if (this.getLookingFor().equals("Both")) {
			tempList.add(database.getUser(key));
		}
			else if (this.getLookingFor().equals(Character.toString(database.getUser(key).getGender()))) {
				tempList.add(database.getUser(key));
			}
			}
			else if (database.getUser(key).getLookingFor().equals(Character.toString(this.getGender()))) {
				
				if (this.getLookingFor() == "Both") {
					tempList.add(database.getUser(key));
				}
					else if (this.getLookingFor().equals(Character.toString(database.getUser(key).getGender()))) {
						tempList.add(database.getUser(key));
					}
	}
			}
	}
	Collections.sort(tempList, new DistanceComparator());
	String output = "";
	DecimalFormat numberFormat = new DecimalFormat("#.00");
	for (int x = 0; x < tempList.size() && x < 11; x++) {
		output += (x + 1) + ". " + tempList.get(x).getFirstName() + " " + tempList.get(x).getLastName() + " - " + numberFormat.format(tempList.get(x).getDistance()) + "km\n";
	}
	return output;
}
}