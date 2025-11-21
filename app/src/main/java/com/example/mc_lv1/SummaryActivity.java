package com.example.mc_lv1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.os.Bundle;

public class SummaryActivity extends AppCompatActivity {

    private Button oBtnKraj;
    private TextView oTxtIme;
    private TextView oTxtPrezime;
    private TextView oTxtDatum;
    private TextView oTxtProfesor;
    private TextView oTxtSatiPR;
    private TextView oTxtSatiLV;
    private TextView oIzborni;


    private TextView oTxtPredmet;
    private String sTxtIme;
    private String sTxtPredmet;
    private String sTxtPrezime;
    private String sTxtProfesor;
    private String sTxtDatum;
    private String sTxtSatiPR;
    private String sTxtSatiLV;
    private String sIzborni;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        final Bundle oExtras = getIntent().getExtras();
        sTxtIme = oExtras.getString("ime");
        oTxtIme = findViewById(R.id.txtIme);
        oTxtIme.setText(sTxtIme);

        sTxtPrezime = oExtras.getString("prezime");
        oTxtPrezime = findViewById(R.id.txtPrezime);
        oTxtPrezime.setText(sTxtPrezime);

        sTxtDatum = oExtras.getString("datum");
        oTxtDatum = findViewById(R.id.txtDatumNovi);
        oTxtDatum.setText(sTxtDatum);

        sTxtPredmet = oExtras.getString("predmet");
        oTxtPredmet = findViewById(R.id.txtDatum);
        oTxtPredmet.setText(sTxtPredmet);

        sTxtProfesor = oExtras.getString("profesor");
        oTxtProfesor = findViewById(R.id.txtProfesor);
        oTxtProfesor.setText(sTxtProfesor);

        sTxtSatiPR = oExtras.getString("satiPR");
        oTxtSatiPR = findViewById(R.id.txtSatiPR);
        oTxtSatiPR.setText(sTxtSatiPR);

        sTxtSatiLV = oExtras.getString("satiLV");
        oTxtSatiLV = findViewById(R.id.txtSatiLV);
        oTxtSatiLV.setText(sTxtSatiLV);

        sIzborni = oExtras.getString("izborni");
        oIzborni = findViewById(R.id.txtIzborni);
        oIzborni.setText(sIzborni);

        oBtnKraj = findViewById(R.id.btnKraj);

        oBtnKraj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Student noviStudent = new Student(sTxtIme, sTxtPrezime, sTxtPredmet);
                //ApiSingleton.getInstance().addStudent(noviStudent);
                startActivity(new Intent(SummaryActivity.this, HomeActivity.class));

            }
        });
    }
}