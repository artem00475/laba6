import Messages.Answer;
import Messages.Request;
import collection.CollectionManager;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ServerManager {
    private SendManager sendManager;
    private RecieveManager recieveManager;
    private CollectionManager collectionManager;
    private ServerCommandManager serverCommandManager;
    private ServerConsoleCommandManager serverConsoleCommandManager;
    private Scanner scanner;

    public ServerManager(SendManager sendManager,RecieveManager recieveManager, CollectionManager collectionManager){
        this.collectionManager=collectionManager;
        this.recieveManager=recieveManager;
        this.sendManager=sendManager;
        serverCommandManager = new ServerCommandManager(collectionManager);
        scanner = new Scanner(System.in);
        serverConsoleCommandManager = new ServerConsoleCommandManager(scanner,serverCommandManager);
    }

    public void run() {
        while (true) {
            try {
                Request request;
                try {
                    request = recieveManager.recieveRequest();
                    Answer answer = serverCommandManager.execute(request, false);
                    sendManager.sendAnswer(answer);
                } catch (SocketTimeoutException exception) {
                    try {
                        if (System.in.available() > 0) {
                            String com = scanner.nextLine().toLowerCase(Locale.ROOT);
                            if (com.equals("exit")) {
                                collectionManager.saveCollection();
                                break;
                            } else if (com.equals("save")) {
                                collectionManager.saveCollection();
                            } else {
                                try {
                                    serverConsoleCommandManager.run(com);
                                }catch (NoSuchElementException e){
                                    System.out.println("Вы вышли из ввода команды");
                                    scanner = new Scanner(System.in);
                                    serverConsoleCommandManager = new ServerConsoleCommandManager(scanner,serverCommandManager);
                                }
                            }
                        }
                    } catch (NullPointerException | IOException e) {
                    }
                } catch (IOException | ClassNotFoundException e) {
                    sendManager.sendAnswer(new Answer(null, true));
                }
            }catch (NoSuchElementException e){break;}
        }
    }
}
