package com.example.marianna.vivinerasmus;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegistratiActivity extends AppCompatActivity {
    private Button mRegistrati;
    private EditText mEmail, mUsername, mPassword;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    private TextView mLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mLogin = (TextView)findViewById(R.id.textLogin);
        mRegistrati = (Button)findViewById(R.id.btnRegistrati);
        mEmail = (EditText)findViewById(R.id.textEmail);
        mUsername = (EditText)findViewById(R.id.textUsername);
        mPassword = (EditText)findViewById(R.id.textPassword);
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Users");
        mLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegistratiActivity.this, LoginActivity.class));
            } });
        mRegistrati.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(RegistratiActivity.this, "LOADING…", Toast.LENGTH_LONG).show();
                final String username = mUsername.getText().toString().trim();
                final String email = mEmail.getText().toString().trim();
                final String password = mPassword.getText().toString().trim();
                if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(username)&&!TextUtils.isEmpty(password)){
                    mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            String user_id = mAuth.getCurrentUser().getUid();
                            DatabaseReference current_user_db = mDatabase.child(user_id);
                            current_user_db.child("Username").setValue(username);
                            //current_user_db.child("Email").setValue(email);
                            //current_user_db.child(“Image”).setValue(“Default”);
                            Toast.makeText(RegistratiActivity.this, "Registrazione avvenuta con successo", Toast.LENGTH_LONG).show();
                         /*   Intent regIntent = new Intent(RegistratiActivity.this, ProfileActivity.class);
                            regIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(regIntent); */
                        } });
                }else {
                    Toast.makeText(RegistratiActivity.this, "Completa tutti i campi.", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

}

