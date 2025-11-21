package com.example.mc_lv1;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.google.android.material.textfield.TextInputEditText;


public class StudentInfoFragment extends Fragment {

    private AutoCompleteTextView oPredmet;


    String [] predmeti = {"Matematika", "PMA", "WPSP", "SPJ", "E-learning"};

    private SharedViewModel sharedViewModel;
    public StudentInfoFragment() {
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




        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_student_info, container, false);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                requireContext(),
                android.R.layout.simple_dropdown_item_1line,
                predmeti);

        oPredmet = view.findViewById(R.id.autoPredmet);
        oPredmet.setThreshold(2);
        oPredmet.setAdapter(adapter);

        //oPredmet = view.findViewById(R.id.autoPredmet);
        oPredmet.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                sharedViewModel.setPredmet(s.toString());

            }
        });
        return view;
    }
}