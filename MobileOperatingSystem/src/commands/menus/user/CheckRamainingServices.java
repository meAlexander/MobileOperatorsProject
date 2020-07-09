package commands.menus.user;

import java.io.BufferedReader;
import java.io.PrintStream;
import java.sql.Connection;

import client.User;
import commands.Command;

public class CheckRamainingServices implements Command {
	private Connection connection;
	private PrintStream printOut;
	private BufferedReader buffReader;
	private User user;

	public CheckRamainingServices(Connection connection, PrintStream printOut, BufferedReader buffReader, User user) {
		this.connection = connection;
		this.printOut = printOut;
		this.buffReader = buffReader;
		this.user = user;
	}
	
	@Override
	public Command execute(Command parent) {
		
		return null;
	}
}