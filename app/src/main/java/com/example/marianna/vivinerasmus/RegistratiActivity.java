package com.example.marianna.vivinerasmus;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegistratiActivity extends AppCompatActivity {

    private final static String TAG = "Registrati";

    private Button mRegistrati;
    private EditText mEmail, mUsername, mPassword;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    private TextView mLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrati);

        //widget
        mLogin = (TextView) findViewById(R.id.textLogin);
        mRegistrati = (Button) findViewById(R.id.btnRegistrati);
        mEmail = (EditText) findViewById(R.id.textEmail);
        mUsername = (EditText) findViewById(R.id.textUsername);
        mPassword = (EditText) findViewById(R.id.textPassword);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Users");

        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegistratiActivity.this, LoginActivity.class));
            }
        });

        mRegistrati.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(RegistratiActivity.this, "ATTENDI…", Toast.LENGTH_LONG).show();
                //trim: elimino gli spazi
                final String username = mUsername.getText().toString().trim();
                final String email = mEmail.getText().toString().trim();
                final String password = mPassword.getText().toString().trim();

                if (!email.isEmpty() && !username.isEmpty() && !password.isEmpty()) {
                    mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                String user_id = mAuth.getCurrentUser().getUid();
                                DatabaseReference current_user_db = mDatabase.child(user_id);
                                current_user_db.child("Username").setValue(username);
                                current_user_db.child("Email").setValue(email);

                                Toast.makeText(RegistratiActivity.this, "Registrazione avvenuta con successo", Toast.LENGTH_LONG).show();

                                Intent regIntent = new Intent(RegistratiActivity.this, ListaActivity.class);
                                startActivity(regIntent);
                            } else {
                                Toast.makeText(RegistratiActivity.this, "Registrazione fallita.", Toast.LENGTH_SHORT).show();
                                try {
                                    throw task.getException();
                                } catch (FirebaseAuthWeakPasswordException e) {
                                    mPassword.setError(e.getReason());
                                    mPassword.requestFocus();
                                } catch (FirebaseAuthInvalidCredentialsException e) {
                                    mEmail.setError(getString(R.string.error_invalid_email));
                                    mEmail.requestFocus();
                                } catch (FirebaseAuthUserCollisionException e) {
                                    mEmail.setError("Email già utilizzata.");
                                    mEmail.requestFocus();
                                } catch (Exception e) {
                                    Log.e(TAG, e.getMessage());
                                }
                            }
                        }
                    });
                } else {
                    Toast.makeText(RegistratiActivity.this, "Completa tutti i campi.", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

}

