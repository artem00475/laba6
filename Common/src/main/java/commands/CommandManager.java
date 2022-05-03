package commands;

import Messages.Request;

public interface CommandManager {
    void execute(Request request,boolean argument);
}
