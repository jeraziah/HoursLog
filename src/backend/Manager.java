package backend;

import java.util.Calendar;
import java.util.Date;

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
		Calendar calendar = Calendar.getInstance();
		Date date = new Date();
		calendar.setTime(date);
		int month = calendar.get(Calendar.MONTH) + 1;
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		int converted = Conversions.convert(month, day);
		
		int total = 0;
		for(int i = 0; i < converted; i++) {
			total = total + hours[i];
		}
		
		System.out.println("Employee YTD: " + total);
		
		return true;
	}

	@Override
	public boolean getOvertime() {
		// TODO Auto-generated method stub
		return false;
	}


}
