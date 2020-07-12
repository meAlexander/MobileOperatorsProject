package commands.input.addSMS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.Connection;

import commands.Command;
import commands.action.addSMS.AddSMSActionCommand;

public class GetSMSInputCommand implements Command {
	private Connection connection;
	private PrintStream printOut;
	private BufferedReader buffReader;
	private Command nextCommand;
	
	public GetSMSInputCommand(Connection connection, PrintStream printOut, BufferedReader buffReader, Command nextCommand) {
		this.connection = connection;
		this.printOut = printOut;
		this.buffReader = buffReader;
		this.nextCommand = nextCommand;
	}
	
	@Override
	public Command execute(Command parent) {
		try {
			printOut.println("Please enter how sms you want to add:");
			printOut.println("Your input please: ");
			printOut.flush();
			String sms = buffReader.readLine();
			
			return new AddSMSActionCommand(connection, printOut, buffReader, sms, nextCommand);
		}catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}