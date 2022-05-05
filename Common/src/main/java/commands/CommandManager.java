package commands;

import Messages.Request;

public interface CommandManager {
    Object execute(Request request, boolean argument);
}
