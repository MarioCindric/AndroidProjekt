package com.example.mc_lv1;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.os.Bundle;
import android.content.Intent;
import android.widget.Toast;


import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class PersonalInfoActivity extends AppCompatActivity {
    private Button oBtnPrvi;
    private TextInputEditText oIme;
    private TextInputEditText oPrezime;
    private TextInputEditText oDatum;
    private String sIme;
    private String sPrezime;
    private String sDatum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_info);

        oBtnPrvi = findViewById(R.id.btnStInfo);
        oIme = findViewById(R.id.unosIme);
        oPrezime = findViewById(R.id.unosPrezime);
        oDatum = findViewById(R.id.unosDatum);

        oDatum.setOnLongClickListener((View.OnLongClickListener) v ->{
            MaterialDatePicker<Long> materialDatePicker = MaterialDatePicker.Builder.datePicker()
                    .setTitleText("Odaberite datum")
                    .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                    .build();
            materialDatePicker.show(PersonalInfoActivity.this.getSupportFragmentManager(), "DATE_PICKER");
            materialDatePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener<Long>(){
                @Override
                public void onPositiveButtonClick(Long selection){
                    String date = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()).format(new Date(selection));
                    oDatum.setText(date);
                }

            });
            return false;
        });



        oBtnPrvi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sIme = oIme.getText().toString();
                sPrezime = oPrezime.getText().toString();
                sDatum = oDatum.getText().toString();

                Intent studentActivity  = new Intent(getApplicationContext(), StudentInfoActivity.class);
                studentActivity.putExtra("ime", sIme);
                studentActivity.putExtra("prezime", sPrezime);
                studentActivity.putExtra("datum", sDatum);

                if(!sIme.isEmpty())
                {
                    startActivity(studentActivity);
                }
                else
                {
                    Toast.makeText(PersonalInfoActivity.this, "Ime je prazno", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}