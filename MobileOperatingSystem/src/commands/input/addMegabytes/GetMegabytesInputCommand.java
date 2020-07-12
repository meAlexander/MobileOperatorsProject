package commands.input.addMegabytes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.Connection;

import commands.Command;
import commands.action.addMegabytes.AddMegabytesActionCommand;

public class GetMegabytesInputCommand implements Command {
	private Connection connection;
	private PrintStream printOut;
	private BufferedReader buffReader;
	private Command nextCommand;
	
	public GetMegabytesInputCommand(Connection connection, PrintStream printOut, BufferedReader buffReader, Command nextCommand) {
		this.connection = connection;
		this.printOut = printOut;
		this.buffReader = buffReader;
		this.nextCommand = nextCommand;
	}
	
	@Override
	public Command execute(Command parent) {
		try {
			printOut.println("Please enter how megabytes you want to add:");
			printOut.println("Your input please: ");
			printOut.flush();
			String megabytes = buffReader.readLine();
			
			return new AddMegabytesActionCommand(connection, printOut, buffReader, megabytes, nextCommand);
		}catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}