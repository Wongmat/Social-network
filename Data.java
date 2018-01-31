package Assignment2Files;
import java.io.File;
import java.util.Hashtable;
import java.util.Scanner;

public class Data {
 File user;
 File friendship;
 public Hashtable < String, User > getUserList() {
  return userList;
 }

 private Hashtable < String, User > userList;



 public Data(File user, File friendship) {
  userList = new Hashtable < String, User > ();
  this.user = user;
  this.friendship = friendship;
 }

 public void fillData() {
  Scanner inputFile = null;
  char role = ' ';
  String firstName = null;
  String lastName = null;
  String id = null;
  char gender = ' ';
  String LookingFor;
  double longitude;
  double latitude;
  try {
   inputFile = new Scanner(user);
   inputFile.nextLine();
   while (inputFile.hasNextLine() == true) {
    String line = inputFile.nextLine();

    String tokens[] = line.split(" |\t");
    int x = 0;
    if (tokens[0].startsWith("V")) {
     role = 'v';
    } else if (tokens[0].startsWith("C")) {
     role = 'c';
    } else if (tokens[0].startsWith("M")) {
     role = 'm';
    }

    if (role == 'v' || role == 'm') {
     firstName = tokens[1];
     x = 2;
     for (; Character.isUpperCase(tokens[x].charAt(0)) == true && Character.isUpperCase(tokens[x + 1].charAt(0)) == true; x++) {
      firstName += " " + tokens[x];
     }
     if (Character.isUpperCase(tokens[x].charAt(0)) == true) {
      lastName = tokens[x];
     } else {
      lastName = " ";
      x--;
     }

    } else if (role == 'c') {
     firstName = tokens[2];
     x = 3;
     for (; Character.isUpperCase(tokens[x].charAt(0)) == true && Character.isUpperCase(tokens[x + 1].charAt(0)) == true; x++) {
      firstName += " " + tokens[x];
     }
     if (Character.isUpperCase(tokens[x].charAt(0)) == true) {
      lastName = tokens[x];
     } else {
      lastName = " ";
      x--;
     }

    }
    id = tokens[x + 1];
    gender = tokens[x + 2].charAt(0);
    LookingFor = tokens[x + 3];
    latitude = Double.parseDouble(tokens[x + 4]);
    longitude = Double.parseDouble(tokens[x + 5]);
    if (role == 'v') {
     userList.put(id, new VIP(firstName, lastName, id, gender, LookingFor, longitude, latitude));
    }
    if (role == 'm') {
     userList.put(id, new Member(firstName, lastName, id, gender, LookingFor, longitude, latitude));
    }

    if (role == 'c') {
     userList.put(id, new Common(firstName, lastName, id, gender, LookingFor, longitude, latitude));
    }
   }
  } catch (Exception e) {
   System.err.println("Error: " + e.getMessage());
  } finally {
   inputFile.close();

  }
 }

 public String friendsToString() {
  String list = "";
  for (String key: userList.keySet()) {
   list += userList.get(key).getFriendsList().toString();
  }
  return list;
 }


 public void fillFriendships() {
  Scanner inputFile = null;
  try {
   inputFile = new Scanner(friendship);
   inputFile.nextLine();
   while (inputFile.hasNextLine() == true) {
    String line = inputFile.nextLine();
    String tokens[] = line.split("\t");
    userList.get(tokens[0]).getFriendsList().add(tokens[2]);
    userList.get(tokens[2]).getFriendsList().add(tokens[0]);
   }
  } catch (Exception e) {
   System.err.println("Error: " + e.getMessage());
  } finally {
   inputFile.close();

  }


 }

 public String printFriends(String user) {
  String result = "";
  for (int i = 0; i < getUser(user).getFriendsList().size(); i++) {
   result += i + 1 + ". " + getUser(userList.get(user).getFriend(i)).getFirstName() + " " + getUser(getUser(user).getFriend(i)).getLastName() + "\n"; //get first name and last name of friend
  }


  return result;
 }

 public User getUser(String tag) {
  return userList.get(tag);
 }

 public String printInbox(String user) {
  String result = "";
  for (int i = 0; i < getUser(user).getInbox().size(); i++) {
   result += getUser(user).getInbox().get(i).getSender() + " " + getUser(userList.get(user).getFriend(i)).getFirstName() + getUser(getUser(user).getFriend(i)).getLastName() + "\n"; //get first name and last name of friend

  }
  return result;
 }

 public void fillDistances(String id) {
  for (String key: userList.keySet()) {
   userList.get(key).setDistance(userList.get(key).haversine(userList.get(id).getLongitude(), userList.get(id).getLatitude(), userList.get(key).getLongitude(), userList.get(key).getLatitude()));
  }
 }
}
		
	
	
	


