package backend;

public class Manager implements User {
	boolean manager = true;
	int id;
	
	public Manager(int id)
	{
		this.id = id;
	}
	
	public boolean isManager() {
		return manager;
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean setHours(int dayOfMonth, int hours) {
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

	@Override
	public boolean isManager(int id) {
		// TODO Auto-generated method stub
		return false;
	}
}
