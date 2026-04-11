package com.example.diplom;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class SocketClient {
    public static void main(String[] args){
        try {
            Socket clientSocket = new Socket ( "ip",2544);
            InputStream is = clientSocket.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String receivedData = br.readLine();
            System.out.println("Received Data: "+receivedData);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
