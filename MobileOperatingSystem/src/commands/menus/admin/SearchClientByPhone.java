package commands.menus.admin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.Connection;

import commands.Command;
import commands.input.getUserInfo.GetPhoneNumberInputCommand;
import exceptions.InputOptionException;

public class SearchClientByPhone implements Command {
	private Connection connection;
	private PrintStream printOut;
	private BufferedReader buffReader;
	private Command nextCommand;

	public SearchClientByPhone(Connection connection, PrintStream printOut, BufferedReader buffReader, Command nextCommand) {
		this.connection = connection;
		this.printOut = printOut;
		this.buffReader = buffReader;
		this.nextCommand = nextCommand;
	}
	
	@Override
	public Command execute(Command parent) {
		try {
			printOut.println("Search client by: 1.Phone number");
			printOut.println("Your input please: ");
			printOut.flush();

			String searchClientAnswer = buffReader.readLine();
			return getNextCommand(searchClientAnswer, parent);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InputOptionException e) {
			printOut.println(e.getMessage());
		}
		return nextCommand;
	}

	private Command getNextCommand(String searchClientAnswer, Command nextCommand) throws InputOptionException {
		switch (searchClientAnswer) {
		case "Phone number":
		case "1":
			return new GetPhoneNumberInputCommand(connection, printOut, buffReader, nextCommand);
		default:
			throw new InputOptionException();
		}
	}
}