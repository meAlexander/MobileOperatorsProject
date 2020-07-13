package bg.tu.sofia.common.exceptions;

@SuppressWarnings("serial")
public class InputOptionException extends Exception {
	
	@Override
	public String getMessage() {
		return "Invalid option!";
	}
}