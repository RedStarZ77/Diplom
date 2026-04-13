package com.example.diplom;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ShowServer extends AppCompatActivity {

    private TextView id, ip, port, status, temp, ram, hdd;

    private DatabaseReference mDataBase;

    private String SERVER_KEY = "Server";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_server);
        init();
        getIntentServer();
    }

    private void init(){
        id = findViewById(R.id.id);
        ip = findViewById(R.id.ip);
        port = findViewById(R.id.port);
        status = findViewById(R.id.status);
        temp = findViewById(R.id.temp);
        ram = findViewById(R.id.ram);
        hdd = findViewById(R.id.hdd);
        mDataBase = FirebaseDatabase.getInstance().getReference(SERVER_KEY);
    }

    private void getIntentServer(){
        Intent showServer = getIntent();
        if (showServer != null){
            id.setText("Имя сервера: " + showServer.getStringExtra("id"));
            ip.setText("IP сервера: " + showServer.getStringExtra("ip"));
            port.setText("Порт сервера: " + showServer.getStringExtra("port"));
            status.setText("Статус сервера: " + showServer.getStringExtra("status"));
            temp.setText("Температура сервера: " + showServer.getStringExtra("temp"));
            ram.setText("Оперативная память сервера: " + showServer.getStringExtra("ram"));
            hdd.setText("HDD сервера: " + showServer.getStringExtra("hdd"));
        }
    }
}
