package com.example.diplom;

public class Server {

    public String id, IP, Port, status, temp, ram, hdd;
    public Server(){

    }

    public Server (String id, String IP, String Port, String status,String temp,String ram,String hdd){
        this.id = id;
        this.IP = IP;
        this.Port = Port;
        this.status = status;
        this.temp = temp;
        this.ram = ram;
        this.hdd = hdd;
    }
}
