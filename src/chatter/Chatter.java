package chatter;

import java.io.Console;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Chatter {
	
	protected static ArrayList<Chat> allChats = new ArrayList<Chat>();
	protected static HashMap<String, List<String>> allFollowings = new HashMap<String, List<String>>();
	
	/**
	 * Display all posts submitted by a user
	 * 
	 * @param userToRead The name of the user whose posts are to be displayed
	 */
	protected static void doReading(String userToRead) {
		displayUserChats(userToRead, false);
	}

	/**
	 * Store the users message
	 * 
	 * @param posting String array containing the posting command
	 */
	protected static void doPosting(String[] posting) {
		StringBuilder message = new StringBuilder();
		
		for(int index = 2; index < posting.length; index++) {
			if(index > 2) {
				message.append(" ");
			}
			message.append(posting[index]);
		}
		
		allChats.add(new Chat(posting[0], message.toString(), Calendar.getInstance()));
	}
	
	/**
	 * Register the user to follow
	 * 
	 * @param posting String array containing the follows command
	 */
	protected static void doFollowing(String[] posting) {
		List<String> followingUsers = allFollowings.get(posting[0]);
		
		if(followingUsers == null) {
			followingUsers = new ArrayList<String>();
		}
		if(!followingUsers.contains(posting[2])) {
			followingUsers.add(posting[2]);
		}

		allFollowings.put(posting[0], followingUsers);
	}
	
	/**
	 * Display the users wall
	 * 
	 * @param userThe name of the user whose wall is to be displayed
	 */
	protected static void doWall(String user) {
		displayUserChats(user, true);
		
		for(Map.Entry<String, List<String>> allFollowingUsers : allFollowings.entrySet()) {
			if(allFollowingUsers.getKey().equalsIgnoreCase(user)) {
				for(String followingUser : allFollowingUsers.getValue()) {
					displayUserChats(followingUser, true);
				}
			}
		}
	}

	private static void displayUserChats(String user, boolean displayUsername) {
		for(Chat chat : allChats) {
			if(chat.getUser().equalsIgnoreCase(user)) {
				if(displayUsername) {
					System.out.print(user + " - ");
				}
				System.out.println(chat.getMessage() + " " + ChatterUtils.getSince(Calendar.getInstance(), chat.getChatTime()));
			}
		}
	}

	public static void main(String[] args) {
		Console console = System.console();
		
		while(true) {
			String command = console.readLine("> ");
			
			String[] commandParts = command.split(" ");
			
			if(commandParts.length == 1) {	// Reading
				doReading(commandParts[0]);
			} else {
				if(commandParts[1].equals("->")) { // Posting
					doPosting(commandParts);
				} else if(commandParts[1].equalsIgnoreCase("follows")) { // Following
					doFollowing(commandParts);
				} else if(commandParts[1].equalsIgnoreCase("wall")) { // Wall
					doWall(commandParts[0]);
				}
			}
		}
	}
}
