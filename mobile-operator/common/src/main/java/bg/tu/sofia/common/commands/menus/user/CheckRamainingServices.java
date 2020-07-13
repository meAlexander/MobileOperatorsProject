package bg.tu.sofia.common.commands.menus.user;

import java.io.PrintStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import bg.tu.sofia.common.User;
import bg.tu.sofia.common.commands.Command;

public class CheckRamainingServices implements Command {
	private Connection connection;
	private PrintStream printOut;
	private User user;
	private Command nextCommand;

	public CheckRamainingServices(Connection connection, PrintStream printOut, User user, Command nextCommand) {
		this.connection = connection;
		this.printOut = printOut;
		this.user = user;
		this.nextCommand = nextCommand;
	}
	
	@Override
	public Command execute(Command parent) {
		try {
			printOut.println(getRemainingServices() + "\n---------");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nextCommand;
	}
	
	private String getRemainingServices() throws SQLException {
		String remainServices = null;
		ResultSet resultSet = connection.prepareStatement(
						String.format(
								"SELECT first_name, last_name, phone, minutes, megabytes, sms " + 
								"FROM clients_contracts cc " + 
								"JOIN clients c ON " + 
								"c.id = cc.client_id " + 
								"WHERE c.phone = '%s'", user.getPhone()))
								.executeQuery();
		
		if (resultSet.next()) {
			remainServices = String.format("Name: %s %s, Phone: %s, Minutes: %.2f, Megabytes: %.2f, SMS: %d",
					resultSet.getString("first_name"),
					resultSet.getString("last_name"),
					resultSet.getString("phone"),
					resultSet.getDouble("minutes"),
					resultSet.getDouble("megabytes"),
					resultSet.getInt("sms"));
		}
		return remainServices;
	}
}