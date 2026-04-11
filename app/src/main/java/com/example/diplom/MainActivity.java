package com.example.diplom;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button buttonLog;
    Button buttonReg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonLog = (Button) findViewById(R.id.ButtonLog);
        buttonLog.setOnClickListener(this);
        buttonReg = (Button) findViewById(R.id.ButtonReg);
        buttonReg.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.ButtonLog) {
            Intent login = new Intent(this, Login.class);
            startActivity(login);
        }
        if (view.getId() == R.id.ButtonReg) {
            Intent registration = new Intent(this, Registration.class);
            startActivity(registration);
        }
//        switch (view.getId()) {
//            case R.id.ButtonLog:
//                Intent login = new Intent(this, Login.class);
//                startActivity(login);
//                break;
//            case R.id.ButtonReg:
//                Intent registration = new Intent(this, Registration.class);
//                startActivity(registration);
//                break;
//    }
    }
}