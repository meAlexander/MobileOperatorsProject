package commands.action.addSMS;

import java.io.BufferedReader;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import client.UserInput;
import commands.Command;
import commands.action.getClientServices.GetClientServicesActionCommand;
import exceptions.UpdateServicesException;

public class AddSMSActionCommand implements Command {
	private Connection connection;
	private PrintStream printOut;
	private BufferedReader buffReader;
	private String sms;
	private Command nextCommand;
	
	public AddSMSActionCommand(Connection connection, PrintStream printOut, BufferedReader buffReader, String sms, Command nextCommand) {
		this.connection = connection;
		this.printOut = printOut;
		this.buffReader = buffReader;
		this.sms = sms;
		this.nextCommand = nextCommand;
	}
	
	@Override
	public Command execute(Command parent) {
		try {
			showClientServices();
			String phone = getPhone();
			updateClientSMS(phone);
			
			printOut.println("SMS are updated successfully!");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (UpdateServicesException e) {
			printOut.println(e.getMessage());
		}
		return nextCommand;
	}
	
	public void updateClientSMS(String phone) throws SQLException, UpdateServicesException  {
		PreparedStatement ps = connection.prepareStatement(
											"UPDATE clients_contracts cc " +
											"JOIN clients c ON " + 
											"c.id = cc.client_id " +
											"SET cc.sms = cc.sms + ? WHERE phone = ?");
		ps.setString(1, sms);
		ps.setString(2, phone);

		if (ps.execute()) {
			throw new UpdateServicesException();
		}
	}
	
	private void showClientServices() throws SQLException {
		new GetClientServicesActionCommand(connection, printOut, nextCommand);
		GetClientServicesActionCommand.getClientServices();
	}
	
	private String getPhone() {
		new UserInput(printOut, buffReader);
		return UserInput.createUser().getPhone();
	}
}