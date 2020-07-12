package commands.menus.admin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.Connection;

import commands.Command;
import commands.input.addMegabytes.GetMegabytesInputCommand;
import commands.input.addMinutes.GetMinutesInputCommand;
import commands.input.addSMS.GetSMSInputCommand;
import exceptions.InputOptionException;

public class AddServicesToClient implements Command {
	private Connection connection;
	private PrintStream printOut;
	private BufferedReader buffReader;

	public AddServicesToClient(Connection connection, PrintStream printOut, BufferedReader buffReader) {
		this.connection = connection;
		this.printOut = printOut;
		this.buffReader = buffReader;
	}

	@Override
	public Command execute(Command parent) {
		try {
			printOut.println("Add services: 1.Minutes 2.Megabytes 3.SMS 4.Admin menu");
			printOut.println("Your input please: ");
			printOut.flush();

			String searchClientAnswer = buffReader.readLine();
			return getNextCommand(searchClientAnswer, parent);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InputOptionException e) {
			printOut.println(e.getMessage());
		}
		return null;	
	}

	private Command getNextCommand(String searchClientAnswer, Command parent) throws InputOptionException {
		switch (searchClientAnswer) {
		case "Minutes":
		case "1":
			return new GetMinutesInputCommand(connection, printOut, buffReader, parent);
		case "Megabytes":
		case "2":
			return new GetMegabytesInputCommand(connection, printOut, buffReader, parent);
		case "SMS":
		case "3":
			return new GetSMSInputCommand(connection, printOut, buffReader, parent);
		case "Admin menu":
		case "4":
			return new LoggedInAdminMenuCommand(connection, printOut, buffReader);
		default:
			throw new InputOptionException();
		}
	}
}