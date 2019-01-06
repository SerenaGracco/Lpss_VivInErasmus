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

public class DataStore  {
    // Costanti
    private final static String TAG = "DataStore";
    private final static String DB_UNIVERSITA = "Università";

    private ValueEventListener listenerUni;

    // Lista locale degli studenti
    private ArrayList<Universitas> leuniversita;


    public DataStore() {
        leuniversita = new ArrayList<>();
    }

    public interface UpdateListener {
        void universitaAggiornate();
    }

    public void iniziaOsservazioneUniversita(final UpdateListener notifica) {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        //databaseException: Calls to setPersistenceEnabled() must be made before any other usage of FirebaseDatabase instance.
        //ogni seconda volta che vado a listaactivity (sia che nel frattempo mi sia loggato o meno) quindi ho creato una nuova classe che fa
        //extends su application che contiene solo questo metodo:
       // database.setPersistenceEnabled(true);
        DatabaseReference ref = database.getReference(DB_UNIVERSITA);

        listenerUni = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
               leuniversita.clear();
                for (DataSnapshot elemento:dataSnapshot.getChildren()) {
                    Universitas uni = new Universitas();
                    uni = elemento.getValue(Universitas.class);
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
     * @return Lista di università
     */
    public List<Universitas> elencoUni() {
        return leuniversita;
    }
    /**
     * Aggiunge una uni
     * @param universita uni da aggiungere
     */
    public void aggiungiUni(Universitas universita) {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(DB_UNIVERSITA).child(universita.getIDuni());
        ref.setValue(universita);
        /*ref.child("Nome").setValue(universita.getnome());
        ref.child(KEY_NOME).setValue(studente.getNome());
        ref.child(KEY_CREDITI).setValue(studente.getCrediti());
        */
    }

    /**
     * Aggiorna i dati del'uni utilizzando uniID come riferimento
     * @param universita dati da aggiornare
     */
    public void aggiornaStudente(Universitas universita) {
        int posizione = getUniversitaIndex(universita.getIDuni());
        if (posizione == -1)
            aggiungiUni(universita);
        else
            leuniversita.set(posizione, universita);
    }

    /**
     * Elimina una uni
     */
    public void eliminaUni(String IDuni) {
        int posizione = getUniversitaIndex(IDuni);
        if (posizione != -1)
            leuniversita.remove(posizione);
    }


    public Universitas leggiUniversita(String nome) {
        int posizione = getUniversitaIndex(nome);
        if (posizione == -1)
            return null;
        else
            return leuniversita.get(posizione);
    }

    /**
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
