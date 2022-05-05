import Messages.Answer;
import exceptions.ConnectionException;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.SocketTimeoutException;
import java.nio.channels.DatagramChannel;

public class RecieveManager {
    private DatagramChannel datagramChannel;

    public RecieveManager(DatagramChannel datagramChannel){
        this.datagramChannel=datagramChannel;
    }

    public Answer recieve() throws IOException, ClassNotFoundException {
        byte[] buffer = new byte[1024*1024];
        DatagramPacket datagramPacket = new DatagramPacket(buffer,buffer.length);
        datagramChannel.socket().setSoTimeout(1000);
        datagramChannel.socket().receive(datagramPacket);
        byte[] bytes = datagramPacket.getData();
        Answer answer = (Answer) deserialize(bytes);
        return answer;
    }

    public Object deserialize(byte[] data) throws IOException, ClassNotFoundException {
        ByteArrayInputStream in = new ByteArrayInputStream(data);
        ObjectInputStream is = new ObjectInputStream(in);
        return is.readObject();
    }
    public void printRecieved() {
        try {
            System.out.println(recieve().getString());
        }catch (SocketTimeoutException e) {
            System.out.println("Сервер не отвечает, повторная попытка получения ответа...");
            try {
                System.out.println(recieve().getString());
            }catch (IOException | ClassNotFoundException exception){
                throw new ConnectionException("Сервер не отвечает");
            }
        } catch (IOException | ClassNotFoundException e){
            throw new ConnectionException("Что-то пошло не так");
        }
    }

}
