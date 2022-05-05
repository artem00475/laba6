import Messages.Answer;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.SocketTimeoutException;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class RecieveManager {
    private DatagramChannel datagramChannel;

    public RecieveManager(DatagramChannel datagramChannel){
        this.datagramChannel=datagramChannel;
    }

    public Answer recieve() throws IOException, ClassNotFoundException, SocketTimeoutException {
        //ByteBuffer buffer = ByteBuffer.allocate(1024*1024);
        byte[] buffer = new byte[1024*1024];
        DatagramPacket datagramPacket = new DatagramPacket(buffer,buffer.length);
        datagramChannel.socket().receive(datagramPacket);
        ByteBuffer byteBuffer = ByteBuffer.wrap(datagramPacket.getData());
        byteBuffer.flip();
        int limits = byteBuffer.limit();
        byte[] bytes = new byte[limits];
        byteBuffer.get(bytes,0,limits);
        Answer answer = (Answer) deserialize(bytes);
        return answer;
    }

    public Object deserialize(byte[] data) throws IOException, ClassNotFoundException {
        ByteArrayInputStream in = new ByteArrayInputStream(data);
        ObjectInputStream is = new ObjectInputStream(in);
        return is.readObject();
    }

}
