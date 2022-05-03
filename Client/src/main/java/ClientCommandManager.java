import commands.Command;
import commands.CommandManager;
import person.Location;
import person.Person;

import java.io.IOException;

public class ClientCommandManager implements CommandManager {
    ConsoleManager consoleManager = new ConsoleManager();
    public void execute(Command command, boolean hasArgument){
        if (!hasArgument){
            Client.sendManager.send(new Request(command));
            try {
                System.out.println(Client.recieveManager.recieve().getString());
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        }
        else {
            if (command.getName().equals("add")||command.getName().equals("add_if_max")||command.getName().equals("remove_greater")){
                Person person = consoleManager.getPersonFromConsole(Client.scanner);
                Client.sendManager.send(new Request(command,person));
            }
            else if (command.getName().equals("remove_by_id")){
                Client.sendManager.send(new Request(command,consoleManager.getID(Client.scanner)));
            }
            else if (command.getName().equals("update")){
                Person person = consoleManager.getPersonFromConsole(Client.scanner);
                Client.sendManager.send(new Request(command,consoleManager.getID(Client.scanner),person));
            }
            else if (command.getName().equals("count_greater_than_location")){
                Location location = consoleManager.getLocationFromConsole(Client.scanner);
                Client.sendManager.send(new Request(command,location));
            }
            else if (command.getName().equals("filter_less_than_eye_color")){
                Client.sendManager.send(new Request(command,consoleManager.getEyeColor(Client.scanner)));
            }

        }

    }
}
