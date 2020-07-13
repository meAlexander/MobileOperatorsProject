package bg.tu.sofia.common.commands.menus;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.Connection;

import bg.tu.sofia.common.commands.Command;
import bg.tu.sofia.common.commands.input.login.GetAdminInputLoginCommand;
import bg.tu.sofia.common.commands.input.login.GetUserInputLoginCommand;
import bg.tu.sofia.common.exceptions.InputOptionException;

public class LoginMenuCommand implements Command {
	private Connection connection;
	private PrintStream printOut;
	private BufferedReader buffReader;

	public LoginMenuCommand(Connection connection, PrintStream printOut, BufferedReader buffReader) {
		this.connection = connection;
		this.printOut = printOut;
		this.buffReader = buffReader;
	}

	@Override
	public Command execute(Command parent) {
		try {
			printOut.println("Login menu: 1.User 2.Admin 3.Main menu");
			printOut.println("Your input please: ");
			printOut.flush();
			String userLoginAnswer = buffReader.readLine();

			return getNextCommand(userLoginAnswer);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InputOptionException e) {
			printOut.println(e.getMessage());
			printOut.flush();
			return new LoginMenuCommand(connection, printOut, buffReader);
		}
		return null;
	}

	private Command getNextCommand(String userLoginAnswer) throws InputOptionException {
		switch (userLoginAnswer) {
		case "User":
		case "1":
			return new GetUserInputLoginCommand(connection, printOut, buffReader);
		case "Admin":
		case "2":
			return new GetAdminInputLoginCommand(connection, printOut, buffReader);
		case "Main menu":
		case "3":
			return new MainMenuCommand(connection, printOut, buffReader);
		default:
			throw new InputOptionException();
		}
	}
}