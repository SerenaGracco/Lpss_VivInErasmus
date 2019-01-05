package com.example.marianna.vivinerasmus;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;

import com.example.marianna.vivinerasmus.datamodel.DataStore;
import com.example.marianna.vivinerasmus.datamodel.Universitas;


public class ListaActivity extends AppCompatActivity {


    // Costanti
    private final static String EXTRA_UNIVERSITA = "universita";
    //private final static String TAG = "VivInErasmus";

    // Widget
    private ListView mLista;
    private FloatingActionButton mAccedi;
    private SearchView mCerca;

    // Adapter
    private UniAdapter adapter;

    // Data Store
    private DataStore archivio = new DataStore();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        mAccedi=(FloatingActionButton)findViewById(R.id.fabAccedi);
        mLista = (ListView)findViewById(R.id.listaUniversita);
        mCerca=(SearchView)findViewById(R.id.searchView);

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
                //TODO: if logineffettuato intent intent=new intent(,BachecaActivity.class) else
                Intent intent = new Intent(view.getContext(), DettaglioUniActivity.class);
                intent.putExtra(EXTRA_UNIVERSITA, universita);
                startActivity(intent);
            }
        });


//TODO: se loggato non c'è?
        mAccedi.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), LoginActivity.class);
                startActivity(intent);
            }

        });


    }
}
