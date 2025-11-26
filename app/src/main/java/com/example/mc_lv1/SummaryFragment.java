package com.example.mc_lv1;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;


public class SummaryFragment extends Fragment {
    private TextView oTxtIme;
    private TextView oTxtPrezime;
    private TextView oTxtDatum;
    private TextView oTxtPredmet;
    private TextView oTxtProfesor;
    private ImageView oSlikaProfesor;

    private String sTxtIme = "";
    private String sTxtPrezime = "";
    private String sTxtDatum = "";
    private String sTxtPredmet = "";
    private String sTxtProfesor = "";
    private String sImgProfesor ="";

    private Uri uSlika = Uri.parse("");

    private Button oBtn;
    private SharedViewModel sharedViewModel;


    public SummaryFragment() {
// Required empty public constructor
}


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View view= inflater.inflate(R.layout.fragment_summary, container, false);

       oBtn = view.findViewById(R.id.btnKraj);
       oTxtIme = view.findViewById(R.id.txtIme);
       oTxtPrezime = view.findViewById(R.id.txtPrezime);
       oTxtDatum = view.findViewById(R.id.txtDatumNovi);
       oTxtPredmet = view.findViewById(R.id.txtPredmet);
       oTxtProfesor = view.findViewById(R.id.txtProfesor);
       oSlikaProfesor = view.findViewById(R.id.imgProfesor);

       oTxtIme.setText("");
       sharedViewModel.getIme().observe(getViewLifecycleOwner(), novoIme ->{
           sTxtIme = novoIme;
           oTxtIme.setText(novoIme);
       });


        oTxtPrezime.setText("");
        sharedViewModel.getPrezime().observe(getViewLifecycleOwner(), novoPrezime ->{
            sTxtPrezime= novoPrezime;
            oTxtPrezime.setText(novoPrezime);
        });



        sharedViewModel.getDatum().observe(getViewLifecycleOwner(), noviDatum ->{
            sTxtDatum = noviDatum;
            oTxtDatum.setText(noviDatum);
        });


        oTxtPredmet.setText("");
        sharedViewModel.getPredmet().observe(getViewLifecycleOwner(), noviPredmet ->{
            sTxtPredmet = noviPredmet;
            oTxtPredmet.setText(noviPredmet);
        });

        oTxtProfesor.setText("");
        sharedViewModel.getProfesor().observe(getViewLifecycleOwner(), noviProfesor -> {
                sTxtProfesor = noviProfesor.getIme() + " " + noviProfesor.getPrezime();
                oTxtProfesor.setText(sTxtProfesor);

            Picasso.get()
                    .load(noviProfesor.getProfilna())
                    .placeholder(R.drawable.ic_launcher_background)    // opcionalno
                    .error(R.drawable.ic_launcher_foreground)           // opcionalno
                    .into(oSlikaProfesor);
        });






        sharedViewModel.getSlika().observe(getViewLifecycleOwner(), novaSlika -> {
            uSlika = novaSlika;
        });

        oBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(sTxtIme.isEmpty() || sTxtPrezime.isEmpty() ||  sTxtPredmet.isEmpty())
                {
                    Toast.makeText(getActivity(), "Unesite sve podatke", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Student noviStudent = new Student(sTxtIme, sTxtPrezime, sTxtPredmet, uSlika);
                    ApiSingleton.getInstance().addStudent(noviStudent);
                    startActivity(new Intent(getActivity(), HomeActivity.class));
                }
            }
        });
       return view;
    }
}