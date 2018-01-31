package Assignment2Files;
import java.util.Scanner;
import java.io.File;
public class network {

 public static void main(String[] args) {
  // TODO Auto-generated method stub
  File user = new File("user.txt");
  File friendship = new File("friendship.txt");
  Data database = new Data(user, friendship);
  database.fillData();
  database.fillFriendships();
  boolean masterLoop = true;
  while (masterLoop == true) {
   System.out.println("Welcome to the location based social network system.");
   boolean valid = false;
   System.out.println("Please enter an ID...or type -1 to exit.");
   Scanner in = new Scanner(System.in);
   boolean endLoop = false;
   String currentUser = "";

   while (valid == false) {
    String input = in .next();
    if (input.equals("-1")) {
     System.out.println("Thank you for using the system. Goodbye!");
     masterLoop = false;
     endLoop = true;
     break;
    } else if (database.getUserList().containsKey(input) != true) {
     System.out.println("Invalid input. Please enter a valid ID.");
    } else
    if (database.getUser(input) instanceof Common) {
     System.out.println("Hello, Common User: " + database.getUser(input).getFirstName() + " " + database.getUser(input).getLastName());
     valid = true;
     currentUser = input;
    } else if (database.getUser(input) instanceof VIP) {
     System.out.println("Hello, VIP: " + database.getUser(input).getFirstName() + " " + database.getUser(input).getLastName());
     valid = true;
     currentUser = input;
    } else if (database.getUser(input) instanceof Member) {
     System.out.println("Hello, Member: " + database.getUser(input).getFirstName() + " " + database.getUser(input).getLastName());
     valid = true;
     currentUser = input;
    }
   }
   database.fillDistances(currentUser);
   while (endLoop == false) {
    System.out.println("Menu:");
    System.out.println("1. Search nearby strangers");
    System.out.println("2. Manage friend requests");
    System.out.println("3. Send messages");
    System.out.println("4. Receive messages");
    System.out.println("5. Manage friends list");
    System.out.println("6. Switch user");
    System.out.println("-1 Quit");
    System.out.println("\nEnter a choice: ");
    int choice = in .nextInt();
    switch (choice) {
     case 1:
      {
       System.out.println("Strangers nearby (Enter 0 to return to menu):");
       System.out.println(database.getUser(currentUser).nearMeCalc(database));
       int req = in .nextInt();
       if (req != 0) {
        String name = database.getUser(currentUser).getFirstName() + " " + database.getUser(currentUser).getLastName();
        String receiverTag = "";
        receiverTag = database.getUser(currentUser).tempList.get(req - 1).getId();
        database.getUser(receiverTag).getReqBox().add(new FriendReq(name, currentUser, database.getUser(currentUser).tempList.get(req - 1).getDistance()));
        System.out.println("Friend request has been sent to " + database.getUser(currentUser).tempList.get(req - 1).getFirstName() + " " + database.getUser(currentUser).tempList.get(req - 1).getLastName());
       }
       break;
      }
     case 2:
      {
       if (database.getUser(currentUser).getReqBox().isEmpty()) {
        System.out.println("No friend requests.");
        break;
       }
       else
       for (int x = 0; x < database.getUser(currentUser).getReqBox().size(); x++) {
        System.out.println("Friend requests (Enter 0 to return to menu): ");
        System.out.println((x + 1) + ". " + database.getUser(currentUser).getReqBox().get(x).openReq());
       }
       int input = in .nextInt();
       if (input == 0) {
        break;
       } else database.getUser(currentUser).getFriendsList().add(database.getUser(currentUser).getReqBox().get(input - 1).getSenderID());
       database.getUser(database.getUser(currentUser).getReqBox().get(input - 1).getSenderID()).getFriendsList().add(currentUser);
       break;
      }
     case 3:
      {
       System.out.println("Send messages to (Enter 0 to return to menu): ");
       System.out.print(database.printFriends(currentUser));
       String body = "";
       int input = in .nextInt();
       if (input == 0) {
        break;
       } else {
        String receiver = database.getUser(currentUser).getFriend(input - 1);
        System.out.println("To: " + database.getUser(receiver).getFirstName() + database.getUser(receiver).getLastName());
        if ( in .nextLine() == "\n") { in .close();
        }
        body = in .nextLine();
        database.getUser(receiver).newMsg(currentUser, body);
        System.out.println("Message sent.");



       }
       break;
      }
     case 4:
      {
       System.out.println("Messages: ");
       if (database.getUser(currentUser).getInbox().isEmpty()) {
        System.out.println("No messages.");
       } else
        for (int x = 0; x < database.getUser(currentUser).getInbox().size(); x++) {
         String senderID = database.getUser(currentUser).getInbox().get(x).getSender();
         System.out.println(database.getUser(senderID).getFirstName() + " " +
          database.getUser(senderID).getLastName() + ": " + database.getUser(currentUser).getInbox().get(x).getBody());
        }
       break;
      }
     case 5:
      {
       System.out.println("Select to unfriend (Enter 0 to return to menu): ");
       System.out.print(database.printFriends(currentUser));
       int input = in .nextInt();
       if (input == 0) {
        break;
       } else 
    	   System.out.println(database.getUser(database.getUser(currentUser).getFriendsList().get(input - 1)).getFirstName() + " " + 
    			   database.getUser(database.getUser(currentUser).getFriendsList().get(input - 1)).getLastName() + " has been removed.");
    	   database.getUser(currentUser).getFriendsList().remove(input - 1);
       break;
      }



     case 6:
      {
       endLoop = true;
       valid = false;
       break;
      }

     case -1:
      {
       endLoop = true;
       valid = true;
       masterLoop = false;
       System.out.println("Thank you for using the system. Goodbye!");
       break;
      }







    }
   }
  }
 }
}

