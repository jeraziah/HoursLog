package backend;

public interface User {
	
	public boolean isManager();
	
	public int getID();
	
	public boolean setHours (int month, int dayOfMonth, int hours);
	
	public boolean getDailyHours(int month, int dayOfMonth);
	
	public boolean getWeeklyHours(int month, int dayOfMonth);
	
	public boolean getYTD();
	
	public boolean getOvertime();
	
	public int getTotalHours();
}

