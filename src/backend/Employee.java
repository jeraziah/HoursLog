package backend;

public class Employee implements User {
	public int[] hours = new int[365];
	int id;
	
	public Employee (int id) {
		this.id = id;
	}

	@Override
	public boolean isManager() {
		return false;
	}

	@Override
	public int getID() {
		return id;
	}
	
	@Override
	public boolean setHours(int month, int dayOfMonth, int hours) {
		int converted = convert(month, dayOfMonth);
		
		if(!inMonth(month, dayOfMonth)) { return false; }
		this.hours[converted] = hours;
		
		return true;
	}

	@Override
	public boolean getDailyHours(int month, int dayOfMonth) {
		int converted = convert(month, dayOfMonth);
		
		System.out.println(this.hours[converted]);
		return false;
	}

	@Override
	public boolean getWeeklyHours(int month, int dayOfMonth) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean getYTD() {
		// TODO Iteration 3
		return false;
	}

	@Override
	public boolean getOvertime() {
		// TODO Iteration 3
		return false;
	}
	
	public int convert(int month, int dayOfMonth) {
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
	
	public boolean inMonth(int month, int dayOfMonth) {
		
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
