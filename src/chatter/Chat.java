package chatter;

import java.util.Calendar;

public class Chat {
	
	private String user;
	private String message;
	private Calendar chatTime;
	
	public Chat(String user, String message, Calendar chatTime) {
		this.user = user;
		this.message = message;
		this.chatTime = chatTime;
	}

	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Calendar getChatTime() {
		return chatTime;
	}
	public void setChatTime(Calendar chatTime) {
		this.chatTime = chatTime;
	}
}
