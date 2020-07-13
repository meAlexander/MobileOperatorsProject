package bg.tu.sofia.common.commands.input;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import bg.tu.sofia.common.User;
import bg.tu.sofia.common.commands.Command;
import bg.tu.sofia.common.commands.action.CreateNewContractActionCommand;
import bg.tu.sofia.common.commands.menus.user.ViewAvailableServices;

public class CreateNewContractInputCommand implements Command {
	private Connection connection;
	private PrintStream printOut;
	private BufferedReader buffReader;
	private Command nextCommand;

	public CreateNewContractInputCommand(Connection connection, PrintStream printOut, BufferedReader buffReader, User user, Command nextCommand) {
		this.connection = connection;
		this.printOut = printOut;
		this.buffReader = buffReader;
		this.nextCommand = nextCommand;
	}
	
	@Override
	public Command execute(Command parent) {
		try {
			viewAvailableContracts(parent);
			printOut.println("Choose contract id");
			printOut.println("Your input please: ");
			printOut.flush();
			int contractID = buffReader.read();
			
			viewClients();
			printOut.println("Choose client id");
			printOut.println("Your input please: ");
			printOut.flush();
			int clientID = buffReader.read();
			
			java.sql.Date signingDate = java.sql.Date.valueOf(new java.sql.Date(new Date().getTime()).toLocalDate());
			java.sql.Date expiryDate = java.sql.Date.valueOf(new java.sql.Date(new Date().getTime()).toLocalDate().plusYears(2));
			
			printOut.println("Enter payment date");
			printOut.println("Your input please: ");
			printOut.flush();
			int paymentDate = buffReader.read();
			
			return new CreateNewContractActionCommand(connection, printOut, contractID, clientID, signingDate, expiryDate, paymentDate, nextCommand);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private void viewClients() throws SQLException {
		ResultSet resultSet = connection.prepareStatement(
									"SELECT id, first_name, last_name, phone " + 
									"FROM clients")
									.executeQuery();
		
		while (resultSet.next()) {
			String clientInfo = String.format("ID: %d, Name: %s %s, Phone: %s: ",
					resultSet.getInt("id"),
					resultSet.getString("first_name"),
					resultSet.getString("last_name"),
					resultSet.getString("phone"));
			printOut.println(clientInfo);
		}
	}

	private void viewAvailableContracts(Command parent) throws SQLException {
		new ViewAvailableServices(connection, printOut, parent);
		ViewAvailableServices.getAvailableServices();
	}
}