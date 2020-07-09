package commands.menus.admin;

import java.io.BufferedReader;
import java.io.PrintStream;
import java.sql.Connection;

import client.User;
import commands.Command;

public class AddNewClient implements Command {
	private Connection connection;
	private PrintStream printOut;
	private BufferedReader buffReader;

	public AddNewClient(Connection connection, PrintStream printOut, BufferedReader buffReader) {
		this.connection = connection;
		this.printOut = printOut;
		this.buffReader = buffReader;
	}
	
	@Override
	public Command execute(Command parent) {
		return null;
	}
}