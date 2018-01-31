package Assignment2Files;
import java.text.DecimalFormat;

public class FriendReq extends Message {
double distance;
String senderID;
	public double getDistance() {
	return distance;
}

public void setDistance(double distance) {
	this.distance = distance;
}

public String getSenderID() {
	return senderID;
}

public void setSenderID(String senderID) {
	this.senderID = senderID;
}

	FriendReq (String sender, String senderID, double distance) {
		super(sender, "");
		this.senderID = senderID;
		this.distance = distance;
	}
	
	public String openReq () {
		DecimalFormat numberFormat = new DecimalFormat("#.00");
		return this.getSender() + " - " + numberFormat.format(distance) + "km";
	}
}
