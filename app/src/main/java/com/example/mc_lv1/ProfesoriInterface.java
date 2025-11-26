package com.example.mc_lv1;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
public interface ProfesoriInterface {
    @GET("read_teachers.php")
    Call<List<Profesor>> getProfesori();
}
