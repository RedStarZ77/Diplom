package com.example.diplom;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.diplom.databinding.ActivityServersNewBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class servers_new extends AppCompatActivity implements View.OnClickListener {

    public Button analitic;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servers_new);
        init();
        getDataFromBD();
        setOnClickItem();
        analitic = findViewById(R.id.analiticButton);
        analitic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoURL("https://console.firebase.google.com/u/0/project/diplom-c7ddd/analytics/app/android:com.example.diplom/overview/reports~2Fdashboard%3Fr%3Dfirebase-overview&fpn%3D637790362556");
            }
        });
    }

    @Override
    public void onClick(View view){
        Intent newTest = new Intent(this, NewServer.class);
        startActivity(newTest);
    }

    public void onClickNewTest(View view){
        Intent newServer = new Intent(this, NewServer.class);
        startActivity(newServer);
    }

    void gotoURL(String s){
        try {
            Uri uri = Uri.parse(s);
            startActivity(new Intent(Intent.ACTION_VIEW, uri));
        }
        catch (Exception e){
            Toast.makeText(getApplicationContext(), "Ошибка перехода на сайт", Toast.LENGTH_SHORT).show();
        }
    }

    private AppBarConfiguration mAppBarConfiguration;
    private DatabaseReference mDataBase;
    private ArrayAdapter<String> adapter;
    private List<String> listData;
    private ListView listServers;
    private List<Server> listServer;
    private String SERVER_KEY = "Server";

    private void init(){
        listServers = findViewById(R.id.Servers);
        listData = new ArrayList<>();
        listServer = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listData);
        listServers.setAdapter(adapter);
        mDataBase = FirebaseDatabase.getInstance().getReference(SERVER_KEY);
    }

    private void getDataFromBD(){
        ValueEventListener vListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (listData.size() > 0) listData.clear();
                if (listServer.size() > 0) listServer.clear();
                for (DataSnapshot ds : dataSnapshot.getChildren()){
                    Server server = ds.getValue(Server.class);
                    assert server != null;
                    listData.add(server.id);
                    listServer.add(server);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        mDataBase.addValueEventListener(vListener);
    }

    private void setOnClickItem(){
        listServers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Server server = listServer.get(position);
                Intent showServer = new Intent(servers_new.this, ShowServer.class);
                showServer.putExtra("id", server.id);
                showServer.putExtra("ip", server.IP);
                showServer.putExtra("port", server.Port);
                showServer.putExtra("status", server.status);
                showServer.putExtra("temp", server.temp);
                showServer.putExtra("ram", server.ram);
                showServer.putExtra("hdd", server.hdd);
                startActivity(showServer);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.servers_new, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_servers_new);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}