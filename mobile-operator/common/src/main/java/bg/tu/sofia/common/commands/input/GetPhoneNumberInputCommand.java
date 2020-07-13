package bg.tu.sofia.common.commands.input;

import java.io.BufferedReader;
import java.io.PrintStream;
import java.sql.Connection;

import bg.tu.sofia.common.User;
import bg.tu.sofia.common.UserInput;
import bg.tu.sofia.common.commands.Command;
import bg.tu.sofia.common.commands.action.GetUserByPhoneNumberActionCommand;

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