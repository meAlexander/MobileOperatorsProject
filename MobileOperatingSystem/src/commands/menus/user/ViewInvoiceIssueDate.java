package commands.menus.user;

import java.io.PrintStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import client.User;
import commands.Command;

public class ViewInvoiceIssueDate implements Command {
	private Connection connection;
	private PrintStream printOut;
	private User user;
	private Command nextCommand;

	public ViewInvoiceIssueDate(Connection connection, PrintStream printOut, User user, Command nextCommand) {
		this.connection = connection;
		this.printOut = printOut;
		this.user = user;
		this.nextCommand = nextCommand;
	}
	@Override
	public Command execute(Command parent) {
		try {
			printOut.println(getInvoiceIssueDate() + "\n---------");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nextCommand;
	}
	
	private String getInvoiceIssueDate() throws SQLException {
		String invoiceIssueDate = null;
		ResultSet resultSet = connection.prepareStatement(
						String.format(
								"SELECT first_name, last_name, phone, payment_date " + 
								"FROM clients_contracts cc " + 
								"JOIN clients c ON " + 
								"c.id = cc.client_id " + 
								"WHERE c.phone = '%s'", user.getPhone()))
								.executeQuery();
		
		if (resultSet.next()) {
			invoiceIssueDate = String.format("Name: %s %s, Phone: %s, Payment Date: %d",
					resultSet.getString("first_name"),
					resultSet.getString("last_name"),
					resultSet.getString("phone"),
					resultSet.getInt("payment_date"));
		}
		return invoiceIssueDate;
	}
}