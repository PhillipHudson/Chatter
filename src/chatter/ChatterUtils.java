package chatter;

import java.util.Calendar;

public class ChatterUtils {

	/**
	 * Accepts two Calendar objects, calculates the difference in time between them and returns a string
	 * describing the difference in time. The currentTime must be a later date to chatTime.
	 * 
	 * @param currentTime
	 * @param chatTime
	 * @return The description of the time difference between the two Calendar objects.
	 */
	public static String getSince(Calendar currentTime, Calendar chatTime) {
		StringBuilder since = new StringBuilder();
		Calendar difference = Calendar.getInstance();
		
		difference.setTimeInMillis(currentTime.getTimeInMillis() - chatTime.getTimeInMillis());
		
		int hour = difference.get(Calendar.HOUR_OF_DAY) - 1;
		int minute = difference.get(Calendar.MINUTE);
		int second = difference.get(Calendar.SECOND);
		
		if(hour > 0) {
			since.append(hour + " hour");
			if(hour > 1) {
				since.append("s");
			}
		}
		if(minute > 0 && since.length() > 0) {
			since.append(" and ");
		}
		if(minute > 0) {
			since.append(minute + " minute");
			if(minute > 1) {
				since.append("s");
			}
		}
		if(second > 0 && since.length() > 0) {
			since.append(" and ");
		}
		if(second > 0) {
			since.append(second + " second");
			if(second > 1) {
				since.append("s");
			}
		}
		
		return "(" + since.toString() + " ago)";
	}

}
