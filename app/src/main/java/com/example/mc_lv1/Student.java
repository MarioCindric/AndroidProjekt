package com.example.mc_lv1;

import android.net.Uri;

public class Student {
    private String ime;
    private String prezime;
    private String predmet;

    private Uri slika;

    public Student(String ime, String prezime, String predmet, Uri slika)
    {
        this.ime = ime;
        this.prezime = prezime;
        this.predmet = predmet;
        this.slika = slika;
    }

    public String getIme(){return ime;}
    public String getPrezime(){return prezime;}
    public String getPredmet(){return predmet;}
    public Uri getSlika(){return slika;}
}
