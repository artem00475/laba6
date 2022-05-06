import Messages.Request;
import commands.*;
import exceptions.ConnectionException;

import java.util.*;

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
    private Deque<String> scriptQueue = new LinkedList<>();
    private SendManager sendManager;
    private RecieveManager recieveManager;

    public ClientManager( SendManager sendManager, RecieveManager recieveManager) {
        this.sendManager=sendManager;
        this.recieveManager=recieveManager;
        clientCommandManager = new ClientCommandManager(scriptQueue);
    }

    public void consoleMode() {
        ifConsole = true;
        while (true) {
            boolean found = false;
            System.out.print("Введите команду (help - список команд): ");
            try {
                com = Client.scanner.nextLine().toLowerCase(Locale.ROOT);
            } catch (NoSuchElementException e) {
                System.out.println("\nВы вышли из программы");
                break;
            } if (com.equals("exit")) {
                break;
            } else {
                try {
                    for (Command command : commands) {
                        if (com.equals(command.getName())) {
                            found = true;
                            Request request = clientCommandManager.execute(new Request(command),ifConsole);
                            if (request != null){
                                sendManager.send(request);
                                recieveManager.printRecieved();
                            }if (com.equals("execute_script")) {
                                scriptMode();
                                ifConsole = true;
                            }
                        }
                    }
                }catch (NoSuchElementException e) {
                    Client.scanner = new Scanner(System.in);
                    System.out.println("Вы вышли из ввода команды команды");
                }catch (ConnectionException e){
                    System.out.println("Повторите попытку позже");
                } if (!found) {
                    System.out.println("Команда введениа неверно, или такой команды не существует");
                }
            }
        }
    }

    public void scriptMode() {
        ifConsole = false;
        while (!scriptQueue.isEmpty()) {
            String com = scriptQueue.removeFirst().toLowerCase(Locale.ROOT);
            if (com.equals("stop")) {
                System.out.println("Скрипт выполнен");
                break;
            } else {
                for (Command command : commands) {
                    if (com.equals(command.getName())) {
                        Request request;
                        if (!command.hasArgement()) {
                            request = clientCommandManager.execute(new Request(command),true);
                        } else {
                            request =  clientCommandManager.execute(new Request(command),ifConsole);
                        }if (request != null) {
                            sendManager.send(request);
                            recieveManager.printRecieved();
                        }
                    }
                }
            }
        }
    }
}
