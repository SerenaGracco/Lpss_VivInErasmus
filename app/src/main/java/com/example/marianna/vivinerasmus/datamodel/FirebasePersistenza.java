package com.example.marianna.vivinerasmus.datamodel;

import com.google.firebase.database.FirebaseDatabase;

public class FirebasePersistenza extends android.app.Application {

    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }
}