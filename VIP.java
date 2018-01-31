package Assignment2Files;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;

public class VIP extends User {
 VIP(String firstName, String LastName, String id, char gender, String LookingFor, double longitude, double latitude) {
  super(firstName, LastName, id, gender, LookingFor, longitude, latitude, 30);

 }

 @Override
 public String nearMeCalc(Data database) {
  tempList = new ArrayList < User > ();
  for (String key: database.getUserList().keySet()) {
   if (this.getFriendsList().contains(key) == false && key != this.getId()) {
    if (this.getLookingFor().equals("Both")) {
     tempList.add(database.getUser(key));
    } else if (this.getLookingFor().equals(Character.toString(database.getUser(key).getGender()))) {
     tempList.add(database.getUser(key));
    }
   }
  }
  Collections.sort(tempList, new DistanceComparator());
  String output = "";
  DecimalFormat numberFormat = new DecimalFormat("#.00");
  for (int x = 0; x < tempList.size() && x < 30; x++) {
   output += (x + 1) + ". " + tempList.get(x).getFirstName() + " " + tempList.get(x).getLastName() + " - " + numberFormat.format(tempList.get(x).getDistance()) + "km\n";
  }
  return output;
 }


}
