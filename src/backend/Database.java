package backend;

import java.util.ArrayList;

public class Database {
	ArrayList<User> users = new ArrayList<>();
	
	public boolean putUser(User user) {
		
		// Places a user into the database, returns true if added, false otherwise.
		if(user != null) {
			users.add(user);
			return true;
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
	
}
