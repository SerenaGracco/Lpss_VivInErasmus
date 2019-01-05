package com.example.marianna.vivinerasmus;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.marianna.vivinerasmus.datamodel.Universitas;

import java.util.Collections;
import java.util.List;

/**
 * Created by Marianna on 10/10/2018.
 */

public class UniAdapter extends BaseAdapter{

    private List<Universitas> listaUniversita = Collections.emptyList();
    private Context context;

    /**
     * Costruttore
     * @param context contesto da utilizzare
     */
    public UniAdapter(Context context) {
        this.context = context;
    }

    public void update(List<Universitas> newList) {
        listaUniversita = newList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return listaUniversita.size();
    }

    @Override
    public Universitas getItem(int position) {
        return listaUniversita.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null)
            //creiamo una view se quella da riciclare è null
            convertView = LayoutInflater.from(context).inflate(R.layout.universita, parent, false);

        // Ottengo gli id correnti
        TextView textIndirizzo = (TextView)convertView.findViewById(R.id.textIndirizzo);
        TextView textNome = (TextView)convertView.findViewById(R.id.textNome);



        // Imposto i valori da visualizzare
        Universitas universitas = listaUniversita.get(position);
        textNome.setText(universitas.getNome());
        textIndirizzo.setText(universitas.getIndirizzo());
        // TODO: 10/10/2018 immagine e ranking???

        //su firebase immagine: url comune/nomeimmagine.jpg

        return convertView;
    }
}
