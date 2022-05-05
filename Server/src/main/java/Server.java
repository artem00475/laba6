import collection.CollectionManager;

import java.io.IOException;
import java.net.DatagramSocket;


public class Server {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        DatagramSocket server = new DatagramSocket(4584);
        System.out.println("Сервер запущен");
        server.setSoTimeout(10000);
        CollectionManager collectionManager = new CollectionManager();
        RecieveManager recieveManager = new RecieveManager(server);
        SendManager sendManager = new SendManager(server,recieveManager);
        ServerManager serverManager = new ServerManager(sendManager,recieveManager,collectionManager);
        serverManager.run();
        server.close();

    }

}
