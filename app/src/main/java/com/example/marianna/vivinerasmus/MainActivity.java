package com.example.marianna.vivinerasmus;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private Button mLogin;
    private Button mOspite;
    private Button mEntra;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        mLogin = (Button) findViewById(R.id.btnLogin);
        mOspite = (Button) findViewById(R.id.btnOspite);
        mEntra = (Button) findViewById(R.id.btnEntra);
        mEntra.setVisibility(View.GONE);

        mAuth = FirebaseAuth.getInstance();

        mOspite.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), ListaActivity.class);
                startActivity(intent);
            }

        });

        mLogin.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), LoginActivity.class);
                startActivity(intent);
            }

        });


    }

    @Override
    public void onStart() {
        super.onStart();
        // Controllo del login dello user
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            findViewById(R.id.btnLogin).setVisibility(View.GONE);
            findViewById(R.id.btnOspite).setVisibility(View.GONE);
            findViewById(R.id.btnEntra).setVisibility(View.VISIBLE);
            Toast.makeText(MainActivity.this, "Sei già autenticato. Ben tornato!", Toast.LENGTH_LONG).show();
            mEntra.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getBaseContext(), ListaActivity.class);
                    startActivity(intent);
                }
            });

        }
    }


}
