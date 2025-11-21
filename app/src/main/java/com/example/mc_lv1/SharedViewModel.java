package com.example.mc_lv1;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.LiveData;

public class SharedViewModel extends ViewModel {

    private MutableLiveData<String> stringIme = new MutableLiveData<>();
    private MutableLiveData<String> stringPrezime = new MutableLiveData<>();
    private MutableLiveData<String> stringDatumRodjenja = new MutableLiveData<>();
    private MutableLiveData<String> stringPredmet = new MutableLiveData<>();

    //Setteri
    public void setIme(String podatak)
    {
        stringIme.setValue(podatak);
    }
    public void setPrezime(String podatak)
    {
        stringPrezime.setValue(podatak);
    }
    public void setDatumRodjenja(String podatak)
    {
        stringDatumRodjenja.setValue(podatak);
    }
    public void setPredmet(String podatak)
    {
        stringPredmet.setValue(podatak);
    }

    //Getteri

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
        return stringDatumRodjenja;
    }
    public LiveData<String> getPredmet()
    {
        return stringPredmet;
    }

}
