package backend;

public class Manager implements User {
	public int[] hours = new int[365];
	boolean manager = true;
	int id;
	
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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean getDailyHours(int month, int dayOfMonth) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean getWeeklyHours(int month, int dayOfMonth) {
		// TODO Auto-generated method stub
		return false;
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
