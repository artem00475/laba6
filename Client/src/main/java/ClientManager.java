import commands.*;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class ClientManager {
    private Command[] commands = {
            new AddCommand(),
            new InfoCommand(),
            new ShowCommand(),
            new HelpCommand(),
            new UpdateCommand(),
            new RemoveByIdCommand(),
            new ClearCommand(),
            new RemoveHeadCommand(),
            new AddIfMaxCommand(),
            new RemoveGreaterCommand(),
            new CountGreaterThanLocationCommand(),
            new PrintFieldAscendingLocationCommand(),
            new FilterLessThanEyeColorCommand(),
            new ExcecuteCommand()
    };
    private String com;
    private boolean ifConsole;
    private ClientCommandManager clientCommandManager;
    public ClientManager(){
        clientCommandManager = new ClientCommandManager();
    }

    public void consoleMode() {
        ifConsole = true;
        while (true) {
            boolean found = false;
            System.out.print("Введите команду (help - список команд): ");
            try {
                com = Client.scanner.nextLine();
            }catch (NoSuchElementException e) {
                System.out.println("\nВы вышли из программы");
                break;
            }
            if (com.equals("exit")) {
                break;
            } else {
                try {
                    for (Command command : commands) {
                        if (com.equals(command.getName())) {
                            found = true;
                            //if (com.equals("execute_script")) {
                               // scriptManager.createScriptFlesArray();
                               // command.execute(ifConsole);
                                //scriptMode();
                                //ifConsole=true;
                           // }
                            command.execute(false,clientCommandManager);
                        }
                    }
                } catch (NoSuchElementException e) {
                    Client.scanner = new Scanner(System.in);
                    System.out.println("Вы вышли из ввода команды команды");
                }
                if (!found) {
                    System.out.println("Команда введениа неверно, или такой команды не существует");
                }
            }
        }
    }
}
