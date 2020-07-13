package bg.tu.sofia.common.commands.input;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.Connection;

import bg.tu.sofia.common.User;
import bg.tu.sofia.common.commands.Command;
import bg.tu.sofia.common.commands.action.AddNewClientActionCommand;

public class AddNewClientInputCommand implements Command {
	private Connection connection;
	private PrintStream printOut;
	private BufferedReader buffReader;
	private Command nextCommand;

	public AddNewClientInputCommand(Connection connection, PrintStream printOut, BufferedReader buffReader, Command nextCommand) {
		this.connection = connection;
		this.printOut = printOut;
		this.buffReader = buffReader;
		this.nextCommand = nextCommand;
	}
	
	@Override
	public Command execute(Command parent) {
		try {
			printOut.println("Enter client first name");
			printOut.println("Your input please: ");
			printOut.flush();
			String firstName = buffReader.readLine();
			
			printOut.println("Enter client last name");
			printOut.println("Your input please: ");
			printOut.flush();
			String lastName = buffReader.readLine();
			
			printOut.println("Enter client phone number");
			printOut.println("Your input please: ");
			printOut.flush();
			String phone = buffReader.readLine();
			
			printOut.println("Enter client password");
			printOut.println("Your input please: ");
			printOut.flush();
			String password = buffReader.readLine();
			
			User user = new User(phone, password, firstName, lastName);
			return new AddNewClientActionCommand(connection, printOut, buffReader, user, nextCommand);
		}catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}