package com.example.mc_lv1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

// STUDENT ADAPTER - POVEZUJE PODATKE I LAYOUT, INFLATA, UZIMA PODATKE IZ LISTE I PUNI view-holder polja.
public class StudentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private List<Student> studenti;
    private List<Student> filtriraniStudenti;


    private static final int VIEW_TYPE_HEADER = 0;

    private static final int VIEW_TYPE_STUDENT = 1;
    public StudentAdapter(List<Student> studenti) {
        this.studenti = studenti;
        // Kopija originalne liste
        this.filtriraniStudenti = new ArrayList<>(studenti);
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return VIEW_TYPE_HEADER;
        } else {
            return VIEW_TYPE_STUDENT;
        }
    }

    @NotNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_HEADER) {
            View headerView = LayoutInflater.from(parent.getContext()).inflate(R.layout.header_layout, parent, false);
            return new HeaderViewHolder(headerView);
        }else{
            View studentView = LayoutInflater.from(parent.getContext()).inflate(R.layout.student_item, parent, false);
            return new StudentViewHolder(studentView);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
       if(holder instanceof  StudentViewHolder)
       {
           // Koristim filtiranu listu radi manipulacije s prikazom
           StudentViewHolder studentViewHolder = (StudentViewHolder) holder;
           Student student = filtriraniStudenti.get(position - 1);
           int redniBroj = position;
           studentViewHolder.studentTextView.setText(student.getIme() + " " + student.getPrezime());
           studentViewHolder.predmetTextView.setText(student.getPredmet());
           studentViewHolder.studentRedniBroj.setText(String.valueOf(redniBroj) + ".");



           Glide.with(holder.itemView.getContext())
                   .load(student.getSlika())
                   .into(studentViewHolder.studentSlika);

       }

       if(holder instanceof  HeaderViewHolder)
       {
           HeaderViewHolder headerViewHolder = (HeaderViewHolder) holder;
           headerViewHolder.txtHeader.setText(R.string.headerStudenti);
       }
    }

    public class StudentViewHolder extends RecyclerView.ViewHolder {
        TextView studentTextView;
        TextView predmetTextView;
        TextView studentRedniBroj;
        ImageView studentSlika;

        public StudentViewHolder(View itemView) {
            super(itemView);

            studentTextView = itemView.findViewById(R.id.studentImePrezime);
            predmetTextView = itemView.findViewById(R.id.studentPredmet);
            studentRedniBroj= itemView.findViewById(R.id.Rbr);
            studentSlika = itemView.findViewById(R.id.studentSlika);

        }
    }

    private class HeaderViewHolder extends RecyclerView.ViewHolder{
        TextView txtHeader;

        HeaderViewHolder(View view){
            super(view);
            txtHeader = view.findViewById(R.id.header);
        }
    }

    @Override
    public int getItemCount() {
        //return studenti.size() + 1;
        return filtriraniStudenti.size() + 1;
    }

    // Funkcija za filtriranje studenata
    public void filter(String text)
    {
        filtriraniStudenti.clear();

        if(text == null || text.trim().isEmpty())
        {
            filtriraniStudenti.addAll(studenti);
        }
        else
        {
            String query = text.toLowerCase();

            for(Student s : studenti)
            {
                if(s.getIme().toLowerCase().contains(query) || s.getPrezime().toLowerCase().contains(query)
                || s.getPredmet().toLowerCase().contains(query))
                {
                    filtriraniStudenti.add(s);
                }
            }
        }
        notifyDataSetChanged();

    }




}

