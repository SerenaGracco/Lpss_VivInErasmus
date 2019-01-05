package com.example.marianna.vivinerasmus;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ProfiloActivity extends AppCompatActivity {
//TODO:IMPORTANTE se blog non va, nel profilo diamo un "like" a un'uni
    //TODO : mostrare dati del profilo
    private EditText profUserName;
    private Button doneBtn;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabaseUsers;
    //private DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profilo);

        mAuth = FirebaseAuth.getInstance();
        mDatabaseUsers = FirebaseDatabase.getInstance().getReference().child("Users");
       // ref=FirebaseDatabase.getInstance().getReference();
        profUserName = (EditText) findViewById(R.id.profUserName);
        doneBtn = (Button) findViewById(R.id.doneBtn);

        doneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String name = profUserName.getText().toString().trim();
                final String userID = mAuth.getCurrentUser().getUid();
                if (!name.isEmpty()) {

                    mDatabaseUsers.child(userID).child("name").setValue(name//TODO
                             /*, new ref.CompletionListener() {
                        @Override
                        public void onComplete(FirebaseError firebaseError, FirebaseDatabase firebase) {
                            if (firebaseError != null) {
                                System.out.println("Data could not be saved. " + firebaseError.getMessage());
                            } else {
                                System.out.println("Data saved successfully.");
                            }
                        }
                    });*/);

                    Toast.makeText(ProfiloActivity.this, "Successful", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(ProfiloActivity.this, ListaActivity.class);
                    startActivity(i);
                }

            }
        });


    }

}
