package bg.tu.sofia.common.exceptions;

@SuppressWarnings("serial")
public class AddClientException extends Exception {
	
	@Override
	public String getMessage() {
		return "Add client failed!";
	}
}