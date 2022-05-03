package commands;

public interface CommandManager {
    void execute(Command command, boolean hasArgument);
}
