package backend;

public class Conversions {
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
