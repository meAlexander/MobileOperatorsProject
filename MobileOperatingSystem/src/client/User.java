package client;

public class User {
	private String phone;
	private String password;
	private String firstName;
	private String lastName;
	
	public User(String phone, String password, String firstName, String lastName) {
		this.phone = phone;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public User(String phone, String password) {
		this(phone, password, "NULL", "NULL");
	}
	
	public User(String phone) {
		this(phone, "NULL", "NULL", "NULL");
	}

	public String getPhone() {
		return phone;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
}