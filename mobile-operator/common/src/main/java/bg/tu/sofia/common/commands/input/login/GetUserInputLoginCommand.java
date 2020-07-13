package bg.tu.sofia.common.commands.input.login;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.Connection;

import bg.tu.sofia.common.User;
import bg.tu.sofia.common.commands.Command;
import bg.tu.sofia.common.commands.action.LoginUserActionCommand;

public class GetUserInputLoginCommand implements Command {
	private Connection connection;
	private PrintStream printOut;
	private BufferedReader buffReader;

	public GetUserInputLoginCommand(Connection connection, PrintStream printOut, BufferedReader buffReader) {
		this.connection = connection;
		this.printOut = printOut;
		this.buffReader = buffReader;
	}

	@Override
	public Command execute(Command parent) {
		try {
			printOut.println("Please enter your phone number");
			printOut.println("Your input please: ");
			printOut.flush();
			String userPhone = buffReader.readLine();

			printOut.println("Please enter your password");
			printOut.println("Your input please: ");
			printOut.flush();
			String password = buffReader.readLine();

			User user = new User(userPhone, password);
			return new LoginUserActionCommand(connection, printOut, buffReader, user);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}