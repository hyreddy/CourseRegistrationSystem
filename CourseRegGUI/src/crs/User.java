package crs;

import java.io.Serializable;
public abstract class User implements Serializable{
	private String username;
	private String password;
	private String first_name;
	private String last_name;
	
	public User() {
		
	}
	
	public abstract String getUsername();
	
	public abstract String getPassword();
	
	public abstract String getFirstName();
	
	public abstract String getLastName();

}