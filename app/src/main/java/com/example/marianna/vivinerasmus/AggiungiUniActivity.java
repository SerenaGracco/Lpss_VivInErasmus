package com.example.marianna.vivinerasmus;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.marianna.vivinerasmus.datamodel.DataStore;
import com.example.marianna.vivinerasmus.datamodel.Universitas;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AggiungiUniActivity extends AppCompatActivity {

    private EditText mAggID;
    private EditText mAggNome;
    private EditText mAggIndirizzo;
    private EditText mAggEmail;
    private EditText mAggSito;
    private Button btnAggiungi;

    private DataStore archivio=new DataStore();

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabaseUni;
    private DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aggiungi_uni);

        mAuth = FirebaseAuth.getInstance();
        mDatabaseUni = FirebaseDatabase.getInstance().getReference().child("Università");



        mAggNome = (EditText) findViewById(R.id.editNomeUni);
        mAggID = (EditText) findViewById(R.id.editIDuni);
        mAggIndirizzo = (EditText) findViewById(R.id.editIndirizzoUni);
        mAggSito = (EditText) findViewById(R.id.editSitoUni);
        mAggEmail = (EditText) findViewById(R.id.editEmailUni);
        btnAggiungi = (Button) findViewById(R.id.btnAggiungi);

        btnAggiungi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String IDuni = mAggNome.getText().toString().trim();
                final String nome = mAggNome.getText().toString().trim();
                final String indirizzo = mAggIndirizzo.getText().toString().trim();
                final String email = mAggEmail.getText().toString().trim();
                final String sito = mAggSito.getText().toString().trim();

                final String userID = mAuth.getCurrentUser().getUid();
                ref=FirebaseDatabase.getInstance().getReference().child("Users").child(userID).child("name");

                if (IDuni.isEmpty())
                {mAggID.setError("ID obbligatorio");}
                else if (nome.isEmpty())
                {mAggNome.setError("Nome obbligatorio");}
                else if (indirizzo.isEmpty())
                { mAggIndirizzo.setError("Indirizzo obbligatorio");}
                else if (sito.isEmpty())
                {mAggSito.setError("Sito obbligatorio");}
                else if (email.isEmpty())
                { mAggEmail.setError("Email obbligatoria");}
                else{

                    Universitas u = new Universitas();
                    u.setIDuni(IDuni);
                    u.setSito(sito);
                    u.setEmail(email);
                    u.setNome(nome);
                    u.setIndirizzo(indirizzo);
                    archivio.aggiungiUni(u);
                    //aggiungo anche l'autore
                    ref.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            String autore = dataSnapshot.getValue(String.class);
                            mDatabaseUni.child(IDuni).child("autore").setValue(autore);
                        }
                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                    Toast.makeText(AggiungiUniActivity.this, "Uni aggiunta", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(AggiungiUniActivity.this, ListaActivity.class);
                    startActivity(i);
                }

            }
        });


    }

}
