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

    // Widget
    private TextView mNome;
    private TextView mIndirizzo;
    private TextView mSito;
    private TextView mEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dettaglio_uni);

        // Imposto gli id widget
        mNome = (TextView)findViewById(R.id.textNome);
        mIndirizzo = (TextView)findViewById(R.id.textIndirizzo);
        mSito = (TextView)findViewById(R.id.textSito);
        mEmail = (TextView)findViewById(R.id.textEmail);

        // Ottengo i dati passati ed eventualmente visualizzo lo studente
        Intent intent = getIntent();
        Universitas universita = (Universitas) intent.getSerializableExtra(EXTRA_UNIVERSITA);

        if (universita != null) {
            mIndirizzo.setText(universita.getIndirizzo());
            mSito.setText(universita.getSito());
            mNome.setText(universita.getNome());
            mEmail.setText(universita.getEmail());
        }
    }
}
