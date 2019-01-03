package com.example.marianna.vivinerasmus;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.marianna.vivinerasmus.datamodel.DataStore;
import com.example.marianna.vivinerasmus.datamodel.Universitas;


public class ListaActivity extends AppCompatActivity {

    private FloatingActionButton mAccedi;
    private ListView mLista;
    // Costanti
    private final static String EXTRA_UNIVERSITA = "universita";
    //private final static String TAG = "VivInErasmus";

    // Widget
    private ListView listaUniversita;

    // Adapter
    private UniAdapter adapter;

    // Data Store
    private DataStore archivio = new DataStore();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        mAccedi=(FloatingActionButton)findViewById(R.id.fabAccedi);
        listaUniversita = (ListView)findViewById(R.id.listaUniversita);

        adapter = new UniAdapter(this);
        //adapter.update(archivio.elencoUni());
        archivio.iniziaOsservazioneUniversita(new DataStore.UpdateListener() {
            @Override
            public void universitaAggiornate() {
                adapter.update(archivio.elencoUni());
            }
        });

        listaUniversita.setAdapter(adapter);

        listaUniversita.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Universitas universita = adapter.getItem(position);
                //TODO: if logineffettuato intent intent=new intent(,BachecaActivity.class) else
                Intent intent = new Intent(view.getContext(), DettaglioUniActivity.class);
                intent.putExtra(EXTRA_UNIVERSITA, universita);
                startActivity(intent);
            }
        });

        //FirebaseListOptions <Universitas> options = new FirebaseListOptions


        mAccedi.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), LoginActivity.class);
                startActivity(intent);
            }

        });


    }
}
