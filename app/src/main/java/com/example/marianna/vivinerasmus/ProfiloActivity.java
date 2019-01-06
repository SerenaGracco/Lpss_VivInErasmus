package com.example.marianna.vivinerasmus;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfiloActivity extends AppCompatActivity {

    private EditText editNuovoNome;
    private Button doneBtn;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabaseUserAttuale;
    private TextView mNome;
    private TextView mEmail;
    private TextView mUsername;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profilo);

        mNome = findViewById(R.id.textView1);
        mEmail = findViewById(R.id.textView2);
        mUsername = findViewById(R.id.textView3);

        mAuth = FirebaseAuth.getInstance();
        final String userID = mAuth.getCurrentUser().getUid();
        mDatabaseUserAttuale = FirebaseDatabase.getInstance().getReference().child("Users").child(userID);


        mDatabaseUserAttuale.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                final String nome = dataSnapshot.child("name").getValue(String.class);
                mNome.setText(nome);
                final String username = dataSnapshot.child("Username").getValue(String.class);
                mUsername.setText(username);
                final String email = dataSnapshot.child("Email").getValue(String.class);
                mEmail.setText(email);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        editNuovoNome = (EditText) findViewById(R.id.profName);
        doneBtn = (Button) findViewById(R.id.doneBtn);

        doneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String name = editNuovoNome.getText().toString().trim();
                //final String userID = mAuth.getCurrentUser().getUid();
                if (!name.isEmpty()) {

                    mDatabaseUserAttuale.child("name").setValue(name);

                    Toast.makeText(ProfiloActivity.this, "Nome cambiato.", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(ProfiloActivity.this, ListaActivity.class);
                    startActivity(i);
                }

            }
        });


    }

}
