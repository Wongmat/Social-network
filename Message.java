package Assignment2Files;

public class Message {
private String sender;
private String body;

Message (String sender, String body) {
	this.sender = sender;
	this.body = body;
}


public String getSender() {
	return sender;
}


public String getBody() {
	return body;
}


public void setSender(String sender) {
	this.sender = sender;
}

public void setBody(String body) {
	this.body = body;
}

public String MessageString (Data database) {
	return database.getUser(this.sender).getFirstName() + " " + database.getUser(this.sender).getLastName() + ": " + this.body;
}


}
