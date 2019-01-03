package com.example.marianna.vivinerasmus.datamodel;
import java.io.Serializable;
/**
 * Created by Serena on 24/04/2018.
 */

public class Universitas implements Serializable{

    private String Indirizzo;
    private String Nome;
    private String Sito;
    private String Email;

    public Universitas(){

    }

    public Universitas (String Indirizzo, String Nome, String Sito, String Email){
        this.Indirizzo = Indirizzo;
        this.Nome = Nome;
        this.Sito = Sito;
        this.Email = Email;
    }

    public String getIndirizzo(){return Indirizzo;}
    public void setIndirizzo (String Indirizzo) {this.Indirizzo = Indirizzo;}

    public String getNome () {return Nome;}
    public void setNome (String Nome) {this.Nome = Nome;}

    public String getSito () {return Sito;}
    public void setSito (String Sito) {this.Sito = Sito;}

    public String getEmail () {return Email;}
    public void setEmail (String Email) {this.Email = Email;}



}
