	package backend;

import java.util.Calendar;
import java.util.Date;

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
		Calendar calendar = Calendar.getInstance();
		Date date = new Date();
		calendar.setTime(date);
		int month = calendar.get(Calendar.MONTH) + 1;
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		int converted = Conversions.convert(month, day);
		
		int overtime = 0;
		for(int i = 0; i < converted; i++) {
			if(hours[i] > 8) {
				overtime = overtime + (hours[i] - 8);
			}
		}
		
		System.out.println("Employee Overtime: " + overtime);
		
		return true;
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
