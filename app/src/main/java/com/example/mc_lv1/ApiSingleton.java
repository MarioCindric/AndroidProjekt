package com.example.mc_lv1;

import java.util.ArrayList;
import java.util.List;

public class ApiSingleton {
    private static ApiSingleton oInstance;
    public List<Student> studenti = new ArrayList<>();

    public static ApiSingleton getInstance() {
        if(oInstance == null) {
            oInstance = new ApiSingleton();
        }
        return oInstance;
    }

    public List<Student> getStudenti()
    {
        return studenti;
    }

    public void addStudent(Student student)
    {
        studenti.add(student);
    }
}
