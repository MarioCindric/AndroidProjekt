package com.example.mc_lv1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.os.Bundle;
import android.widget.Switch;
import android.widget.Toast;
import com.google.android.material.textfield.TextInputEditText;

public class StudentInfoActivity extends AppCompatActivity {

    String [] predmeti = {"Matematika", "PMA", "WPSP", "SPJ", "E-learning"};
    private Button oBtnDrugi;
    private AutoCompleteTextView autoView;
    private TextInputEditText oProfesor;
    private TextInputEditText oSatiPR;
    private TextInputEditText oSatiLV;
    private Switch oIzborni;
    private String sIzborni;
    private String sPredmet;
    private String sIme;
    private String sPrezime;
    private String sDatum;
    private String sProfesor;
    private String sSatiPR;
    private String sSatiLV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_info);
        ArrayAdapter<String> adapter = new ArrayAdapter<>
                (this,android.R.layout.simple_dropdown_item_1line,predmeti);
        autoView = findViewById(R.id.autoPredmet);
        autoView.setThreshold(2);
        autoView.setAdapter(adapter);

        oProfesor = findViewById(R.id.unosProfesor);
        oSatiPR = findViewById(R.id.unosSatiPR);
        oSatiLV = findViewById(R.id.unosSatiLV);
        oIzborni = findViewById(R.id.switchIzborni);

        oBtnDrugi = findViewById(R.id.btnSum);


        oBtnDrugi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                final Bundle oExtras = getIntent().getExtras();
                sIme = oExtras.getString("ime");
                sPrezime = oExtras.getString("prezime");
                sDatum = oExtras.getString("datum");

                sProfesor = oProfesor.getText().toString();
                sSatiLV = oSatiLV.getText().toString();
                sSatiPR = oSatiPR.getText().toString();





                sPredmet = autoView.getText().toString();
                Intent summaryAct  = new Intent(getApplicationContext(), SummaryActivity.class);
                summaryAct.putExtra("ime", sIme);
                summaryAct.putExtra("prezime", sPrezime);
                summaryAct.putExtra("datum", sDatum);
                summaryAct.putExtra("predmet", sPredmet);
                summaryAct.putExtra("profesor", sProfesor);
                summaryAct.putExtra("satiPR", sSatiPR);
                summaryAct.putExtra("satiLV", sSatiLV);
                if(oIzborni.isChecked())
                {
                    sIzborni = "Predmet je izborni";
                    summaryAct.putExtra("izborni", sIzborni);
                }
                else
                {
                    sIzborni = "Predmet je obvezan";
                    summaryAct.putExtra("izborni", sIzborni);
                }

                if(!sPredmet.isEmpty())
                {
                    startActivity(summaryAct);
                }
                else
                {
                    Toast.makeText(StudentInfoActivity.this, "Predmet nije upisan", Toast.LENGTH_SHORT).show();
                }
            }
        });



    }
}