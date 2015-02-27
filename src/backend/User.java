package backend;

public interface User {
	
	public boolean isManager(int id);
	
	public int getID();
	
	public boolean setHours (int dayOfMonth, int hours);
	
	public boolean getDailyHours(int month, int dayOfMonth);
	
	public boolean getWeeklyHours(int month, int dayOfMonth);
	
	public boolean getYTD();
	
	public boolean getOvertime();
	
}
