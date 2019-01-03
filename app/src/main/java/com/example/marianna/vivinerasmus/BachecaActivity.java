package com.example.marianna.vivinerasmus;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TabHost;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class BachecaActivity extends AppCompatActivity implements OnMapReadyCallback {

    private TabHost mHost;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bacheca);



        mHost=findViewById(R.id.tabHost);
        mHost.setup();
//errore fragment inflater
        MapFragment mapFragment =
                (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        /*TabHost :
        * - TabHost.TabSpec (classe):A tab has a tab indicator, content, and a tag that is used to keep track of it.
        * - TabHost.OnTabChangeListener (interfaccia): Interface definition for a callback to be invoked when tab changed
          - TabHost.TabContentFactory (interfaccia): Makes the content of a tab when it is selected.
        */
        //Tab 1
        TabHost.TabSpec spec=mHost.newTabSpec("tab1");
        spec.setContent(R.id.tabAnnunci);
        spec.setIndicator("Annunci");
        mHost.addTab(spec);
        //Tab 2
        spec=mHost.newTabSpec("tab2");
        spec.setContent(R.id.tabEventi);
        spec.setIndicator("Annunci");
        mHost.addTab(spec);
        //Tab 3
        spec=mHost.newTabSpec("tab3");
        spec.setContent(R.id.map);
        spec.setIndicator("Mappa");
        mHost.addTab(spec);


    }

    public void onMapReady(GoogleMap map) {
        map.addMarker(new MarkerOptions().position(new LatLng(0, 0)).title("Marker"));
    }

}
