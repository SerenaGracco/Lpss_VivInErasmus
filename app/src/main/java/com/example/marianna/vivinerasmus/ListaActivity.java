package com.example.marianna.vivinerasmus;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.marianna.vivinerasmus.datamodel.DataStore;
import com.example.marianna.vivinerasmus.datamodel.Universitas;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;


public class ListaActivity extends AppCompatActivity {


    // Costanti
    private final static String EXTRA_UNIVERSITA = "universita";

    // Widget
    private ListView mLista;
    private FloatingActionButton mOmino;
    private SearchView mCerca;

    // Adapter
    private UniAdapter adapter;

    // Data Store
    private DataStore archivio = new DataStore();

    private FirebaseAuth mAuth;
    private FirebaseDatabase database;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        mOmino = (FloatingActionButton) findViewById(R.id.fabOmino);
        mLista = (ListView) findViewById(R.id.listaUniversita);
        mCerca = (SearchView) findViewById(R.id.searchView);

        mAuth = FirebaseAuth.getInstance();

        adapter = new UniAdapter(this);

        archivio.iniziaOsservazioneUniversita(new DataStore.UpdateListener() {
            @Override
            public void universitaAggiornate() {
                adapter.update(archivio.elencoUni());
            }
        });

        mLista.setAdapter(adapter);

        mLista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Universitas universita = adapter.getItem(position);
                Intent intent = new Intent(view.getContext(), DettaglioUniActivity.class);
                intent.putExtra(EXTRA_UNIVERSITA, universita);
                startActivity(intent);
            }
        });


        mOmino.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                FirebaseUser currentUser = mAuth.getCurrentUser();
                if (currentUser == null) {
                    Intent intent = new Intent(getBaseContext(), LoginActivity.class);
                    startActivity(intent);
                } else {
                    Intent intent2 = new Intent(getBaseContext(), ProfiloActivity.class);
                    startActivity(intent2);
                }
            }

        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_lista, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_add) {
            FirebaseUser currentUser = mAuth.getCurrentUser();
            if (currentUser != null) {
                startActivity(new Intent(ListaActivity.this, AggiungiUniActivity.class));
            } else {
                Toast.makeText(ListaActivity.this, "DEVI ESSERE LOGGATO", Toast.LENGTH_LONG).show();
                startActivity(new Intent(ListaActivity.this, MainActivity.class));
            }
        } else if (id == R.id.logout) {
            mAuth.signOut();
            Intent logoutIntent = new Intent(ListaActivity.this, MainActivity.class);
            /*If set, and the activity being launched is already running in the current task,
            then instead of launching a new instance of that activity,
            all of the other activities on top of it will be closed and this Intent will be delivered
            to the (now on top) old activity as a new Intent*/
            logoutIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(logoutIntent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        archivio.terminaOsservazioneUniversita();
    }
}
