package com.example.mc_lv1;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Spinner;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class StudentInfoFragment extends Fragment {

    private AutoCompleteTextView oPredmet;


    private Spinner spinnerProfesori;

    private List<Profesor> profesori = new ArrayList<>();
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

        spinnerProfesori = view.findViewById(R.id.spinnerProfesor);
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

            ApiManager.getInstance()
                    .profesoriInterface()
                    .getProfesori()
                    .enqueue(new Callback<List<Profesor>>() {
                        @Override
                        public void onResponse(Call<List<Profesor>> call, Response<List<Profesor>> response) {
                            if (response.isSuccessful() && response.body() != null) {

                                profesori.clear();
                                profesori.addAll(response.body());

                                List<String> imena = new ArrayList<>();
                                for (Profesor p : profesori) {
                                    imena.add(p.getIme() + " " + p.getPrezime());
                                }

                                ArrayAdapter<String> adapter = new ArrayAdapter<>(
                                        getContext(),
                                        android.R.layout.simple_spinner_item,
                                        imena
                                );
                                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                spinnerProfesori.setAdapter(adapter);
                            }
                        }

                        @Override
                        public void onFailure(Call<List<Profesor>> call, Throwable t) {
                            t.printStackTrace();
                        }
                    });
        spinnerProfesori.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Profesor p = profesori.get(position);
                sharedViewModel.setProfesor(p);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });


        return view;
    }
}