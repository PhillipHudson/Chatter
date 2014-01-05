package chatter;

import static org.junit.Assert.*;
import java.util.Calendar;
import org.junit.Before;
import org.junit.Test;

public class ChatTest {
	private static final String CHAT_USER = new String("Alice");
	private static final String CHAT_TEST_MESSAGE = new String("Chat test message");
	
	private Chat chat;

	@Before
	public void setUp() throws Exception {
		Calendar chatTime = Calendar.getInstance(); 
		chatTime.setTimeInMillis(90000);		// 1 minute 30 seconds
		chat = new Chat(CHAT_USER, CHAT_TEST_MESSAGE, chatTime);
	}

	@Test
	public final void testGetUser() {
		String user = chat.getUser();
		assertTrue(user.equals(CHAT_USER));
	}

	@Test
	public final void testSetUser() {
		String user;
		
		chat.setUser("Charlie");
		user = chat.getUser();
		assertTrue(user.equals("Charlie"));
	}

	@Test
	public final void testGetMessage() {
		String message = chat.getMessage();
		assertTrue(message.equals(CHAT_TEST_MESSAGE));
	}

	@Test
	public final void testSetMessage() {
		String message;
		
		chat.setMessage("A different test message");
		message = chat.getMessage();
		assertTrue(message.equals("A different test message"));
	}

	@Test
	public final void testGetChatTime() {
		Calendar chatTime = chat.getChatTime();
		assertTrue(chatTime.getTimeInMillis() == 90000);
	}

	@Test
	public final void testSetChatTime() {
		Calendar chatTime = Calendar.getInstance();
		chatTime.setTimeInMillis(180000l);
		
		chat.setChatTime(chatTime);
		assertTrue(chat.getChatTime().getTimeInMillis() == 180000);
	}
}
