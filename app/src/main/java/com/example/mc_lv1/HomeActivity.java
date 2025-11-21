package com.example.mc_lv1;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends BaseActivity  {

    Button oBtn;
    Spinner spinner;
    List<String> oJezici = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        oBtn = findViewById(R.id.btnHome);
        spinner = findViewById(R.id.spJezik);

        RecyclerView recyclerView = findViewById(R.id.recycleView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        StudentAdapter studentAdapter = new StudentAdapter(ApiSingleton.getInstance().getStudenti());
        recyclerView.setAdapter(studentAdapter);


        oJezici.add("Hrvatski");
        oJezici.add("Engleski");
        oJezici.add("Mađarski");


        ArrayAdapter<String> oAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, oJezici);
        oAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(oAdapter);


        String lang = getSharedPreferences("lang", MODE_PRIVATE)
                .getString("code", "hr");

        int pos;
        if (lang.equals("hr")) pos = 0;
        else if (lang.equals("en")) pos = 1;
        else pos = 2;

        spinner.setSelection(pos, false);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String code = position == 0 ? "hr"
                        : position == 1 ? "en"
                        : "hu";

                String current = getSharedPreferences("lang", MODE_PRIVATE)
                        .getString("code", "hr");

                if (code.equals(current)) {
                    return;
                }

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
                startActivity(new Intent(HomeActivity.this, CreateNewRecordActivity.class)));
    }
}
