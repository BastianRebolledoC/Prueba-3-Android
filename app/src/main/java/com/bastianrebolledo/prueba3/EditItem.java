package com.bastianrebolledo.prueba3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONException;
import org.json.JSONObject;


public class EditItem extends AppCompatActivity {

    private TextView txtUID, txtName, txtType, txtValue, txtUbication, txtDateTime;
    private EditText inputExtra;
    private String initialExtra;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);

        txtUID = findViewById(R.id.txtUID);
        txtName = findViewById(R.id.txtName);
        txtType = findViewById(R.id.txtType);
        txtValue = findViewById(R.id.txtValue);
        txtUbication = findViewById(R.id.txtUbication);
        txtDateTime = findViewById(R.id.txtDateTime);
        inputExtra = findViewById(R.id.inputExtra);

        //init firebase
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

        //info from previous activity
        String data = getIntent().getStringExtra("sensorUID");
        databaseReference.child("sensors").child(data).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                txtUID.setText(data);
                txtName.setText(snapshot.child("name").getValue().toString());
                txtType.setText(snapshot.child("type").getValue().toString());
                txtValue.setText(snapshot.child("value").getValue().toString());
                txtUbication.setText(snapshot.child("ubication").getValue().toString());
                txtDateTime.setText(snapshot.child("dateTime").getValue().toString());
                inputExtra.setText(snapshot.child("extra").getValue().toString());
                //backup Extra
                initialExtra = snapshot.child("extra").getValue().toString();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(EditItem.this, "ERROR!:(", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void save(View view){
        if(!inputExtra.getText().toString().trim().equals(initialExtra.trim())){
            String data = getIntent().getStringExtra("sensorUID");
            databaseReference.child("sensors").child(data).child("extra").setValue(inputExtra.getText().toString());
            Toast.makeText(this, "Cambios guardados!", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "No se han registrado cambios!", Toast.LENGTH_SHORT).show();
        }
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
    public void back(View view){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
}