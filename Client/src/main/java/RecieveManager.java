import Messages.Answer;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class RecieveManager {
    private DatagramChannel datagramChannel;

    public RecieveManager(DatagramChannel datagramChannel){
        this.datagramChannel=datagramChannel;
    }

    public Answer recieve() throws IOException, ClassNotFoundException {
        ByteBuffer buffer = ByteBuffer.allocate(1024*1024);
        datagramChannel.receive(buffer);
        buffer.flip();
        int limits = buffer.limit();
        byte[] bytes = new byte[limits];
        buffer.get(bytes,0,limits);
        Answer answer = (Answer) deserialize(bytes);
        return answer;
    }

    public Object deserialize(byte[] data) throws IOException, ClassNotFoundException {
        ByteArrayInputStream in = new ByteArrayInputStream(data);
        ObjectInputStream is = new ObjectInputStream(in);
        return is.readObject();
    }

}
