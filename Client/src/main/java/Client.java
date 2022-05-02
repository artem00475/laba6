

import commands.*;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.Scanner;

import static java.lang.System.in;

public class Client {
    public static SendManager sendManager;
    public static Scanner scanner;
    public static void main(String[] args) throws IOException {
        scanner = new Scanner(in);

        sendManager = new SendManager();
        ClientManager clientManager = new ClientManager();
        clientManager.consoleMode();
        //DatagramChannel client = DatagramChannel.open();
        //InetSocketAddress reciever = new InetSocketAddress("localhost",4584);
        //byte[] buffer = new byte[1024];
        //String str = "Hello";
        //buffer= str.getBytes();
        //client.send(ByteBuffer.wrap(buffer),reciever);
        //client.close();
    }
}
