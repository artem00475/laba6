import collection.CollectionManager;
import person.Person;

import java.io.IOException;
import java.net.DatagramSocket;


public class Server {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        DatagramSocket server = new DatagramSocket(4584);
        CollectionManager collectionManager = new CollectionManager();
        System.out.println(collectionManager.getCollection());
        System.out.println(Person.getIdArray());
        RecieveManager recieveManager = new RecieveManager(server);
        SendManager sendManager = new SendManager(server,recieveManager);
        ServerManager serverManager = new ServerManager(sendManager,recieveManager,collectionManager);
        serverManager.run();
        server.close();

    }


}
