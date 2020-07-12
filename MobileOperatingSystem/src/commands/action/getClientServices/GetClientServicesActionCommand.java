package commands.action.getClientServices;

import java.io.PrintStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import commands.Command;

public class GetClientServicesActionCommand implements Command{
	private static Connection connection;
	private static PrintStream printOut;
	private Command nextCommand;
	
	public GetClientServicesActionCommand(Connection connection, PrintStream printOut, Command nextCommand) {
		GetClientServicesActionCommand.connection = connection;
		GetClientServicesActionCommand.printOut = printOut;
		this.nextCommand = nextCommand;
	}
	
	@Override
	public Command execute(Command parent) {
		try {
			getClientServices();
			return nextCommand;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void getClientServices() throws SQLException {
		ResultSet resultSet = connection.prepareStatement(
				String.format(
				"SELECT first_name, last_name, phone, signing_date, expiry_date, payment_date, cc.minutes, cc.megabytes, cc.sms, cc.bill " + 
				"FROM clients_contracts cc " + 
				"JOIN clients cl ON " + 
				"cc.client_id = cl.id " + 
				"JOIN contracts co ON " + 
				"cc.contract_id = co.id "))
				.executeQuery();
		
		while (resultSet.next()) {
			String clientInfo = String.format("Name: %s %s, Phone: %s, Signing date: %s, Expiry date: %s, Payment date: %d,\nMinutes: %.2f, Megabytes: %.2f, SMS: %d, Bill: %.2f",
					resultSet.getString("first_name"),
					resultSet.getString("last_name"),
					resultSet.getString("phone"),
					resultSet.getDate("signing_date").toString(),
					resultSet.getDate("expiry_date").toString(),
					resultSet.getInt("payment_date"),
					resultSet.getDouble("cc.minutes"),
					resultSet.getDouble("cc.megabytes"),
					resultSet.getInt("cc.sms"),
					resultSet.getDouble("cc.bill"));
			printOut.println(clientInfo);
		}
	}
}