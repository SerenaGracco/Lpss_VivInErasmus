package com.example.marianna.vivinerasmus;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.marianna.vivinerasmus.datamodel.Universitas;

/**
 * Created by Marianna on 11/10/2018.
 */

public class DettaglioUniActivity extends AppCompatActivity {

    // Costanti
    private final static String EXTRA_UNIVERSITA = "universita";



    @Override
    protected void onCreate(Bundle savedInstanceState) { // Widget
        /*private*/ TextView mNome;
        /*private*/ TextView mIndirizzo;
       /* private*/ TextView mSito;
        /*private*/ TextView mEmail;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dettaglio_uni);

        // Imposto gli id widget
        mNome = findViewById(R.id.textNomeUni);
        mIndirizzo = /*(TextView)*/findViewById(R.id.textIndirizzoUni);
        mSito = findViewById(R.id.textSitoUni);
        mEmail = findViewById(R.id.textEmailUni);

        // Ottengo i dati passati ed eventualmente visualizzo l'uni
        Intent intent = getIntent();
        Universitas universita = (Universitas) intent.getSerializableExtra(EXTRA_UNIVERSITA);

        if (universita != null) {
            mIndirizzo.setText(universita.getIndirizzo());
            mSito.setText(universita.getSito());
            mNome.setText(universita.getNome());
            mEmail.setText(universita.getEmail());
           
            //TODO: pulsante per eliminare l'uni (se sono stato io a crearla)
        }
    }
}
