package dao;

import javax.jdo.annotations.PersistenceCapable;

@PersistenceCapable
public class Passenger {
	public String Username;
	public String Password;
	public String Email;
	
	public Passenger(String username) {
		super();
		Username = username;
	}
	public String getUsername() {
		return Username;
	}
	public void setUsername(String username) {
		Username = username;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}


}
