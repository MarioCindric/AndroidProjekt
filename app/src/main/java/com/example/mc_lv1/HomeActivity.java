package com.example.mc_lv1;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Spinner;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends BaseActivity  {

    Button oBtn;
    Spinner spinner;
    List<String> oJezici = new ArrayList<>();

    private SearchView searchView;
    private Uri uSlika = Uri.parse("");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        oBtn = findViewById(R.id.btnHome);
        spinner = findViewById(R.id.spJezik);
        searchView = findViewById(R.id.searchBar);
        //searchView.clearFocus();




        // Slika
        uSlika = Uri.parse("android.resource://"
                + getPackageName()
                + "/"
                + R.drawable.noimage);


        // Ako nema if-a onda se studenti nadodaju kod promjene jezika
        if (ApiSingleton.getInstance().getStudenti().isEmpty()) {
            Student student = new Student("marko", "markic", "PMA", uSlika);
            Student student2 = new Student("ivana", "ivankovic ivanic", "PMA", uSlika);
            Student student3 = new Student("marko", "markic", "PMA", uSlika);
            Student student4 = new Student("ivana", "ivankovic ivanic", "Matematika", uSlika);
            ApiSingleton.getInstance().addStudent(student);
            ApiSingleton.getInstance().addStudent(student2);
            ApiSingleton.getInstance().addStudent(student3);
            ApiSingleton.getInstance().addStudent(student4);
        }

        // Recycler view i searchView
        RecyclerView recyclerView = findViewById(R.id.recycleView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        StudentAdapter studentAdapter = new StudentAdapter(ApiSingleton.getInstance().getStudenti());
        recyclerView.setAdapter(studentAdapter);



       searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
           @Override
           public boolean onQueryTextSubmit(String query) {
               studentAdapter.filter(query);
               return true;
           }

           @Override
           public boolean onQueryTextChange(String newText) {
              studentAdapter.filter(newText);
              return true;
           }
       });



        // Postavljanje spinnera
        String lang = getSharedPreferences("lang", MODE_PRIVATE)
                .getString("code", "hr");

        oJezici.clear();

        // Jezici
        if (lang.equals("hr")) {
            oJezici.add("Hrvatski");
            oJezici.add("Engleski");
            oJezici.add("Mađarski");
        }
        else if (lang.equals("en")) {
            oJezici.add("Croatian");
            oJezici.add("English");
            oJezici.add("Hungarian");
        }
        else if (lang.equals("hu")) {
            oJezici.add("Horvát");
            oJezici.add("Angol");
            oJezici.add("Magyar");
        }

        ArrayAdapter<String> oAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, oJezici);
        oAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(oAdapter);




        int pos;
        if (lang.equals("hr")) pos = 0;
        else if (lang.equals("en")) pos = 1;
        else pos = 2;

        // Prehodno stavljam poziciju jer setSelection prima int vrijednost a ne string
        spinner.setSelection(pos, false);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                // Pretvara poziciju u code
                String code = position == 0 ? "hr"
                        : position == 1 ? "en"
                        : "hu";


                // trenutni kod
                String current = getSharedPreferences("lang", MODE_PRIVATE)
                        .getString("code", "hr");

                // ako je isti kao trenutni onda ništa ne radi
                if (code.equals(current)) {
                    return;
                }


                // Spremanje jezika i resetiranje activitija
                getSharedPreferences("lang", MODE_PRIVATE)
                        .edit()
                        .putString("code", code)
                        .apply();

                finish();
                startActivity(getIntent());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });




        oBtn.setOnClickListener(v ->
                //startActivity(new Intent(HomeActivity.this, CreateNewRecordActivity.class)));
                startActivity(new Intent(HomeActivity.this, PersonalInfoActivity.class)));
    }


}
