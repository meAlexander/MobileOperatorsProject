package commands.input.getClientInformation;

import java.io.BufferedReader;
import java.io.PrintStream;
import java.sql.Connection;

import client.User;
import client.UserInput;
import commands.Command;
import commands.action.getClientInformation.GetUserByPhoneNumberActionCommand;

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
		new UserInput(printOut, buffReader);
		User user = UserInput.createUser();
		
		return new GetUserByPhoneNumberActionCommand(connection, printOut, user, nextCommand);
	}
}