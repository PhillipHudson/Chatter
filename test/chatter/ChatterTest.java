package chatter;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.Console;
import java.io.PrintStream;
import java.util.Calendar;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ChatterTest {
	private static final String CHAT_USER_ALICE = new String("Alice");
	private static final String CHAT_USER_BOB = new String("Bob");
	private static final String CHAT_USER_CHARLIE = new String("Charlie");
	
	private static final String COMMAND_POST = new String("->");
	private static final String COMMAND_FOLLOW = new String("follows");

	private static final String CHAT_USER_ALICE_TEST_MESSAGE_1 = new String("I love the weather today");
	private static final String CHAT_USER_BOB_TEST_MESSAGE_1 = new String("Oh, we lost!");
	private static final String CHAT_USER_BOB_TEST_MESSAGE_2 = new String("at least it's sunny");
	private static final String CHAT_USER_CHARLIE_TEST_MESSAGE_1 = new String("I'm in New York today! Anyone wants to have a coffee?");
	
	private static ByteArrayOutputStream stdout = new ByteArrayOutputStream();
	
	Console console;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.setOut(new PrintStream(stdout));
	}

	@Before
	public void setUp() throws Exception {
	}
	
	@After
	public void tearDown() throws Exception {
		Chatter.allChats.clear();
	}

	@Test
	public final void testDoPosting() {
		String[] command;
		
		command = new String[]{CHAT_USER_ALICE, COMMAND_POST, CHAT_USER_ALICE_TEST_MESSAGE_1};
		Chatter.doPosting(command);
		assertTrue(Chatter.allChats.size() == 1);
		
		command = new String[]{CHAT_USER_BOB, COMMAND_POST, CHAT_USER_BOB_TEST_MESSAGE_1};
		Chatter.doPosting(command);
		assertTrue(Chatter.allChats.size() == 2);

		command = new String[]{CHAT_USER_BOB, COMMAND_POST, CHAT_USER_BOB_TEST_MESSAGE_2};
		Chatter.doPosting(command);
		assertTrue(Chatter.allChats.size() == 3);
	}

	@Test
	public final void testDoReading() {
		Calendar readingTime = null;
		Calendar chatTime = null;
		String display;
		String[] command;

		chatTime = Calendar.getInstance();
		command = new String[]{CHAT_USER_ALICE, COMMAND_POST, CHAT_USER_ALICE_TEST_MESSAGE_1};
		Chatter.doPosting(command);
		command = new String[]{CHAT_USER_BOB, COMMAND_POST, CHAT_USER_BOB_TEST_MESSAGE_1};
		Chatter.doPosting(command);
		command = new String[]{CHAT_USER_BOB, COMMAND_POST, CHAT_USER_BOB_TEST_MESSAGE_2};
		Chatter.doPosting(command);

		readingTime = Calendar.getInstance();
		Chatter.doReading(CHAT_USER_ALICE);
		display = stdout.toString().trim();
		stdout.reset();
		assertTrue(display.equals(CHAT_USER_ALICE_TEST_MESSAGE_1 + " " + ChatterUtils.getSince(readingTime, chatTime)));

		readingTime = Calendar.getInstance();
		Chatter.doReading(CHAT_USER_BOB);
		display = stdout.toString().trim();
		stdout.reset();
		assertTrue(display.equals(
				CHAT_USER_BOB_TEST_MESSAGE_1 + " " + ChatterUtils.getSince(readingTime, chatTime) + "\r\n" +
				CHAT_USER_BOB_TEST_MESSAGE_2 + " " + ChatterUtils.getSince(readingTime, chatTime)));

	}

	@Test
	public final void testDoFollowingAndWall() {
		Calendar readingTime = null;
		Calendar chatTime = null;
		String display;
		String[] command;

		chatTime = Calendar.getInstance();
		command = new String[]{CHAT_USER_ALICE, COMMAND_POST, CHAT_USER_ALICE_TEST_MESSAGE_1};
		Chatter.doPosting(command);
		command = new String[]{CHAT_USER_BOB, COMMAND_POST, CHAT_USER_BOB_TEST_MESSAGE_1};
		Chatter.doPosting(command);
		command = new String[]{CHAT_USER_BOB, COMMAND_POST, CHAT_USER_BOB_TEST_MESSAGE_2};
		Chatter.doPosting(command);
		command = new String[]{CHAT_USER_CHARLIE, COMMAND_POST, CHAT_USER_CHARLIE_TEST_MESSAGE_1};
		Chatter.doPosting(command);
		
		command = new String[]{CHAT_USER_CHARLIE, COMMAND_FOLLOW, CHAT_USER_ALICE};
		Chatter.doFollowing(command);
		assertTrue(Chatter.allFollowings.size() == 1);
		assertTrue(Chatter.allFollowings.get(CHAT_USER_CHARLIE).size() == 1);

		readingTime = Calendar.getInstance();
		Chatter.doWall(CHAT_USER_CHARLIE);
		display = stdout.toString().trim();
		stdout.reset();
		assertTrue(display.equals(
				CHAT_USER_CHARLIE + " - " + CHAT_USER_CHARLIE_TEST_MESSAGE_1 + " " + ChatterUtils.getSince(readingTime, chatTime) + "\r\n" +
				CHAT_USER_ALICE + " - " +  CHAT_USER_ALICE_TEST_MESSAGE_1 + " " + ChatterUtils.getSince(readingTime, chatTime)));
		
		command = new String[]{CHAT_USER_CHARLIE, COMMAND_FOLLOW, CHAT_USER_BOB};
		Chatter.doFollowing(command);
		assertTrue(Chatter.allFollowings.size() == 1);
		assertTrue(Chatter.allFollowings.get(CHAT_USER_CHARLIE).size() == 2);

		readingTime = Calendar.getInstance();
		Chatter.doWall(CHAT_USER_CHARLIE);
		display = stdout.toString().trim();
		stdout.reset();
		assertTrue(display.equals(
				CHAT_USER_CHARLIE + " - " + CHAT_USER_CHARLIE_TEST_MESSAGE_1 + " " + ChatterUtils.getSince(readingTime, chatTime) + "\r\n" +
				CHAT_USER_ALICE + " - " +  CHAT_USER_ALICE_TEST_MESSAGE_1 + " " + ChatterUtils.getSince(readingTime, chatTime) + "\r\n" +
				CHAT_USER_BOB + " - " +  CHAT_USER_BOB_TEST_MESSAGE_1 + " " + ChatterUtils.getSince(readingTime, chatTime) + "\r\n" +
				CHAT_USER_BOB + " - " +  CHAT_USER_BOB_TEST_MESSAGE_2 + " " + ChatterUtils.getSince(readingTime, chatTime)));
	}
}
