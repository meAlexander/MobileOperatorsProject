package bg.tu.sofia.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;

public class UserInput {
	private static PrintStream printOut;
	private static BufferedReader buffReader;

	public UserInput(PrintStream printOut, BufferedReader buffReader) {
		UserInput.printOut = printOut;
		UserInput.buffReader = buffReader;
	}

	public static User createUser() {
		try {
		printOut.println("Please enter phone number");
		printOut.println("Your input please: ");
		printOut.flush();
		String phoneNumber = buffReader.readLine();
		
		return new User(phoneNumber);
		}catch(IOException e){
			e.printStackTrace();
		}
		return null;
	}
}