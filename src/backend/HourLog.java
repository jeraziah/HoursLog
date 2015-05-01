package backend;

import java.util.Scanner;

public class HourLog {
	public static DatabaseSupport db;
	
	public static void main(String args[]){	
		db = new DatabaseSupport();
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Please create employeee id number for default manager. Default is 0:\n");
		int id = scanner.nextInt();
		User manager = new Manager(id);
		db.putUser(manager);
		User currentUser = manager;
		
		while (true) {

			System.out
					.println("Please enter a command or type 'h' for help:\n");

			String command = scanner.next();

			if (command.equals("h")) {	
				printHelpMenu();
			} else if (command.equals("getYTD")) {
				System.out.println("please enter employee id to get YTD of:\n");
				int tempId = scanner.nextInt();
				User tempUser = new Employee(tempId);
				tempUser.getYTD();
			} else if(command == ""){
				
			}
			else if (command == "shutdown") {
				break;
			}

		}
		scanner.close();
		return;
	}

	public User identifyUser(int id) {
		return db.pullUser(id);
	}

	public boolean addEmployee(User user, int id) {
		boolean userAccepted = false;

		if (user.isManager()) {
			userAccepted = db.putUser(user);
			if (userAccepted) {
				return true;
			}
		}

		return false;
	}

	public boolean setPayScale(int id, int hourlyRate) {
		User user = db.pullUser(id);

		if (user == null) {
			return false;
		}

		if (user instanceof Employee) {
			((Employee) user).payScale = hourlyRate;
			return true;
		}
		if (user instanceof Manager) {
			((Manager) user).payScale = hourlyRate;
			return true;
		}

		return false;
	}

	public boolean removeEmployee(int id) {
		User user = db.pullUser(id);

		if (user == null) {
			System.err.println("User does not exist.");
			return false;
		} else {
			user = null;
			db.putUser(user);
			return true;
		}
	}

	public boolean viewDailyHours(int id, int month, int dayOfMonth) {
		User user = db.pullUser(id);

		if (user == null) {
			System.err.println("User does not exist.");
		}

		if (user instanceof Employee) {
			((Employee) user).getDailyHours(month, dayOfMonth);
			return true;
		}
		if (user instanceof Manager) {
			((Manager) user).getDailyHours(month, dayOfMonth);
			return true;
		}

		return false;
	}

	public boolean viewWeeklyHours(int id, int month, int firstDayOfWeek) {
		User user = db.pullUser(id);

		if (user == null) {
			System.err.println("User does not exist.");
		}

		if (user instanceof Employee) {
			((Employee) user).getWeeklyHours(month, firstDayOfWeek);
			return true;
		}
		if (user instanceof Manager) {
			((Manager) user).getWeeklyHours(month, firstDayOfWeek);
			return true;
		}

		return false;
	}

	public boolean approveHours(int id) {
		User user = db.pullUser(id);

		if (user == null) {
			System.err.println("User does not exist.");
		}

		if (user instanceof Employee) {
			return ((Employee) user).approveHours();
		}

		return false;
	}

	public boolean viewSick(int id) {
		User user = db.pullUser(id);

		if (user == null) {
			System.err.println("User does not exist.");
		}

		if (user instanceof Employee) {
			return ((Employee) user).viewSick();
		}

		return false;
	}

	public boolean viewVacation(int id) {
		User user = db.pullUser(id);

		if (user == null) {
			System.err.println("User does not exist.");
		}

		if (user instanceof Employee) {
			return ((Employee) user).viewVacation();
		}

		return false;
	}

	public boolean checkApproved(int id) {
		User user = db.pullUser(id);

		if (user == null) {
			System.err.println("User does not exist.");
		}

		if (user instanceof Employee) {
			if (((Employee) user).approved() == true) {
				return true;
			} else {
				return false;
			}
		}

		return false;
	}
	
	public static void printHelpMenu(){
		System.out.println("" + "\nHelp Menu:\n" + "\nh - help\n\n* indicates manager functions"
					+ "\n-----------User Commands-----------------"
					+ "\ngetYTD - get year to date earnings"
					+ "\nsetHours sets hours worked for the current day"
					+ "\ngetDailyHours - get the hours worked the past 7 days"
					+ "\ngetWeeklyHours - get the hours worked displayed by week"
					+ "\ngetOvertime - display overtime logged this year"
					+ "\nuseSick - use sick time"
					+ "\nuseVacation - use vacation time"
					+ "\nviewSick - view sick time used"
					+ "\nviewVacation - view vacation time used"
					+ "\n\n----------Manager Commands------------ "
					+ "\n*addEmployee - add a new employee"
					+ "\n*removeEmployee - remove an employee. Caution - do not remove yourself!"
					+ "\n*viewHours - view hours for any employee given id"
					+ "\n*approveHours - approve the input hours for an employee"
					+ "\n*awardOvertime - approve Overtime hours"
					+ "\n*setPayScale - set the pay rate for an employee"
					+ "\n*viewSick - view sick time used for an employee"
					+ "\n*viewVacation - view vacation time used for an employee"
					+ "\n*viewTotalHours view hour totals for all employees"
					+ "\n*viewOvertimePaidTotal - view all payments for overtime"
					+ "\n*viewOvertimePaidEmployee"
					+ "getTaxRate - get the tax percentages for an employee"
					+ "viewEmployeeYTD - view the year to date earnings of an employee"
					+ "\n*shutdown - exit the application - this will erase all data"
					+ "\n");
	}
}

	