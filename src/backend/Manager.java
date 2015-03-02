package backend;

public class Manager implements User {
	public int[] hours = new int[365];
	boolean manager = true;
	int id;
	double payScale;
	
	public Manager(int id)
	{
		this.id = id;
	}
	
	@Override
	public boolean isManager() {
		return manager;
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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean getOvertime() {
		// TODO Auto-generated method stub
		return false;
	}


}
