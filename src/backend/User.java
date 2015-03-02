package backend;

import java.util.ArrayList;

public interface User {
	
	public ArrayList<ArrayList<Integer>> year = new ArrayList<ArrayList<Integer>>();
	
	public boolean isManager();
	
	public int getID();
	
	public boolean setHours (int month, int dayOfMonth, int hours);
	
	public boolean getDailyHours(int month, int dayOfMonth);
	
	public boolean getWeeklyHours(int month, int dayOfMonth);
	
	public boolean getYTD();
	
	public boolean getOvertime();
	
}

