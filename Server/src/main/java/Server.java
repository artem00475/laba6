import java.io.IOException;
import java.net.DatagramSocket;


public class Server {
    public static RecieveManager recieveManager;
    public static SendManager sendManager;
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        DatagramSocket server = new DatagramSocket(4584);
        recieveManager = new RecieveManager(server);
        sendManager = new SendManager(server);
        ServerCommandManager serverCommandManager = new ServerCommandManager();
        Request request = recieveManager.recieveRequest();
        System.out.println(request.getName() + request.getId() + request.getObject());
        serverCommandManager.execute(request.getName(),false);



        server.close();

    }


}
