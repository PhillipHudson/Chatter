package chatter;

import static org.junit.Assert.*;

import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;

public class ChatterUtilsTest {
	private Calendar chatTime = Calendar.getInstance();
	
	@Before
	public void setUp() throws Exception {
		chatTime.setTimeInMillis(90000);		// 1 minute 30 seconds
	}

	@Test
	public final void testGetSince() {
		Calendar currentTime = Calendar.getInstance();
		String since;
		
		currentTime.setTimeInMillis(91000l);		// 1 second since chat
		since = ChatterUtils.getSince(currentTime, chatTime);
		assertTrue(since.equals("(1 second ago)"));
		
		currentTime.setTimeInMillis(120000l);		// 30 seconds since chat
		since = ChatterUtils.getSince(currentTime, chatTime);
		assertTrue(since.equals("(30 seconds ago)"));
		
		currentTime.setTimeInMillis(150000l);		// 1 minute since chat
		since = ChatterUtils.getSince(currentTime, chatTime);
		assertTrue(since.equals("(1 minute ago)"));
		
		currentTime.setTimeInMillis(151000l);		// 1 minute and 1 second since chat
		since = ChatterUtils.getSince(currentTime, chatTime);
		assertTrue(since.equals("(1 minute and 1 second ago)"));
		
		currentTime.setTimeInMillis(180000l);		// 1 minute and 30 seconds since chat
		since = ChatterUtils.getSince(currentTime, chatTime);
		assertTrue(since.equals("(1 minute and 30 seconds ago)"));
		
		currentTime.setTimeInMillis(210000l);		// 2 minutes since chat
		since = ChatterUtils.getSince(currentTime, chatTime);
		assertTrue(since.equals("(2 minutes ago)"));
		
		currentTime.setTimeInMillis(211000l);		// 2 minutes and 1 second since chat
		since = ChatterUtils.getSince(currentTime, chatTime);
		assertTrue(since.equals("(2 minutes and 1 second ago)"));
		
		currentTime.setTimeInMillis(240000l);		// 2 minutes and 30 seconds since chat
		since = ChatterUtils.getSince(currentTime, chatTime);
		assertTrue(since.equals("(2 minutes and 30 seconds ago)"));

		currentTime.setTimeInMillis(3690000l);		// 1 hour since chat
		since = ChatterUtils.getSince(currentTime, chatTime);
		assertTrue(since.equals("(1 hour ago)"));
		
		currentTime.setTimeInMillis(3691000l);		// 1 hour and 1 second since chat
		since = ChatterUtils.getSince(currentTime, chatTime);
		assertTrue(since.equals("(1 hour and 1 second ago)"));
		
		currentTime.setTimeInMillis(3720000l);		// 1 hour and 30 seconds since chat
		since = ChatterUtils.getSince(currentTime, chatTime);
		assertTrue(since.equals("(1 hour and 30 seconds ago)"));
		
		currentTime.setTimeInMillis(3750000l);		// 1 hour and 1 minute since chat
		since = ChatterUtils.getSince(currentTime, chatTime);
		assertTrue(since.equals("(1 hour and 1 minute ago)"));
		
		currentTime.setTimeInMillis(3751000l);		// 1 hour and 1 minute and 1 second since chat
		since = ChatterUtils.getSince(currentTime, chatTime);
		assertTrue(since.equals("(1 hour and 1 minute and 1 second ago)"));
		
		currentTime.setTimeInMillis(3780000l);		// 1 hour and 1 minute and 30 seconds since chat
		since = ChatterUtils.getSince(currentTime, chatTime);
		assertTrue(since.equals("(1 hour and 1 minute and 30 seconds ago)"));
		
		currentTime.setTimeInMillis(3810000l);		// 1 hour and 2 minutes since chat
		since = ChatterUtils.getSince(currentTime, chatTime);
		assertTrue(since.equals("(1 hour and 2 minutes ago)"));
		
		currentTime.setTimeInMillis(3811000l);		// 1 hour and 2 minutes and 1 second since chat
		since = ChatterUtils.getSince(currentTime, chatTime);
		assertTrue(since.equals("(1 hour and 2 minutes and 1 second ago)"));
		
		currentTime.setTimeInMillis(3840000l);		// 1 hour and 2 minutes and 30 seconds since chat
		since = ChatterUtils.getSince(currentTime, chatTime);
		assertTrue(since.equals("(1 hour and 2 minutes and 30 seconds ago)"));

		currentTime.setTimeInMillis(7290000l);		// 2 hours since chat
		since = ChatterUtils.getSince(currentTime, chatTime);
		assertTrue(since.equals("(2 hours ago)"));
		
		currentTime.setTimeInMillis(7291000l);		// 2 hours and 1 second since chat
		since = ChatterUtils.getSince(currentTime, chatTime);
		assertTrue(since.equals("(2 hours and 1 second ago)"));
		
		currentTime.setTimeInMillis(7320000l);		// 2 hours and 30 seconds since chat
		since = ChatterUtils.getSince(currentTime, chatTime);
		assertTrue(since.equals("(2 hours and 30 seconds ago)"));
		
		currentTime.setTimeInMillis(7350000l);		// 2 hours and 1 minute since chat
		since = ChatterUtils.getSince(currentTime, chatTime);
		assertTrue(since.equals("(2 hours and 1 minute ago)"));
		
		currentTime.setTimeInMillis(7351000l);		// 2 hours and 1 minute and 1 second since chat
		since = ChatterUtils.getSince(currentTime, chatTime);
		assertTrue(since.equals("(2 hours and 1 minute and 1 second ago)"));
		
		currentTime.setTimeInMillis(7380000l);		// 2 hours and 1 minute and 30 seconds since chat
		since = ChatterUtils.getSince(currentTime, chatTime);
		assertTrue(since.equals("(2 hours and 1 minute and 30 seconds ago)"));
		
		currentTime.setTimeInMillis(7410000l);		// 2 hours and 2 minutes since chat
		since = ChatterUtils.getSince(currentTime, chatTime);
		assertTrue(since.equals("(2 hours and 2 minutes ago)"));
		
		currentTime.setTimeInMillis(7411000l);		// 2 hours and 2 minutes and 1 second since chat
		since = ChatterUtils.getSince(currentTime, chatTime);
		assertTrue(since.equals("(2 hours and 2 minutes and 1 second ago)"));
		
		currentTime.setTimeInMillis(7440000l);		// 2 hours and 2 minutes and 30 seconds since chat
		since = ChatterUtils.getSince(currentTime, chatTime);
		assertTrue(since.equals("(2 hours and 2 minutes and 30 seconds ago)"));
	}
}
