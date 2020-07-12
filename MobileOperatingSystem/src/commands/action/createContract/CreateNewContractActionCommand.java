package commands.action.createContract;

import java.io.PrintStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import commands.Command;
import exceptions.AddContractException;

public class CreateNewContractActionCommand implements Command {
	private Connection connection;
	private PrintStream printOut;
	private int contractID;
	private int clientID;
	private java.sql.Date signingDate;
	private java.sql.Date expiryDate;
	private int paymentDate;
	private Command nextCommand;

	public CreateNewContractActionCommand(Connection connection, PrintStream printOut, int contractID, int clientID,
			java.sql.Date signingDate, java.sql.Date expiryDate, int paymentDate, Command nextCommand) {
		this.connection = connection;
		this.printOut = printOut;
		this.contractID = contractID;
		this.clientID = clientID;
		this.signingDate = signingDate;
		this.expiryDate = expiryDate;
		this.paymentDate = paymentDate;
		this.nextCommand = nextCommand;
	}

	@Override
	public Command execute(Command parent) {
		try {
			chooseContract();
			printOut.println("The contract was created successfully!");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (AddContractException e) {
			printOut.println(e.getMessage());
		}
		return nextCommand;
	}

	private void createContract(double minutes, double megabytes, int sms, double bill)
			throws SQLException, AddContractException {
		PreparedStatement ps = connection.prepareStatement(
				"INSERT INTO clients_contracts (contract_id, client_id, signing_date, expiry_date, payment_date, minutes, megabytes, sms, bill) " +
			    "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)");
		ps.setInt(1, contractID);
		ps.setInt(2, clientID);
		ps.setDate(3, signingDate);
		ps.setDate(4, expiryDate);
		ps.setInt(5, paymentDate);
		ps.setDouble(6, minutes);
		ps.setDouble(7, megabytes);
		ps.setInt(8, sms);
		ps.setDouble(9, bill);

		if (ps.execute()) {
			throw new AddContractException();
		}
	}

	private void chooseContract() throws SQLException, AddContractException {
		ResultSet resultSet = connection
				.prepareStatement(String.format(
						"SELECT c.id, c.minutes, c.megabytes, c.sms, c.bill FROM contracts c WHERE c.id = %d", 
						contractID))
				.executeQuery();

		if (resultSet.next()) {

			double minutes = resultSet.getDouble("c.minutes");
			double megabytes = resultSet.getDouble("c.megabytes");
			int sms = resultSet.getInt("c.sms");
			double bill = resultSet.getDouble("c.bill");

			createContract(minutes, megabytes, sms, bill);
		}
	}
}