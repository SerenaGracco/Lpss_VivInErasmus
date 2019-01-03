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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {
    private EditText mEmail, mPassword;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    private Button mLogin;
    private TextView mSignUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mLogin = (Button)findViewById(R.id.btnLogin);
        mEmail = (EditText)findViewById(R.id.textEmail);
        mPassword = (EditText)findViewById(R.id.textPassword);
        mAuth = FirebaseAuth.getInstance();
        mSignUp=(TextView)findViewById(R.id.textSignUp);
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Users");
        mSignUp.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegistratiActivity.class));
            } });
        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(LoginActivity.this, "PROCESSING….", Toast.LENGTH_LONG).show();
                String email = mEmail.getText().toString().trim();
                String password = mPassword.getText().toString().trim();
                if (!TextUtils.isEmpty(email)&& !TextUtils.isEmpty(password)){
                    mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                checkUserExistence();
                            }else {
                                Toast.makeText(LoginActivity.this, "Login fallito: username inesistente", Toast.LENGTH_SHORT).show();
                            } } });
                }else {
                    Toast.makeText(LoginActivity.this, "Completa tutti i campi", Toast.LENGTH_SHORT).show();
                } } }); }
    public void checkUserExistence(){
        final String user_id = mAuth.getCurrentUser().getUid();
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChild(user_id)){
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                }else {
                    Toast.makeText(LoginActivity.this, "User non registrato", Toast.LENGTH_SHORT).show();
                } }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            } }); }}