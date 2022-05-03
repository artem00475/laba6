import Messages.Request;
import collection.CollectionManager;

import java.io.IOException;

public class ServerManager {
    private SendManager sendManager;
    private RecieveManager recieveManager;
    private CollectionManager collectionManager;
    private ServerCommandManager serverCommandManager;
    public ServerManager(SendManager sendManager,RecieveManager recieveManager, CollectionManager collectionManager){
        this.collectionManager=collectionManager;
        this.recieveManager=recieveManager;
        this.sendManager=sendManager;
        serverCommandManager = new ServerCommandManager(sendManager,collectionManager);
    }

    public void run() throws IOException, ClassNotFoundException {
        while (true){
            Request request = recieveManager.recieveRequest();
            serverCommandManager.execute(request);
        }
    }

}
