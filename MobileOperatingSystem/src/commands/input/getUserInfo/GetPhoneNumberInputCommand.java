package commands.input.getUserInfo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.Connection;

import client.User;
import commands.Command;
import commands.action.getUserInfo.GetUserByPhoneNumberActionCommand;

public class GetPhoneNumberInputCommand implements Command {
	private Connection connection;
	private PrintStream printOut;
	private BufferedReader buffReader;
	private Command nextCommand;
	
	public GetPhoneNumberInputCommand(Connection connection, PrintStream printOut, BufferedReader buffReader, Command nextCommand) {
		this.connection = connection;
		this.printOut = printOut;
		this.buffReader = buffReader;
		this.nextCommand = nextCommand;
	}
	
	@Override
	public Command execute(Command parent) {
		try {
			printOut.println("Please enter phone number");
			printOut.println("Your input please: ");
			printOut.flush();
			String phoneNumber = buffReader.readLine();
			
			User user = new User(phoneNumber);
			return new GetUserByPhoneNumberActionCommand(connection, printOut, user, nextCommand);
		}catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}