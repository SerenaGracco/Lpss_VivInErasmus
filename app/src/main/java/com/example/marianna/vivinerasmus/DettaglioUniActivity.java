package com.example.marianna.vivinerasmus;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
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
        Button mVai;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dettaglio_uni);

        // Imposto gli id widget
        mNome = findViewById(R.id.textView1);
        mIndirizzo = /*(TextView)*/findViewById(R.id.textView2);
        mSito = findViewById(R.id.textView3);
        mEmail = findViewById(R.id.textView4);
        mVai =findViewById(R.id.btnVai);

        // Ottengo i dati passati ed eventualmente visualizzo l'uni
        Intent intent = getIntent();
        Universitas universita = (Universitas) intent.getSerializableExtra(EXTRA_UNIVERSITA);

        if (universita != null) {
            mIndirizzo.setText(universita.getIndirizzo());
            mSito.setText(universita.getSito());
            mNome.setText(universita.getNome());
            mEmail.setText(universita.getEmail());
            //TODO: anche immagine

            mVai.setOnClickListener(new View.OnClickListener() {

                public void onClick(View v) {
                    //TODO:if logineffettuato, else intent a login
                     Intent intent2 = new Intent(getBaseContext(), BachecaActivity.class);
                    //intent2.putExtra(EXTRA_UNIVERSITA, universita);
                    startActivity(intent2);
                }
            });
        }
    }
}
