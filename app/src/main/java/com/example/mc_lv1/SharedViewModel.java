package com.example.mc_lv1;

import android.net.Uri;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.LiveData;

public class SharedViewModel extends ViewModel {

    private MutableLiveData<String> stringIme = new MutableLiveData<>();
    private MutableLiveData<String> stringPrezime = new MutableLiveData<>();
    private MutableLiveData<String> stringDatumRodjenja = new MutableLiveData<>();
    private MutableLiveData<String> stringPredmet = new MutableLiveData<>();
    private MutableLiveData<Uri> UriSlika = new MutableLiveData<>();
    private MutableLiveData<Integer> intId = new MutableLiveData<>();
    private MutableLiveData<String> stringDatum = new MutableLiveData<>();

    private final MutableLiveData<Profesor> profesor = new MutableLiveData<>();

    //Setteri
    public void setIme(String podatak)
    {
        stringIme.setValue(podatak);
    }
    public void setPrezime(String podatak)
    {
        stringPrezime.setValue(podatak);
    }
    public void setPredmet(String podatak)
    {
        stringPredmet.setValue(podatak);
    }
    public void setUriSlika(Uri slika){UriSlika.setValue(slika);}
    public void setProfesor(Profesor p) {
        profesor.setValue(p);
    }
    public void setId(Integer i){intId.setValue(i);}
    public void setDatum(String podatak){stringDatum.setValue(podatak);}


    //Getteri

    public LiveData<Profesor> getProfesor() {
        return profesor;
    }
    public LiveData<String> getIme()
    {
        return stringIme;
    }
    public LiveData<String> getPrezime()
    {
        return stringPrezime;
    }
    public LiveData<String> getDatum()
    {
        return stringDatum;
    }
    public LiveData<String> getPredmet()
    {
        return stringPredmet;
    }
    public LiveData<Uri> getSlika(){return UriSlika;}
    public LiveData<Integer> getId(){return intId;}


}
