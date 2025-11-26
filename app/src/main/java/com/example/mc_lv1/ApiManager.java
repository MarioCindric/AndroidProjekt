package com.example.mc_lv1;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiManager {
    static ApiManager instance;
    private ProfesoriInterface profesoriInterface;
    private ApiManager(){
        Retrofit.Builder builder = new Retrofit.Builder();
//postavljanje retrofit-a
        Retrofit retrofit = builder.baseUrl("http://31.147.206.25/racunarstvo_android/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        profesoriInterface = retrofit.create(ProfesoriInterface.class);
    }
    public static ApiManager getInstance(){
        if (instance == null){
            instance = new ApiManager();
        }
        return instance;
    }
    public ProfesoriInterface profesoriInterface(){
        return profesoriInterface;
    }
}
