package backend;

import java.util.Calendar;
import java.util.Date;
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
			} else if(command.equals("setHours")) {
				System.out.println("Please enter number of hours worked:\n");
				User temp = db.pullUser(currentUser.getID());
				temp.setHours(Calendar.MONTH, Calendar.DAY_OF_MONTH, scanner.nextInt());
				db.putUser(temp);
			}
			else if(command.equals("getDailyHours"))
			{
				User u = db.pullUser(currentUser.getID());
				u.getDailyHours(Calendar.MONTH, Calendar.DAY_OF_MONTH);
				db.putUser(u);
			}else if(command.equals("getOvertime"))
			{
				db.pullUser(currentUser.getID()).getOvertime();
			}else if(command.equals("useSick"))
			{
				System.out.println("Please enter the day of the month that you wish to use your sick day on:");
				User u = db.pullUser(currentUser.getID());
				if(u instanceof Employee) {
					((Employee)u).useSick(Calendar.MONTH, scanner.nextInt());
				}
				else {
					System.out.println("Only employees can use this function.");
				}
				db.putUser(u);
			}else if(command.equals("useVacation"))
			{
				System.out.println("Please enter the day of the month that you wish to use your vacation day on:");
				User u = db.pullUser(currentUser.getID());
				if(u instanceof Employee) {
					((Employee)u).useVacation(Calendar.MONTH, scanner.nextInt());
				}
				else {
					System.out.println("Only employees can use this function.");
				}
			}else if(command.equals("viewSick"))
			{
				User u = db.pullUser(currentUser.getID());
				if(u instanceof Employee) {
					((Employee)u).viewSick();
				}
				else {
					System.out.println("Only employees can use this function.");
				}
			}else if(command.equals("viewVacation"))
			{
				User u = db.pullUser(currentUser.getID());
				if(u instanceof Employee) {
					((Employee)u).viewVacation();
				}
				else {
					System.out.println("Only employees can use this function.");
				}
			}else if(command.equals("logoff")) {
				currentUser = null;
				while(currentUser == null) {
					System.out.println("Please enter a user ID to login:");
					int loginid = scanner.nextInt();
					User u = db.pullUser(loginid);
					if(u != null) {
						currentUser = u;
						break;
					}
				}
			}
			else if(currentUser.isManager()){ //admin commands
				if(command.equals("")){
				
					
				}else if (command.equals("getYTD")) {
					System.out.println("please enter employee id to get YTD of:\n");
					int tempId = scanner.nextInt();
					User tempUser = new Employee(tempId);
					tempUser.getYTD();
				} 	
				else if(command.equals("addEmployee"))
				{
					System.out.println("please enter employee id number\n");
					int newId = scanner.nextInt();
					db.putUser(new Employee(newId));
				}else if(command.equals("removeEmployee"))
				{
					System.out.println("please enter employee id number\n");
					int newId = scanner.nextInt();
					db.removeUser(newId);
				}else if(command.equals("viewHours"))
				{
					System.out.println("Please enter employee ID number:\n");
				}else if(command.equals("approveHours"))
				{
					//employee.approve(date)?
					System.out.println("please enter employee ID to approve:\n");
					User u = db.pullUser(scanner.nextInt());
					((Employee)u).approveHours();
					db.putUser(u);
					
				}else if(command.equals("awardOvertime"))
				{
					System.out.println("please enter employe ID to award overtime to:\n");
					User u = db.pullUser(scanner.nextInt());
					System.out.println("please enter number of hours to award");
					//((Employee)u).;   need an overtime approved?
					db.putUser(u);
				}else if(command.equals("setPayScale"))
				{
					System.out.println("please enter employee ID to set pay for:\n");
					User u = db.pullUser(scanner.nextInt());
					System.out.println("please enter pay rate:\n");
					((Employee)u).setPayscale(scanner.nextInt());
					db.putUser(u);
				}else if(command.equals("viewSick"))
				{
					System.out.println("please enter employee ID:\n");
					((Employee)db.pullUser(scanner.nextInt())).viewSick();
					
				}else if(command.equals("viewVacation"))
				{
					System.out.println("please enter employee ID:\n");
					((Employee)db.pullUser(scanner.nextInt())).viewVacation();
					
				}/*else if(command.equals("viewTotalHours"))
				{
					System.out.println("please enter employee ID:\n");
				
					db.pullUser(scanner.nextInt()).getTotalHours();
				}else if(command.equals("viewOvertimePaidTotal"))
				{
					int overtime = 0;
				
					
					for(int i = 0; i < db.users.size(); i++){
						if(db.users.get(i) instanceof Employee) {
							overtime += ((Employee)db.users.get(i)).overtime;
						}
						else if(db.users.get(i) instanceof Manager) {
							overtime += ((Manager)db.users.get(i)).overtime;
						}
					}
					System.out.println("Total Overtime: " + overtime);
				}else if(command.equals("viewOvertimePaidEmployee"))
				{
					System.out.println("please enter employee ID:\n");
					db.pullUser(scanner.nextInt());
					
				}else if(command.equals("getTaxRate"))
				{
					//System.out.println("please enter employee ID:\n");
					//((Employee)db.pullUser(scanner.nextInt())).getTaxRate();
			
				}else if(command.equals("viewEmployeeYTD"))
				{
					System.out.println("please enter employee ID:\n");
					db.pullUser(scanner.nextInt());
					
				}*/
				
				
				
				
				else if (command.equals("shutdown")) {
					System.out.println("Please type 'y' to confirm. This will reset EVERYTHING!");
					if(scanner.next() == "y"){
						break;
					}
				}else{ //user isnt manager
					System.out.println("You do not have privelages for this command");
				}
			}//if statements
		}//while loop
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
	
	public boolean viewHours(int id) {
		User user = db.pullUser(id);
		
		if(user == null) {
			System.err.println("User does not exist.");
		}
		
		if (user instanceof Employee) {
			((Employee)user).getTotalHours();
			return true;
		}
		if (user instanceof Manager) {
			((Manager)user).getTotalHours();
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
			if (((Employee) user).hoursApproved() == true) {
				return true;
			} else {
				return false;
			}
		}

		return false;
	}

	public static void printHelpMenu() {
		System.out
				.println(""
						+ "\nHelp Menu:\n"
						+ "\nh - help\n\n* indicates manager functions"
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
