package client;

public class User {
	private String userName;
	private String password;
	private String phone;
	
	public User(String user, String password, String phone) {
		this.userName = user;
		this.password = password;
		this.phone = phone;
	}
	
	public User(String user, String password) {
		this(user, password, "NULL");
	}

	public String getUserName() {
		return userName;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String getPhone() {
		return phone;
	}
}