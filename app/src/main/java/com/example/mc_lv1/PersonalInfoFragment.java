package com.example.mc_lv1;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class PersonalInfoFragment extends Fragment {

    private TextInputEditText oIme;
    private TextInputEditText oPrezime;
    private TextInputEditText oDatum;
    private SharedViewModel sharedViewModel;
    private ImageView imageView;
    public PersonalInfoFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_personal_info, container, false);

        oIme = view.findViewById(R.id.unosIme);
        oPrezime = view.findViewById(R.id.unosPrezime);
        oDatum = view.findViewById(R.id.unosDatum);
        imageView = view.findViewById(R.id.imgSlika);
        imageView.setOnClickListener(v -> openCamera());

        oDatum.setOnLongClickListener((View.OnLongClickListener) v ->{
            MaterialDatePicker<Long> materialDatePicker = MaterialDatePicker.Builder.datePicker()
                    .setTitleText("Odaberite datum")
                    .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                    .build();
            materialDatePicker.show(getParentFragmentManager(), "DATE_PICKER");
            materialDatePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener<Long>(){
                @Override
                public void onPositiveButtonClick(Long selection){
                    String date = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()).format(new Date(selection));
                    Log.d("DATUM_TEST", "DOBIVENI_DATUM = " + date);
                    oDatum.setText(date);
                    sharedViewModel.setDatum(date);
                    Log.d("DATUM_TEST", "EDITTEXT_NAKON_SET = [" + oDatum.getText() + "]");
                }

            });
            return false;
        });

        oIme.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                sharedViewModel.setIme(s.toString());

            }
        });

        oPrezime.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                sharedViewModel.setPrezime(s.toString());
            }
        });


        return view;
    }

    // Otvaram kameru, pokrece custom camera activity
    private void openCamera() {
        Intent intent = new Intent(getContext(), CustomCameraActivity.class);
        startActivityForResult(intent, 100);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 100 && resultCode == getActivity().RESULT_OK) {
            // Putanja slike
            String photoPath = data.getStringExtra("photo_path");
            // Ako postoji, sprema se kao uri i ide u sharedViewModel za dohvacanje
            if (photoPath != null) {
                Uri photoUri = Uri.parse(photoPath);
                imageView.setImageURI(photoUri);
                // Spremam u sharedViewModel
                sharedViewModel.setUriSlika(photoUri);
            } else {
                Toast.makeText(getContext(), "No photo captured", Toast.LENGTH_SHORT).show();
            }
        }
    }
}