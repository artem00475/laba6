import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.nio.channels.DatagramChannel;

public class Server {
    public static void main(String[] args) throws IOException {
        DatagramSocket server = new DatagramSocket(4584);

        System.out.println(server.getPort());
        byte[] bufer = new byte[1024];
        DatagramPacket packet = new DatagramPacket(bufer,bufer.length);
        server.receive(packet);
        System.out.println("ok");
        String response = new String(packet.getData());
        System.out.println(response);
        server.close();
        DatagramChannel.open();
    }
}
