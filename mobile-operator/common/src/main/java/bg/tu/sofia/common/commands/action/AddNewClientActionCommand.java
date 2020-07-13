package bg.tu.sofia.common.commands.action;

import java.io.BufferedReader;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import bg.tu.sofia.common.User;
import bg.tu.sofia.common.commands.Command;
import bg.tu.sofia.common.commands.input.CreateNewContractInputCommand;
import bg.tu.sofia.common.exceptions.AddClientException;

public class AddNewClientActionCommand implements Command {
	private Connection connection;
	private PrintStream printOut;
	private BufferedReader buffReader;
	private User user;
	private Command nextCommand;

	public AddNewClientActionCommand(Connection connection, PrintStream printOut, BufferedReader buffReader, User user,
			Command nextCommand) {
		this.connection = connection;
		this.printOut = printOut;
		this.buffReader = buffReader;
		this.user = user;
		this.nextCommand = nextCommand;
	}

	@Override
	public Command execute(Command parent) {
		try {
			createClient();
			return new CreateNewContractInputCommand(connection, printOut, buffReader, user, nextCommand);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (AddClientException e) {
			printOut.println(e.getMessage());
		}
		return null;
	}

	public void createClient() throws SQLException, AddClientException {
		PreparedStatement ps = connection.prepareStatement(
				"INSERT INTO clients (first_name, last_name, phone, client_pass) " + 
				"VALUES(?, ?, ?, ?)");
		ps.setString(1, user.getFirstName());
		ps.setString(2, user.getLastName());
		ps.setString(3, user.getPhone());
		ps.setString(4, user.getPassword());

		if (ps.execute()) {
			throw new AddClientException();
		}
	}
}