package com.example.marianna.vivinerasmus;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

import com.google.firebase.database.FirebaseDatabase;


public class ListaActivity extends AppCompatActivity {

    private FloatingActionButton mAccedi;
    private ListView mLista;

    //creazione database realtime(delle università)
    private FirebaseDatabase unidata=FirebaseDatabase.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        mAccedi=(FloatingActionButton)findViewById(R.id.fabAccedi);
        mLista=(ListView) findViewById(R.id.listView);

        mAccedi.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), LoginActivity.class);
                startActivity(intent);
            }

        });


    }
}
