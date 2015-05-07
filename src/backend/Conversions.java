package backend;

import java.util.Calendar;

public class Conversions {
	public static int convertDayToMonth(int dayOfYear){
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_YEAR, dayOfYear);
		return calendar.MONTH;		
	}
	public static int convertDayToDayOfMonth(int dayOfYear){
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_YEAR, dayOfYear);
		return calendar.DAY_OF_MONTH;			
	}
	
	public static int convert(int month, int dayOfMonth) {
		if(inMonth(month, dayOfMonth))
		{
			int result = 0;
			
			// Converts month and day integers into a single integer representing the day of the year.
			if(month == 1) { result += dayOfMonth; }
			if(month == 2) { result += (31 + dayOfMonth); }
			if(month == 3) { result += (59 + dayOfMonth); }
			if(month == 4) { result += (90 + dayOfMonth); }
			if(month == 5) { result += (120 + dayOfMonth); }
			if(month == 6) { result += (151 + dayOfMonth); }
			if(month == 7) { result += (181 + dayOfMonth); }
			if(month == 8) { result += (212 + dayOfMonth); }
			if(month == 9) { result += (243 + dayOfMonth); }
			if(month == 10) { result += (273 + dayOfMonth); }
			if(month == 11) { result += (304 + dayOfMonth); }
			if(month == 12) { result += (334 + dayOfMonth); }
			
			return result;
		}
		else{
			throw new IndexOutOfBoundsException();
		}
	}
	
	public static int getFirstDayOfMonth(int month) {
		if(month == 1) { return 0; }
		if(month == 2) { return 31; }
		if(month == 3) { return 59; }
		if(month == 4) { return 90; }
		if(month == 5) { return 120; }
		if(month == 6) { return 151; }
		if(month == 7) { return 181; }
		if(month == 8) { return 212; }
		if(month == 9) { return 243; }
		if(month == 10) { return 273; }
		if(month == 11) { return 304; }
		if(month == 12) { return 334; }
		return 0;
	}
	
	public static int getLastDayOfMonth(int month) {
		if(month == 1) { return 30; }
		if(month == 2) { return 58; }
		if(month == 3) { return 89; }
		if(month == 4) { return 119; }
		if(month == 5) { return 150; }
		if(month == 6) { return 180; }
		if(month == 7) { return 211; }
		if(month == 8) { return 242; }
		if(month == 9) { return 272; }
		if(month == 10) { return 303; }
		if(month == 11) { return 333; }
		if(month == 12) { return 364; }
		return 0;
	}
	
	public static boolean inMonth(int month, int dayOfMonth) {
		
		// Verifies that the day integer provided is contained in the month.
		if(month == 1 && dayOfMonth <= 31 && dayOfMonth >= 1) { return true; }
		if(month == 2 && dayOfMonth <= 28 && dayOfMonth >= 1) { return true; }
		if(month == 3 && dayOfMonth <= 31 && dayOfMonth >= 1) { return true; }
		if(month == 4 && dayOfMonth <= 30 && dayOfMonth >= 1) { return true; }
		if(month == 5 && dayOfMonth <= 31 && dayOfMonth >= 1) { return true; }
		if(month == 6 && dayOfMonth <= 30 && dayOfMonth >= 1) { return true; }
		if(month == 7 && dayOfMonth <= 31 && dayOfMonth >= 1) { return true; }
		if(month == 8 && dayOfMonth <= 31 && dayOfMonth >= 1) { return true; }
		if(month == 9 && dayOfMonth <= 30 && dayOfMonth >= 1) { return true; }
		if(month == 10 && dayOfMonth <= 31 && dayOfMonth >= 1) { return true; }
		if(month == 11 && dayOfMonth <= 30 && dayOfMonth >= 1) { return true; }
		if(month == 12 && dayOfMonth <= 31 && dayOfMonth >= 1) { return true; }
		
		return false;
	}

}
