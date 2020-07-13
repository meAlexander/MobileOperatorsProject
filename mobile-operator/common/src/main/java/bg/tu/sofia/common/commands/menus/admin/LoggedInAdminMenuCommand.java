package bg.tu.sofia.common.commands.menus.admin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.Connection;

import bg.tu.sofia.common.commands.Command;
import bg.tu.sofia.common.commands.menus.MainMenuCommand;
import bg.tu.sofia.common.exceptions.InputOptionException;

public class LoggedInAdminMenuCommand implements Command {
	private Connection connection;
	private PrintStream printOut;
	private BufferedReader buffReader;

	public LoggedInAdminMenuCommand(Connection connection, PrintStream printOut, BufferedReader buffReader) {
		this.connection = connection;
		this.printOut = printOut;
		this.buffReader = buffReader;
	}

	@Override
	public Command execute(Command parent) {
		try {
			printOut.println("Login admin menu:\n"
					+ "1.Add services to client\n"
					+ "2.Add new client\n"
					+ "3.Search client\n"
					+ "4.View clients who have not paid yet\n"
					+ "5.Main menu");
			printOut.println("Your input please: ");
			printOut.flush();

			String adminMenuAnswer = buffReader.readLine();
			return getNextCommand(adminMenuAnswer, parent);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InputOptionException e) {
			printOut.println(e.getMessage());
			printOut.flush();
			return parent;
//			return new LoggedInAdminMenuCommand(connection, printOut, buffReader);
		}
		return null;
	}

	private Command getNextCommand(String adminMenuAnswer, Command nextCommand) throws InputOptionException {
		switch (adminMenuAnswer) {
		case "Add services to client":
		case "1":
			return new AddServicesToClient(connection, printOut, buffReader);
		case "Add new client":
		case "2":
			return new AddNewClient(connection, printOut, buffReader, nextCommand);
		case "Search client by phone number":
		case "3":
			return new SearchClientByPhone(connection, printOut, buffReader, nextCommand);
		case "View clients who have not paid yet":
		case "4":
			return new ViewDebtors(connection, printOut, buffReader);
		case "Main menu":
		case "5":
			return new MainMenuCommand(connection, printOut, buffReader);
		default:
			throw new InputOptionException();
		}
	}
}