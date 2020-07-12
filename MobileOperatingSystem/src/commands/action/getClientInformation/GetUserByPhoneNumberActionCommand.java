package commands.action.getClientInformation;

import java.io.PrintStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import client.User;
import commands.Command;

public class GetUserByPhoneNumberActionCommand implements Command {
	private Connection connection;
	private PrintStream printOut;
	private User user;
	private Command nextCommand;
	
	public GetUserByPhoneNumberActionCommand(Connection connection, PrintStream printOut, User user, Command nextCommand) {
		this.connection = connection;
		this.printOut = printOut;
		this.user = user;
		this.nextCommand = nextCommand;
	}
	
	@Override
	public Command execute(Command parent) {
		try {
			printOut.println(getUserWithPhoneNumber() + "\n----------");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nextCommand;
	}
	
	private String getUserWithPhoneNumber() throws SQLException {
		String clientInfo = null;
		ResultSet resultSet = connection.prepareStatement(
				String.format(
				"SELECT first_name, last_name, phone, signing_date, expiry_date, payment_date, cc.minutes, cc.megabytes, cc.sms, cc.bill " + 
				"FROM clients_contracts cc " + 
				"JOIN clients cl ON " + 
				"cc.client_id = cl.id " + 
				"JOIN contracts co ON " + 
				"cc.contract_id = co.id " + 
				"WHERE cl.phone = '%s'", user.getPhone()))
				.executeQuery();
		
		if (resultSet.next()) {
			clientInfo = String.format("Name: %s %s, Phone: %s, Signing date: %s, Expiry date: %s, Payment date: %d,\nMinutes: %.2f, Megabytes: %.2f, SMS: %d, Bill: %.2f",
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
		}
		return clientInfo;
	}
}