package bg.tu.sofia.common.commands.menus.user;

import java.io.PrintStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import bg.tu.sofia.common.commands.Command;

public class ViewAvailableServices implements Command {
	private static Connection connection;
	private static PrintStream printOut;
	private Command nextCommand;

	public ViewAvailableServices(Connection connection, PrintStream printOut, Command nextCommand) {
		ViewAvailableServices.connection = connection;
		ViewAvailableServices.printOut = printOut;
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
	
	public static void getAvailableServices() throws SQLException {
		ResultSet resultSet = connection.prepareStatement(
				"SELECT c.id, operator_name, minutes, megabytes, sms, bill " +
				"FROM contracts c " +
				"JOIN operators o ON " +
				"c.operator_id = o.id" )
				.executeQuery();
		
		while (resultSet.next()) {
			String availableService = String.format("ID: %d, Operator: %s, Minutes: %.2f, Megabytes: %.2f, SMS: %d, Bill: %.2f",
					resultSet.getInt("id"),
					resultSet.getString("operator_name"),
					resultSet.getDouble("minutes"),
					resultSet.getDouble("megabytes"),
					resultSet.getInt("sms"),
					resultSet.getDouble("bill"));
			printOut.println(availableService);
		}
	}
}