package bg.tu.sofia.common.commands.action;

import java.io.BufferedReader;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import bg.tu.sofia.common.UserInput;
import bg.tu.sofia.common.commands.Command;
import bg.tu.sofia.common.exceptions.UpdateServicesException;

public class AddMegabytesActionCommand implements Command {
	private Connection connection;
	private PrintStream printOut;
	private BufferedReader buffReader;
	private String megabytes;
	private Command nextCommand;

	public AddMegabytesActionCommand(Connection connection, PrintStream printOut, BufferedReader buffReader,
			String megabytes, Command nextCommand) {
		this.connection = connection;
		this.printOut = printOut;
		this.buffReader = buffReader;
		this.megabytes = megabytes;
		this.nextCommand = nextCommand;
	}

	@Override
	public Command execute(Command parent) {
		try {
			showClientServices();
			String phone = getPhone();
			updateClientMegabytes(phone);
			
			printOut.println("Megabytes are updated successfully!");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (UpdateServicesException e) {
			printOut.println(e.getMessage());
		}
		return nextCommand;
	}

	public void updateClientMegabytes(String phone) throws SQLException, UpdateServicesException  {
		PreparedStatement ps = connection.prepareStatement(
											"UPDATE clients_contracts cc " +
											"JOIN clients c ON " + 
											"c.id = cc.client_id " +
											"SET cc.megabytes = cc.megabytes + ? WHERE phone = ?");
		ps.setString(1, megabytes);
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