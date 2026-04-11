package com.example.diplom;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import java.util.Random;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class NewServer extends AppCompatActivity {

    private EditText newIP, newPort, newID;

    private String SERVER_KEY = "Server";

    Random random = new Random();
    Double tempm = (double) (70 + random.nextInt(30));
    Double ramm = (double) (16 + random.nextInt(15));
    Double hddm = (double) (700+ random.nextInt(300));
    String statusm;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference mDataBase = database.getReference("Server");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_server);
        init();
    }

    private void init(){
        newID = findViewById(R.id.newName);
        newIP = findViewById(R.id.newIP);
        newPort = findViewById(R.id.newPort);
        mDataBase = FirebaseDatabase.getInstance().getReference(SERVER_KEY);
    }

    public void onClickSaveServer(View view){
        String id = newID.getText().toString();
        String IP = newIP.getText().toString();
        String Port = newPort.getText().toString();
        if (id.equals("sovcom5623")) {
            statusm = "ok";
        } else {
            statusm = "error 404";
        }
        String status = statusm;
        String temp = String.valueOf(tempm);
        String ram = String.valueOf(ramm);
        String hdd = String.valueOf(hddm);
        if (status.equals("error 404")){
            temp = "error 404";
            ram = "error 404";
            hdd = "error 404";
        }
        Server newServer = new Server(id, IP, Port, status, temp, ram, hdd);
        if (!TextUtils.isEmpty(IP) && !TextUtils.isEmpty(Port)) {
            mDataBase.push().setValue(newServer);
            Toast.makeText(this, "Сервер подключен", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "Пустое поле", Toast.LENGTH_SHORT).show();
        }
        Intent servers = new Intent(this, servers_new.class);
        startActivity(servers);
    }
}
