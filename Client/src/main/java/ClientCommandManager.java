import commands.CommandManager;
import person.Location;
import person.Person;

public class ClientCommandManager implements CommandManager {
    ConsoleManager consoleManager = new ConsoleManager();
    public void execute(String name,boolean hasArgument){
        if (!hasArgument){
            Client.sendManager.send(new Request(name));
        }
        else {
            if (name.equals("add")||name.equals("add_if_max")||name.equals("remove_greater")){
                Person person = consoleManager.getPersonFromConsole(Client.scanner);
                Client.sendManager.send(new Request(name,person));
            }
            else if (name.equals("remove_by_id")){
                Client.sendManager.send(new Request(name,consoleManager.getID(Client.scanner)));
            }
            else if (name.equals("update")){
                Person person = consoleManager.getPersonFromConsole(Client.scanner);
                Client.sendManager.send(new Request(name,consoleManager.getID(Client.scanner),person));
            }
            else if (name.equals("count_greater_than_location")){
                Location location = consoleManager.getLocationFromConsole(Client.scanner);
                Client.sendManager.send(new Request(name,location));
            }
            else if (name.equals("filter_less_than_eye_color")){
                Client.sendManager.send(new Request(name,consoleManager.getEyeColor(Client.scanner)));
            }

        }

    }
}
