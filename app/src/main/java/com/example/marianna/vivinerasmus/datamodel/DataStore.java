package com.example.marianna.vivinerasmus.datamodel;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marianna on 10/10/2018.
 */

public class DataStore {
    // Costanti
    private final static String TAG = "DataStore";
    private final static String DB_UNIVERSITA = "universita";
    private final static String KEY_INDIRIZZO = "indirizzo";
    private final static String KEY_NOME = "nome";
    private final static String KEY_SITO = "sito";
    private final static String KEY_EMAIL = "email";

    private ValueEventListener listenerUni;


    // Lista locale degli studenti
    // Todo: da eliminare
    private ArrayList<Universitas> leuniversita;

    /**
     * Costruttore
     */
    public DataStore() {
        leuniversita = new ArrayList<>();
    }

    public interface UpdateListener {
        void universitaAggiornate();
    }

    public void iniziaOsservazioneUniversita(final UpdateListener notifica) {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference(DB_UNIVERSITA);

        listenerUni = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
               leuniversita.clear();
                for (DataSnapshot elemento:dataSnapshot.getChildren()) {
                    Universitas uni = new Universitas();
                    uni.setNome(elemento.getKey());
                    uni.setIndirizzo(elemento.child(KEY_INDIRIZZO).getValue(String.class));
                    uni.setNome(elemento.child(KEY_NOME).getValue(String.class));
                    uni.setSito(elemento.child(KEY_SITO).getValue(String.class));
                    uni.setEmail(elemento.child(KEY_EMAIL).getValue(String.class));
                    leuniversita.add(uni);
                }
                notifica.universitaAggiornate();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };

        ref.addValueEventListener(listenerUni);
    }

    public void terminaOsservazioneUniversita() {
        if (listenerUni != null)
            FirebaseDatabase.getInstance().getReference(DB_UNIVERSITA).removeEventListener(listenerUni);
    }

    /**
     * Ottiene l'elenco di tutti le uni
     * Todo: Attenzione il metodo è potenzialmente pericoloso. Potrebbe restituire troppi dati!
     * @return Lista di università
     */

    public List<Universitas> elencoUni() {
        return leuniversita;
    }


    public Universitas leggiUniversita(String nome) {
        int posizione = getUniversitaIndex(nome);
        if (posizione == -1)
            return null;
        else
            return leuniversita.get(posizione);
    }



    /**
     * Restituisce il numero di università presenti nel database
     * @return numero di università
     */
    public int numeroUniversita() {
        return leuniversita.size();
    }

    /**
     * Restituisce l'indice di una università nell'array partendo dal nome
     * @param nome nome da cercare
     * @return indice del'università. -1 se non trovato
     */
    private int getUniversitaIndex(String nome) {
        boolean  trovato= false;
        int index = 0;
        while (!trovato && index < leuniversita.size()) {
            if (leuniversita.get(index).getNome().equals(nome)) {
                return index;
                //trovato=true; ????
            }
            ++index;
        }
        return -1;
    }
}
