package commands.menus.user;

import java.io.PrintStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import commands.Command;

public class ViewAvailableServices implements Command {
	private Connection connection;
	private PrintStream printOut;
	private Command nextCommand;

	public ViewAvailableServices(Connection connection, PrintStream printOut, Command nextCommand) {
		this.connection = connection;
		this.printOut = printOut;
		this.nextCommand = nextCommand;
	}

	@Override
	public Command execute(Command parent) {
		try {
			getAvailableServices();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nextCommand;
	}
	
	private void getAvailableServices() throws SQLException {
		ResultSet resultSet = connection.prepareStatement(
				"SELECT operator_name, minutes, megabytes, sms, bill " +
				"FROM contracts c " +
				"JOIN operators o ON " +
				"c.operator_id = o.id" )
				.executeQuery();
		
		while (resultSet.next()) {
			String availableService = String.format("Operator: %s, Minutes: %.2f, Megabytes: %.2f, SMS: %d, Bill: %.2f",
					resultSet.getString("operator_name"),
					resultSet.getDouble("minutes"),
					resultSet.getDouble("megabytes"),
					resultSet.getInt("sms"),
					resultSet.getDouble("bill"));
			printOut.println(availableService);
		}
	}
}