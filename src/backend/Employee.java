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
		int converted = Conversions.convert(month, dayOfMonth);
	
		this.hours[converted] = hours;
		
		return true;
	}

	@Override
	public boolean getDailyHours(int month, int dayOfMonth) {
		int converted = Conversions.convert(month, dayOfMonth);
		
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
	
}
