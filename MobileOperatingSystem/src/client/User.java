package client;

public class User {
	private String userName;
	private String password;
	private String phone;
	
	public User(String phone, String password, String userName) {
		this.phone = phone;
		this.password = password;
		this.userName = userName;
	}
	
	public User(String phone, String password) {
		this(phone, password, "NULL");
	}
	
	public User(String phone) {
		this(phone, "NULL", "NULL");
	}

	public String getPhone() {
		return phone;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String getUserName() {
		return userName;
	}
}