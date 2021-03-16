package com.kha333n.firebasetest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private EditText email, password;
    private Button login;

    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebaseAuth = FirebaseAuth.getInstance();

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        login = findViewById(R.id.loginButton);

        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (firebaseAuth.getCurrentUser() == null){
                    Toast.makeText(LoginActivity.this, "Login Now...", Toast.LENGTH_SHORT).show();
                }
                else {
                    startActivity(new Intent(LoginActivity.this, AccountActivity.class));
                }
            }
        };

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startSignIn();
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        firebaseAuth.addAuthStateListener(authStateListener);
    }

    private void startSignIn(){

         String mEmail = email.getText().toString();
         String mPassword = password.getText().toString();

         if (TextUtils.isEmpty(mEmail) || TextUtils.isEmpty(mPassword))
         {
             Toast.makeText(this, "Fields are empty", Toast.LENGTH_SHORT).show();
         }else {
             firebaseAuth.signInWithEmailAndPassword(mEmail, mPassword)
                     .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                         @Override
                         public void onComplete(@NonNull Task<AuthResult> task) {
                             if (!task.isSuccessful()){
                                 Toast.makeText(LoginActivity.this, "Login Failed", Toast.LENGTH_LONG).show();
                             }
                         }
                     });
         }

    }
}