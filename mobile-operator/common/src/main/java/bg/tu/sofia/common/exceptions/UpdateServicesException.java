package bg.tu.sofia.common.exceptions;

@SuppressWarnings("serial")
public class UpdateServicesException extends Exception {
	
	@Override
	public String getMessage() {	
		return "Updated service failed!";
	}
}