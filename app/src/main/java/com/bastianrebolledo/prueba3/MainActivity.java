package com.bastianrebolledo.prueba3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayList<String> sensorsUIDList = new ArrayList<String>();
    private ArrayList<String> sensorsNameValue = new ArrayList<String>();

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, sensorsNameValue);
        listView.setAdapter(adapter);

        //init firebase
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

        //laod data
        databaseReference.child("sensors").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    sensorsUIDList.clear();
                    for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                        sensorsUIDList.add(dataSnapshot.child("uid").getValue().toString());

                        String name = dataSnapshot.child("name").getValue().toString();
                        String value = dataSnapshot.child("value").getValue().toString();
                        sensorsNameValue.add(name + ": " + value);
                    }
                    adapter.notifyDataSetChanged();
                }
                else {
                    Toast.makeText(MainActivity.this, "No hay registros en la base de datos! :(", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        //On click listener listView
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(MainActivity.this, EditItem.class);
                i.putExtra("sensorUID", sensorsUIDList.get(position));
                startActivity(i);
            }
        });
    }
    public void goMQTT (View view){
        Intent i = new Intent(this, MQTTActivity.class);
        startActivity(i);
    }
}