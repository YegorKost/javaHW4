package com.yegor.chat.version_1.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * This class represents socket connection to the client
 * Created by YegorKost on 17.02.2017.
 */
class SocketForClient implements Runnable{

    private int port;
    private PrintWriter out;

    @Override
    public void run() {
        try (ServerSocket newServerSocket = new ServerSocket(0)){ // create new server socket for client
            this.port = newServerSocket.getLocalPort();
            System.out.println("New socket port " + this.port + "is awaiting to client");
            try (Socket clientSocket = newServerSocket.accept();
                 BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                 PrintWriter out =  new PrintWriter(clientSocket.getOutputStream(), true)){

                this.out = out;

                String fromClient;
                while ((fromClient = in.readLine()) != null) {
                    MainServer.sendMessageForAll(fromClient);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // This method sends message from chat to clients
    void sendMessageForClients(String fromClient) {
        out.println(fromClient);
    }

    // This method returns the number of socket's port to clients
    int getPort() {
        return port;
    }
}
