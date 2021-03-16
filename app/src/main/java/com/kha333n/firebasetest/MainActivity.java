package com.kha333n.firebasetest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance("https://kha333n-firebase-test-default-rtdb.firebaseio.com/");

///    //Sending Data to Firebase simple key value
    private Button sendDataButton;
    private DatabaseReference dbRefForAddingValue = firebaseDatabase.getReference("Test");

///    //Sending Data to Firebase under child

    private Button sendValueToFirebase;
    private EditText valueForFirebase;
    private DatabaseReference dbRefForAddValueToFirebase = firebaseDatabase.getReference("Users");

    private Button loginPage;
    private Button listAdapterPage;
    private Button firestoreFunctions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

///        //Sending Data to Firebase simple key value

        sendDataButton = findViewById(R.id.sendDataButton);
        sendDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbRefForAddingValue.setValue("Writing test string to firebase");
            }
        });


///        //Sending Data to Firebase under child

        sendValueToFirebase = findViewById(R.id.add_value_to_firebase);
        valueForFirebase = findViewById(R.id.value_for_firebase);
        sendValueToFirebase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value = valueForFirebase.getText().toString();

                DatabaseReference childRef = dbRefForAddValueToFirebase.child("Name");
                childRef.setValue(value);
            }
        });

        loginPage = findViewById(R.id.loginButton);
        loginPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
            }
        });

        listAdapterPage = findViewById(R.id.listAdapterPage);
        listAdapterPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ListAdapter.class));
            }
        });

        firestoreFunctions = findViewById(R.id.firestoreStorageButton);
        firestoreFunctions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, FirestoreFunctions.class));
            }
        });
    }
}