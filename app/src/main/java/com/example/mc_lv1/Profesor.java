package com.example.mc_lv1;

import com.google.gson.annotations.SerializedName;

public class Profesor {

    @SerializedName("id")
    private String id;

    @SerializedName("ime")
    private String ime;

    @SerializedName("prezime")
    private String prezime;

    @SerializedName("email")
    private String email;

    @SerializedName("kabinet")
    private String kabinet;

    @SerializedName("titula")
    private String titula;

    @SerializedName("akademsko_zvanje")
    private String akademskoZvanje;

    @SerializedName("profilna")
    private String profilna;

    public Profesor() { }

    public String getIme() { return ime; }
    public String getPrezime() { return prezime; }
    public String getProfilna(){return profilna;}


}

