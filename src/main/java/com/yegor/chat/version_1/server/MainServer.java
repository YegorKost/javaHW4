package com.yegor.chat.version_1.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 * This class represents the main server of a chat.
 * Main server is listening the clients on port: 1311.
 * When new client connects to main server, new socket with new port are created,
 * and client binds to it. Then new socket added to the map to client receives messages.
 * Created by YegorKost on 17.02.2017.
 */
public class MainServer {

    // This map contains all sockets of the chat's clients
    private static Map<Integer, SocketForClient> socketsForClient = new HashMap<>();

    public static void main(String[] args) {

        //The main socket that registers clients to the chat
        try (ServerSocket serverSocket = new ServerSocket(1311)){

            while (true){
                System.out.println("Main server is listening");
                try (Socket socket = serverSocket.accept(); // awaits to new client
                     PrintWriter out = new PrintWriter(socket.getOutputStream())){

                    System.out.println("Client is connected to main server (" + socket.getInetAddress() + " port: " + socket.getLocalPort() + ")");
                    int newPort = generateServerSocketForClient();
                    out.println(newPort); // send new socket's port to the client

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // This method create new object "SocketForClient" that contains new socket and new port for client
    private static int generateServerSocketForClient() {
        int port = 0;
        try {
            SocketForClient socketForClient = new SocketForClient();
            Thread thread = new Thread(socketForClient);
            thread.start(); // start thread with new socket
            Thread.sleep(500); // await when new socket starts
            port = socketForClient.getPort(); // request new port for client
            socketsForClient.put(port, socketForClient); // add port and new object "SocketForClient" with socket to the map
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return port; // return new port for client
    }

    // This method sends messages for all registered clients in the map
    static void sendMessageForAll(String fromClient) {
        for (SocketForClient clientSockets: socketsForClient.values()){
            clientSockets.sendMessageForClients(fromClient);
        }
    }
}
