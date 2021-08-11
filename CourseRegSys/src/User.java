/*
 * @Hrishikesh Yeluru
 * This class creates the Abstract class user which is implemented by the Admin and Student class.
 */

public abstract class User {
	private String username;
	private String password;
	private String first_name;
	private String last_name;
	
	public User() {
		
	}
	
	// Getter
	public abstract String getUsername();
	
	// Getter
	public abstract String getPassword();
	
	// Getter
	public abstract String getFirstName();
	
	// Getter
	public abstract String getLastName();

}