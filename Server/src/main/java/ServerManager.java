import Messages.Answer;
import Messages.Request;
import collection.CollectionManager;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.Scanner;

public class ServerManager {
    private SendManager sendManager;
    private RecieveManager recieveManager;
    private CollectionManager collectionManager;
    private ServerCommandManager serverCommandManager;
    private Scanner scanner;

    public ServerManager(SendManager sendManager,RecieveManager recieveManager, CollectionManager collectionManager){
        this.collectionManager=collectionManager;
        this.recieveManager=recieveManager;
        this.sendManager=sendManager;
        serverCommandManager = new ServerCommandManager(collectionManager);
        scanner = new Scanner(System.in);
    }

    public void run() {
        while (true){
            Request request;
            try {
                request = recieveManager.recieveRequest();
                sendManager.sendAnswer(serverCommandManager.execute(request, false));
            }catch (SocketTimeoutException exception){
                if (scanner.hasNextLine()){
                    String com = scanner.nextLine();
                    if (com.equals("exit")){
                        break;
                    }else if (com.equals("save")){
                        collectionManager.saveCollection();
                    }
                }
            } catch (IOException | ClassNotFoundException e) {
                sendManager.sendAnswer(new Answer(null,true));
            }

        }
    }
}
