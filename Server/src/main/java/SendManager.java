import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class SendManager {
    private byte[] buffer;
    private DatagramSocket datagramSocket;
    private RecieveManager recieveManager;

    public SendManager (DatagramSocket datagramSocket,RecieveManager recieveManager){
        this.datagramSocket = datagramSocket;
        this.recieveManager=recieveManager;
    }

    public void sendAnswer(String string, boolean wasErrors) throws IOException {
        Answer answer = new Answer(string,wasErrors);
        buffer = serialize(answer);
        DatagramPacket output = new DatagramPacket(buffer,buffer.length,recieveManager.getAdress(),recieveManager.getPort());
        datagramSocket.send(output);
    }

    public byte[] serialize(Object obj) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ObjectOutputStream os = new ObjectOutputStream(out);
        os.writeObject(obj);
        byte[] outMess = out.toByteArray();
        out.close();
        os.close();
        return outMess;
    }

}
