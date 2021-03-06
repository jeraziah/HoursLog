package backend;

import java.util.Calendar;
import java.util.Date;

public class Manager implements User {
	public int[] hours = new int[365];
	boolean manager = true;
	int overtime = 0;
	int id;
	boolean hoursApproved = true;
	boolean overtimeApproved = true;
	private int sickDaysRemaining = 10;
	private int vacationDaysRemaining = 10;
	double payScale;
	double taxRate;
	
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
		
		if(this.hours[converted] > 8) {
			overtime += (this.hours[converted] - 8);
		}
		
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
			getDailyHours(month, day + i);
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
		
		overtime = 0;
		for(int i = 0; i < converted; i++) {
			if(hours[i] > 8) {
				overtime = overtime + (hours[i] - 8);
			}
		}
		
		System.out.println("Employee Overtime: " + overtime);
		
		return true;
	}

	@Override
	public int getTotalHours() {
		int total = 0;
		for(int i = 0; i < 365; i++) {
			if(this.hours[i] >= 0) {
				total += this.hours[i];
			}
		}
		
		return total;
	}
	
	@Override
	public boolean viewMonthlyPay(int month) {
		double pay = 0;
		
		for(int i = Conversions.getFirstDayOfMonth(month); i < Conversions.getLastDayOfMonth(month); i++) {
			if(this.hours[i] > 8) {
				pay += 8 * this.payScale;
				if(this.overtimeApproved) {
					pay += (this.hours[i] - 8) * (this.payScale * 1.5);
				}
			}
			else if(this.hours[i] > 0) {
				pay += this.hours[i] * this.payScale;
			}
		}
		if(!this.hoursApproved) {
			pay = 0;
			System.out.println("Hours are currently awaiting approval.");
		}
		if(!this.overtimeApproved) {
			System.out.println("Overtime hours are currently awaiting approval.");
		}
		System.out.println("Current monthly pay is: $" + pay);
		System.out.println("Current net monthly pay is: $" + (pay * taxRate));
		
		return true;
	}
	
	public boolean useSick(int month, int dayOfMonth) {
		int converted = Conversions.convert(month,  dayOfMonth);
		
		if(sickDaysRemaining > 0) {
			sickDaysRemaining--;
			this.hours[converted] = -1;
			System.out.println("Sick days remaining: " + sickDaysRemaining);
			return true;
		}
		
		System.out.println("Insufficient sick days remaining.");
		return false;
	}
	
	public boolean useVacation(int month, int dayOfMonth) {
		int converted = Conversions.convert(month,  dayOfMonth);
		
		if(vacationDaysRemaining > 0) {
			vacationDaysRemaining--;
			this.hours[converted] = -2;
			System.out.println("Vacation days remaining: " + vacationDaysRemaining);
			return true;
		}
		
		System.out.println("Insufficient vacation days remaining.");
		return false;
	}
	
	public boolean viewSick() {
		System.out.println("Sick days remaining: " + sickDaysRemaining);
		return true;
	}
	
	public boolean viewVacation() {
		System.out.println("Vacation days remaining: " + vacationDaysRemaining);
		return true;
	}

}
