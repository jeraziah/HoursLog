package backend;

import java.util.ArrayList;

public class DatabaseSupport {
	ArrayList<User> users = new ArrayList<>();
	
	public boolean putUser(User user) {
		if(users.size() == 0) {
			users.add(user);
			return true;
		}
		// Places a user into the database, returns true if added, false otherwise.
		if(user != null) {
			for(int i = 0; i < users.size(); i++) {
				if(users.get(i).equals(user)) {
					users.set(i, user);
					return true;
				}
			}
			users.add(user);
		}
		return false;
	}
	
	public User pullUser(int id) {
		
		// Retrieves a user from the database with a specified ID, returns null if no user exists.
		for(int i = 0; i < users.size(); i++) {
			if(users.get(i).getID() == id) {
				return users.get(i);
			}
		}
		return null;
	}
	
	public boolean removeUser(int id) {
		for(int i = 0; i < users.size(); i++) {
			if(users.get(i).getID() == id) {
				users.remove(i);
				System.out.println("Employee removed.");
				return true;
			}
		}
		System.out.println("Employee doesn't exist.");
		return false;
	}
	
}
