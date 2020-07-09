package commands.menus.user;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.Connection;

import client.User;
import commands.Command;
import commands.menus.MainMenuCommand;
import exceptions.InputOptionException;

public class LoggedInUserMenuCommand implements Command {
	private Connection connection;
	private PrintStream printOut;
	private BufferedReader buffReader;
	private User user;

	public LoggedInUserMenuCommand(Connection connection, PrintStream printOut, BufferedReader buffReader, User user) {
		this.connection = connection;
		this.printOut = printOut;
		this.buffReader = buffReader;
		this.user = user;
	}

	@Override
	public Command execute(Command parent) {
		try {
			printOut.println("Login user menu:\n"
						+ "1.View available active services\n" 
						+ "2.Check remaining minutes megabytes sms\n"
						+ "3.View monthly invoice issue date\n"
						+ "4.Main menu");
			
			printOut.println("Your input please: ");
			printOut.flush();

			String loginUserAnswer = buffReader.readLine();
			return getNextCommand(loginUserAnswer);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InputOptionException e) {
			printOut.println(e.getMessage());
			printOut.flush();
			return new LoggedInUserMenuCommand(connection, printOut, buffReader, user);
		}
		return null;
	}

	private Command getNextCommand(String loginUserAnswer) throws InputOptionException {
		switch (loginUserAnswer) {
		case "View available active services":
		case "1":
			return new ViewAvailableActiveServices(connection, printOut, buffReader, user);
		case "Check remaining minutes, megabytes, sms":
		case "2":
			return new CheckRamainingServices(connection, printOut, buffReader, user);
		case "View monthly invoice issue date":
		case "3":
			return new ViewInvoiceIssueDate(connection, printOut, buffReader, user);
		case "Main menu":
		case "4":
			return new MainMenuCommand(connection, printOut, buffReader);
		default:
			throw new InputOptionException();
		}
	}
}