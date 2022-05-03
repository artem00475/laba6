import Messages.Request;
import commands.Command;
import commands.CommandManager;
import person.Location;
import person.Person;

import java.io.IOException;
import java.util.Deque;

public class ClientCommandManager implements CommandManager {
    ConsoleManager consoleManager = new ConsoleManager();
    private ScriptManager scriptManager;


    public ClientCommandManager(Deque<String> stringDeque) {
        scriptManager = new ScriptManager(stringDeque);
    }

    public void execute(Request request, boolean ifConsole) {
        Command command = request.getCommand();
        if (ifConsole) {
            if (!command.hasArgement()) {
                Client.sendManager.send(new Request(command));
                recieve();
            } else {
                if (command.getName().equals("add") || command.getName().equals("add_if_max") || command.getName().equals("remove_greater")) {
                    Person person = consoleManager.getPersonFromConsole(Client.scanner);
                    Client.sendManager.send(new Request(command, person));
                    recieve();
                } else if (command.getName().equals("remove_by_id")) {
                    Client.sendManager.send(new Request(command, consoleManager.getID(Client.scanner)));
                    recieve();
                } else if (command.getName().equals("update id")) {
                    int argument = consoleManager.getID(Client.scanner);
                    Person person = consoleManager.getPersonFromConsole(Client.scanner);
                    Client.sendManager.send(new Request(command, argument, person));
                    recieve();
                } else if (command.getName().equals("count_greater_than_location")) {
                    Location location = consoleManager.getLocationFromConsole(Client.scanner);
                    Client.sendManager.send(new Request(command, location));
                    recieve();
                } else if (command.getName().equals("filter_less_than_eye_color")) {
                    Client.sendManager.send(new Request(command, consoleManager.getEyeColor(Client.scanner)));
                    recieve();
                } else if (command.getName().equals("execute_script")) {
                    scriptManager.createScriptFlesArray();
                    scriptManager.addFile(consoleManager.getFile(Client.scanner));
                }
            }
        } else {
            if (command.getName().equals("add") || command.getName().equals("add_if_max") || command.getName().equals("remove_greater")) {
                Person person = scriptManager.getPersonFromScript();
                Client.sendManager.send(new Request(command, person));
                recieve();
            }else if (command.getName().equals("remove_by_id")) {
                Client.sendManager.send(new Request(command,scriptManager.getID()));
                recieve();
            }else if (command.getName().equals("update id")) {
                int argument = scriptManager.getID();
                Person person = scriptManager.getPersonFromScript();
                Client.sendManager.send(new Request(command, argument, person));
                recieve();
            }else if (command.getName().equals("count_greater_than_location")) {
                Location location = scriptManager.getLocationFromScript();
                Client.sendManager.send(new Request(command, location));
                recieve();
            }else if (command.getName().equals("filter_less_than_eye_color")) {
                Client.sendManager.send(new Request(command, scriptManager.getEyeColor()));
                recieve();
            }else if (command.getName().equals("execute_script")) {
                scriptManager.addFile(scriptManager.getFile());
            }
        }
    }

        public void recieve() {
            try {
                System.out.println(Client.recieveManager.recieve().getString());
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
