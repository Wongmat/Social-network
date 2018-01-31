package Assignment2Files;
import java.util.ArrayList;
public abstract class User {
	private String firstName = null;
	private String lastName = null;
	private String id = null;
	private char gender = ' ';
	private String LookingFor = null;
	private double longitude = 0;
	private double latitude = 0;
	private ArrayList <Message> inbox;
	private ArrayList <String> friendsList;
	private ArrayList <FriendReq> reqBox;
	ArrayList <User>tempList;
	private double distance = 0;

	User (String firstName, String lastName, String id, char gender, String LookingFor, double longitude, double latitude, int nearMeSize) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.id = id;
		this.gender = gender;
		this.LookingFor = LookingFor;
		this.longitude = longitude;
		this.latitude = latitude;
		this.inbox = new ArrayList<Message> ();
		this.friendsList = new ArrayList<String> ();
		this.reqBox = new ArrayList <FriendReq>();
		
		
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}

	public String getLookingFor() {
		return LookingFor;
	}

	public void setLookingFor(String lookingFor) {
		LookingFor = lookingFor;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	
	public String getFriend (int index) {
		return friendsList.get(index);
	}
	
	public void newMsg (String sender, String body) {
		inbox.add(new Message(sender, body));
	}
	
	
	
public ArrayList<Message> getInbox() {
	return inbox;
}

public void setInbox(ArrayList<Message> inbox) {
	this.inbox = inbox;
}

public ArrayList<String> getFriendsList() {
	return friendsList;
}

public double getDistance() {
	return distance;
}

public void setDistance(double distance) {
	this.distance = distance;
}

public void setFriendsList(ArrayList<String> friendsList) {
	this.friendsList = friendsList;
}

public ArrayList<FriendReq> getReqBox() {
	return reqBox;
}

public void setReqBox(ArrayList<FriendReq> reqBox) {
	this.reqBox = reqBox;
}



public abstract String nearMeCalc (Data database); 

public double haversine (double long1, double lat1, double long2, double lat2) {
	double r = 6371; // km
	
	double subLat = Math.toRadians(lat2-lat1);
	double subLong = Math.toRadians(long2-long1);

	double a = Math.sin(subLat/2) * Math.sin(subLat/2) +
	        Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
	        Math.sin(subLong/2) * Math.sin(subLong/2);
	double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));

	return r * c;
	
}
}
