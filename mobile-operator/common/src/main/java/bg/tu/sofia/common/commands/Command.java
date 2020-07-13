package bg.tu.sofia.common.commands;

public interface Command {
	
	public Command execute(Command parent);
}