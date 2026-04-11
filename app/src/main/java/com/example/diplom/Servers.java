package com.example.diplom;

import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Servers extends AppCompatActivity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servers);
//        init();
//        getDataFromBD();
//        setOnClickItem();
    }

    public void onClick(View view){
        Intent newServer = new Intent(this, NewServer.class);
        startActivity(newServer);
    }

//    private ListView listView;
//    private ArrayAdapter<String> adapter;
//    private List<String> listData;
//    private DatabaseReference mDataBase;
//    private String SERVER_KEY = "Server";
//    private List<Server> listServer;

//    private void init(){
//        listView = findViewById(R.id.listView);
//        listData = new ArrayList<>();
//        listServer = new ArrayList<>();
//        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listData);
//        listView.setAdapter(adapter);
//        mDataBase = FirebaseDatabase.getInstance().getReference(SERVER_KEY);
//    }

//    private void getDataFromBD(){
//        ValueEventListener vListener = new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
////                if (listData.size() > 0) listData.clear();
////                if (listServer.size() > 0) listServer.clear();
////                for (DataSnapshot ds : dataSnapshot.getChildren()){
////                    Server server = ds.getValue(Server.class);
////                    assert server != null;
//////                    listData.add(test.quest);
////                    listServer.add(server);
////                }
////                adapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        };
//        mDataBase.addValueEventListener(vListener);
//    }
//
//    private void setOnClickItem(){
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
//                Server server = listServer.get(position);
//                Intent showServer = new Intent(Servers.this, ShowServer.class);
//                startActivity(showServer);
//            }
//        });
//    }
}
