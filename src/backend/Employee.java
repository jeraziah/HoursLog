	package backend;

public class Employee implements User {
	public int[] hours = new int[365];
	int id;
	private int sickDaysRemaining = 10;
	private int vacationDaysRemaining = 10;
	private boolean approved = false;
	double payScale;
	
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
	public boolean getWeeklyHours(int month, int firstDayOfWeek) {
		int day = firstDayOfWeek;
		for(int i = 0; i < 7;i++)
		{	
			getDailyHours(month, day);
		}
		
		return true;
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
	
	public boolean useSick(int month, int dayOfMonth) {
		int converted = Conversions.convert(month,  dayOfMonth);
		
		if(sickDaysRemaining > 0) {
			sickDaysRemaining--;
			this.hours[converted] = -1;
			return true;
		}
		
		return false;
	}
	
	public boolean useVacation(int month, int dayOfMonth) {
		int converted = Conversions.convert(month,  dayOfMonth);
		
		if(vacationDaysRemaining > 0) {
			vacationDaysRemaining--;
			this.hours[converted] = -2;
			return true;
		}
		
		return false;
	}
	
	public boolean viewSick() {
		System.out.println("Sick days left: " + sickDaysRemaining);
		return true;
	}
	
	public boolean viewVacation() {
		System.out.println("Vacation days left: " + vacationDaysRemaining);
		return true;
	}
	
	public boolean approveHours() {
		this.approved = true;
		return true;
	}
	
	public boolean approved() {
		return this.approved;
	}
}
