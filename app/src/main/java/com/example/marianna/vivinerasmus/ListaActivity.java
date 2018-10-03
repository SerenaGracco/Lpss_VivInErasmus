package com.example.marianna.vivinerasmus;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

import com.example.marianna.vivinerasmus.datamodel.Universitas;
import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.FirebaseDatabase;


public class ListaActivity extends AppCompatActivity {

    private FloatingActionButton mAccedi;
    private ListView mLista;
    FirebaseListAdapter adapter;




    //creazione database realtime(delle università)
    private FirebaseDatabase unidata=FirebaseDatabase.getInstance();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);


        mAccedi=(FloatingActionButton)findViewById(R.id.fabAccedi);
        mLista=(ListView) findViewById(R.id.listView);


        FirebaseListOptions <Universitas> options = new FirebaseListOptions


        mAccedi.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), LoginActivity.class);
                startActivity(intent);
            }

        });


    }
}
