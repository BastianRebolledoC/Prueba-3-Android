package com.bastianrebolledo.prueba3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bastianrebolledo.prueba3.models.Sensor;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.UUID;

public class AddSensor extends AppCompatActivity {
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    private TextView txtName;
    private TextView txtType;
    private TextView txtValue;
    private TextView txtUbication;
    private TextView txtDateTime;
    private TextView txtExtra;

    private String UID = "";
    private String Name = "";
    private String Type = "";
    private String Value = "";
    private String Ubication = "";
    private String DateTime = "";
    private String Extra = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_sensor);

        txtName = findViewById(R.id.Name);
        txtType = findViewById(R.id.Type);
        txtValue = findViewById(R.id.Value);
        txtUbication = findViewById(R.id.Ubication);
        txtDateTime = findViewById(R.id.DateTime);
        txtExtra = findViewById(R.id.Extra);

        //init firebase
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

        //info from previous activity
        String data = getIntent().getStringExtra("sensorInfo");
        try {
            JSONObject json = new JSONObject(data);
            UID = UUID.randomUUID().toString();
            Name = json.getString("Name");
            Type = json.getString("Type");
            Value = json.getString("Value");
            Ubication = json.getString("Ubication");
            DateTime = json.getString("DateTime");
            Extra = json.getString("Extra");
        } catch (JSONException e) {
            Toast.makeText(this, "Se produjo un error, intente más tarde :(", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(AddSensor.this, MQTTActivity.class);
            startActivity(i);
        }

        //Set Text
        txtName.setText("Nombre del sensor: " + Name);
        txtType.setText("Tipo de sensor: " + Type);
        txtValue.setText("Valor del sensor: " + Value);
        txtUbication.setText("Ubicación del sensor: " + Ubication);
        txtDateTime.setText("Fecha y Hora: " + DateTime);
        txtExtra.setText("Observación: " + Extra);

    }
    public void save(View view){
        if(!UID.isEmpty() && !Name.isEmpty() && !Type.isEmpty() && !Value.isEmpty() && !Ubication.isEmpty() && !DateTime.isEmpty()){
            Sensor sensor = new Sensor(UID,Name,Type,Value, Ubication,DateTime,Extra);
            databaseReference.child("sensors").child(UID).setValue(sensor);

            Toast.makeText(this, "Usuario guardado!", Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(this, "No se puede guardar dato, pruebe con otro", Toast.LENGTH_SHORT).show();
        }
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
    public void back(View view){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
}