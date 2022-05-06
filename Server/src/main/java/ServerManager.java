import Messages.Answer;
import Messages.Request;
import collection.CollectionManager;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.Locale;
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
                Answer answer =  serverCommandManager.execute(request, false);
                sendManager.sendAnswer(answer);
            }catch (SocketTimeoutException exception){
                try {
                    if (System.in.available()>0) {
                        byte[] bytes = new byte[4];
                        System.in.read(bytes);
                        if(bytes.length==4) {
                            String com= new String(bytes).toLowerCase(Locale.ROOT);
                            if (com.equals("exit")) {
                                break;
                            } else if (com.equals("save")) {
                                collectionManager.saveCollection();
                            }else System.out.println("Комманда введена неверно");
                        }
                    }
                }catch (NullPointerException | IOException e){}
            } catch (IOException | ClassNotFoundException e) {
                sendManager.sendAnswer(new Answer(null,true));
            }
        }
    }
}
