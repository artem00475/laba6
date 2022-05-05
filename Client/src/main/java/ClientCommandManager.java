import Messages.Request;
import commands.Command;
import commands.CommandManager;
import java.util.Deque;

public class ClientCommandManager implements CommandManager {
    ConsoleManager consoleManager = new ConsoleManager();
    private final ScriptManager scriptManager;

    public ClientCommandManager(Deque<String> stringDeque) {
        scriptManager = new ScriptManager(stringDeque);
    }

    public Request execute(Request request, boolean ifConsole) {
        Command command = request.getCommand();
        if (ifConsole) {
            if (!command.hasArgement()) {
                return new Request(command);
            } else {
                if (command.getName().equals("add") || command.getName().equals("add_if_max") || command.getName().equals("remove_greater")) {
                    return new Request(command,consoleManager.getPersonFromConsole(Client.scanner));
                } else if (command.getName().equals("remove_by_id")) {
                    return new Request(command,consoleManager.getID(Client.scanner));
                } else if (command.getName().equals("update id")) {
                    return new Request(command,consoleManager.getID(Client.scanner),consoleManager.getPersonFromConsole(Client.scanner));
                } else if (command.getName().equals("count_greater_than_location")) {
                    return new Request(command,consoleManager.getLocationFromConsole(Client.scanner));
                } else if (command.getName().equals("filter_less_than_eye_color")) {
                    return new Request(command,consoleManager.getEyeColor(Client.scanner));
                } else if (command.getName().equals("execute_script")) {
                    scriptManager.createScriptFlesArray();
                    scriptManager.addFile(consoleManager.getFile(Client.scanner));
                }
            }
        } else {
            if (command.getName().equals("add") || command.getName().equals("add_if_max") || command.getName().equals("remove_greater")) {
                return new Request(command,scriptManager.getPersonFromScript());
            }else if (command.getName().equals("remove_by_id")) {
                return new Request(command,scriptManager.getID());
            }else if (command.getName().equals("update id")) {
                return new Request(command,scriptManager.getID(),scriptManager.getPersonFromScript());
            }else if (command.getName().equals("count_greater_than_location")) {
                return new Request(command,scriptManager.getLocationFromScript());
            }else if (command.getName().equals("filter_less_than_eye_color")) {
                return new Request(command,scriptManager.getEyeColor());
            }else if (command.getName().equals("execute_script")) {
                scriptManager.addFile(scriptManager.getFile());
            }
        }return null;
    }
}
