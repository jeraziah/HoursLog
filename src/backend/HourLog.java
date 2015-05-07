package backend;

import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class HourLog {
	public static DatabaseSupport db;

	public static void main(String args[]){	
		db = new DatabaseSupport();
		Calendar cal = Calendar.getInstance();
		Date date = new Date();
		cal.setTime(date);
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Please create employee id number for default manager. Default is 0:\n");
		int id = scanner.nextInt();
		User manager = new Manager(id);
		db.putUser(manager);
		User currentUser = manager;
		
		while (true) {

			System.out.println("Please enter a command or type 'h' for help:\n");

			String command = scanner.next();

			if (command.equals("h")) {	
				printHelpMenu();
			} else if(command.equals("setHours")) {
				System.out.println("Enter the day of the month you wish to record hours for:");
				int dayOfMonth = scanner.nextInt();
				System.out.println("Please enter number of hours worked:\n");
				User temp = db.pullUser(currentUser.getID());
				temp.setHours(Calendar.getInstance().get(Calendar.MONTH) + 1, dayOfMonth, scanner.nextInt());
				db.putUser(temp);
			}
			else if(command.equals("getDailyHours"))
			{
				User u = db.pullUser(currentUser.getID());
				u.getDailyHours(Calendar.getInstance().get(Calendar.MONTH) + 1, Calendar.DAY_OF_MONTH + 1);
				db.putUser(u);
			}
			else if(command.equals("getWeeklyHours")) {
				User u = db.pullUser(currentUser.getID());
				u.getWeeklyHours(Calendar.getInstance().get(Calendar.MONTH) + 1, Calendar.getInstance().getFirstDayOfWeek() + 2);
			}
			else if(command.equals("getYTD")) {
				User u = db.pullUser(currentUser.getID());
				u.getYTD();
			}
			else if(command.equals("getOvertime"))
			{
				db.pullUser(currentUser.getID()).getOvertime();
			}
			else if(command.equals("useSick"))
			{
				System.out.println("Please enter the day of the month that you wish to use your sick day on:");
				User u = db.pullUser(currentUser.getID());
				if(u instanceof Employee) {
					((Employee)u).useSick(Calendar.MONTH + 1, scanner.nextInt());
				}
				else {
					((Manager)u).useSick(Calendar.MONTH + 1, scanner.nextInt());
				}
				db.putUser(u);
			}else if(command.equals("useVacation"))
			{
				System.out.println("Please enter the day of the month that you wish to use your vacation day on:");
				User u = db.pullUser(currentUser.getID());
				if(u instanceof Employee) {
					((Employee)u).useVacation(Calendar.MONTH + 1, scanner.nextInt());
				}
				else {
					((Manager)u).useVacation(Calendar.MONTH + 1, scanner.nextInt());
				}
			}else if(command.equals("viewSick") && currentUser instanceof Employee)
			{
				User u = db.pullUser(currentUser.getID());
				if(u instanceof Employee) {
					((Employee)u).viewSick();
				}
				else {
					((Manager)u).viewSick();
				}
			}else if(command.equals("viewVacation") && currentUser instanceof Employee)
			{
				User u = db.pullUser(currentUser.getID());
				if(u instanceof Employee) {
					((Employee)u).viewVacation();
				}
				else {
					((Manager)u).viewVacation();
				}
			}
			else if(command.equals("viewMonthlyPay")) {
				db.pullUser(currentUser.getID()).viewMonthlyPay(Calendar.getInstance().get(Calendar.MONTH) + 1);
			}
			else if(command.equals("logoff")) {
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
				
					
				}else if (command.equals("viewEmployeeYTD")) {
					System.out.println("please enter employee id to get YTD of:\n");
					int tempId = scanner.nextInt();
					User u = db.pullUser(tempId);
					u.getYTD();
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
				}else if(command.equals("viewHours")) {
					System.out.println("Please enter employee ID number:\n");
					User u = db.pullUser(scanner.nextInt());
					System.out.println("Enter the day of the month to view hours for:\n");
					u.getDailyHours(Calendar.getInstance().get(Calendar.MONTH) + 1, scanner.nextInt());
					System.out.print("\n");
					}
				else if(command.equals("approveHours"))
				{
					//employee.approve(date)?
					System.out.println("please enter employee ID to approve:\n");
					User u = db.pullUser(scanner.nextInt());
					((Employee)u).approveHours();
					db.putUser(u);
					
				}else if(command.equals("approveOvertime"))
				{
					System.out.println("please enter employe ID to approve overtime for:\n");
					User u = db.pullUser(scanner.nextInt());
					((Employee)u).approveOvertime();
					db.putUser(u);
				}else if(command.equals("setPayScale"))
				{
					System.out.println("please enter employee ID to set pay for:\n");
					User u = db.pullUser(scanner.nextInt());
					System.out.println("please enter pay rate:\n");
					((Employee)u).setPayscale(scanner.nextDouble());
					db.putUser(u);
				}else if(command.equals("viewSick"))
				{
					System.out.println("please enter employee ID:\n");
					((Employee)db.pullUser(scanner.nextInt())).viewSick();
					
				}else if(command.equals("viewVacation"))
				{
					System.out.println("please enter employee ID:\n");
					((Employee)db.pullUser(scanner.nextInt())).viewVacation();
					
				}else if(command.equals("viewTotalHours"))
				{
					int totalHours = 0;
					
					for(int i = 0; i < db.users.size(); i++) {
						totalHours += db.users.get(i).getTotalHours();
					}
					System.out.println("Total hours over all employees is: " + totalHours);
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
					System.out.println("Total Overtime: " + overtime + " hours");
				}else if(command.equals("viewOvertimePaidEmployee"))
				{
					System.out.println("please enter employee ID:\n");
					User u = db.pullUser(scanner.nextInt());
					
					if(u instanceof Employee) {
						System.out.println("Employee Overtime: " + ((Employee)u).overtime + " hours"); ;
					}
					else if(u instanceof Manager) {
						System.out.println("Employee Overtime: " + ((Manager)u).overtime + " hours");
					}
					
				}else if(command.equals("getTaxRate"))
				{
					System.out.println("please enter employee ID:\n");
					User u = db.pullUser(scanner.nextInt());
					if(u instanceof Employee) {
						System.out.println("Employee tax rate is: " + ((((Employee)u).taxRate) * 100) + "%");
					}
					if(u instanceof Manager) {
						System.out.println("Employee tax rate is: " + ((((Manager)u).taxRate) * 100) + "%");
					}
					  }
					 
				
				else if(command.equals("setTaxRate")) {
					System.out.println("Please enter employee ID:");
					User u = db.pullUser(scanner.nextInt());
					System.out.println("Enter tax rate as a percentage (e.g. 13.4)");
					if(u instanceof Employee) {
						((Employee)u).taxRate = scanner.nextDouble();
					}
					if(u instanceof Manager) {
						((Manager)u).taxRate = scanner.nextDouble();
					}
				}
				
				else if(command.equals("viewEmployeeYTD")) {
					System.out.println("Please enter employee ID:");
					db.pullUser(scanner.nextInt()).getYTD();
				}
				
				else if (command.equals("shutdown")) {
					System.out.println("Please type 'y' to confirm. This will reset EVERYTHING!");
					if(scanner.next().equals("y")){
						scanner.close();
						System.out.println("System exiting..........\n\n\n");
						return;
					}
				}
			}
			else{ //user isnt manager
				System.out.println("You do not have credentials for this command");
			}//if statements
		}//while loop
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
	
	public boolean setTaxRate(int id, int taxRate) {
		User user = db.pullUser(id);

		if (user == null) {
			return false;
		}

		if (user instanceof Employee) {
			((Employee) user).taxRate = (taxRate / 100);
			return true;
		}
		if (user instanceof Manager) {
			((Manager) user).taxRate = (taxRate / 100);
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
						+ "\ngetYTD - Get year to date earnings."
						+ "\nsetHours - Sets hours worked for the current day."
						+ "\ngetDailyHours - Get the hours worked the past 7 days."
						+ "\ngetWeeklyHours - Get the hours worked displayed by week."
						+ "\ngetOvertime - Display overtime logged this year."
						+ "\nuseSick - Use sick time."
						+ "\nuseVacation - Use vacation time."
						+ "\nviewSick - View sick time used."
						+ "\nviewVacation - View vacation time used."
						+ "\nviewMonthlyPay - View current montly paycheck info."
						+ "\n\n----------Manager Commands------------"
						+ "\n*addEmployee - Add a new employee."
						+ "\n*removeEmployee - Remove an employee. Caution - do not remove yourself!"
					    + "\n*viewHours - view hours for any employee given id"
						+ "\n*approveHours - approve the input hours for an employee"
						+ "\n*approveOvertime - approve Overtime hours"
						+ "\n*setPayScale - Set the pay rate for an employee."
						+ "\n*viewSick - View sick time used for an employee."
						+ "\n*viewVacation - View vacation time used for an employee."
						+ "\n*viewTotalHours view hour totals for all employees"
						+ "\n*viewOvertimePaidTotal - view all payments for overtime"
						+ "\n*viewOvertimePaidEmployee"
						+ "\n*getTaxRate - get the tax percentages for an employee"
						+ "\n*setTaxRate - Set the tax percentages for an employee."
						+ "viewEmployeeYTD - view the year to date earnings of an employee"
						+ "\n-----------Exit Commands-----------------"
						+ "\nshutdown - Exit the application (This will erase all data)."
						+ "\nlogoff - Log the current user out of the system."
						+ "\n");
	}
}
