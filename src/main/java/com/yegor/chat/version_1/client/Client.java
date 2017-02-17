package com.yegor.chat.version_1.client;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

/**
 * This class represents the clients of chat.
 * Created by YegorKost on 17.02.2017.
 */
public class Client {
    // this flag is true when client is going out from chat
    private static boolean exit = false;

    public static void main(String[] args) {
        // arg[0] - client's name
        Thread.currentThread().setName(args[0]);

        try (Socket mainSocket = new Socket(InetAddress.getByName("localhost"), 1311);
             BufferedReader fromMainSocket = new BufferedReader(new InputStreamReader(mainSocket.getInputStream()))){

            String port;
            if ((port = fromMainSocket.readLine()) != null) {
                System.out.println("New port for " + Thread.currentThread().getName() + " is: " + port);
                connectToChat(Integer.parseInt(port));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // This method connects clients to chat through new socket
    private static void connectToChat(int port) {
        try (Socket clientSocket = new Socket(InetAddress.getByName("localhost"), port);
             BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
             BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)){

            System.out.println("New client is in the Chat");

            ListenChat listenChat = new ListenChat(in);
            Thread listenThread = new Thread(listenChat);
            listenThread.start();

            String fromStdIn;
            while ((fromStdIn = stdIn.readLine()) != null) {
                if (fromStdIn.equals("exit")){
                    exit = true; // sets true to close listening of chat
                    out.println(Thread.currentThread().getName() + " has gone out");
                    listenThread.join();
                    return;
                } else {
                    out.println(Thread.currentThread().getName() + ": " + fromStdIn);
                }
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    // This class represents the thread that listens chat
    private static class ListenChat implements Runnable {
        private BufferedReader in;

        ListenChat(BufferedReader in) {
            this.in = in;
        }

        @Override
        public void run() {
            try {
                String fromChart;
                while ((fromChart = in.readLine()) != null && !exit) {
                    System.out.println(fromChart);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}







