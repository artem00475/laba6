import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.nio.channels.DatagramChannel;

public class Server {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        DatagramSocket server = new DatagramSocket(4584);
        byte[] bufer = new byte[1024];
        DatagramPacket packet = new DatagramPacket(bufer,bufer.length);
        server.receive(packet);
        Request request = (Request) Server.deserialize(packet.getData());
        System.out.println(request.getName() + request.getId() + request.getObject());
        //String response = new String(packet.getData());
        //System.out.println(response);
        //server.close();

    }

    public static Serializable deserialize(byte[] data) throws IOException, ClassNotFoundException {
        ByteArrayInputStream in = new ByteArrayInputStream(data);
        ObjectInputStream is = new ObjectInputStream(in);
        Serializable mess = (Serializable) is.readObject();
        in.close();
        is.close();
        return mess;
    }
}
