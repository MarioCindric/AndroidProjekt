package com.example.mc_lv1;

import android.net.Uri;

public class Student {

    private int id;
    private String ime;
    private String prezime;
    private String predmet;
    private String datum;
    private Uri slika;

    public Student(String ime, String prezime, String predmet, Uri slika, String datum)
    {
        this.ime = ime;
        this.prezime = prezime;
        this.predmet = predmet;
        this.slika = slika;
        this.datum = datum;

    }

    public String getIme(){return ime;}
    public String getPrezime(){return prezime;}
    public String getPredmet(){return predmet;}
    public Uri getSlika(){return slika;}
    public String getDatum(){return datum;}


}
