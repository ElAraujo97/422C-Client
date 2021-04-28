package client;

public class User {
	String username;
	String password;
	
	
	User(){
		this.username = "guest";
		this.password = "password";
	}
	
	User(String name, String pass){
		this.username = name;
		this.password = pass;
	}
	
	public void setName(String name) {
		this.username = name;
	}
		
	public void setPassword(String password) {
		this.password = password;
	}
		
	public String getName() {
		return this.username;
	}
	
	public String getPassword() {
		return this.password;
	}
}
