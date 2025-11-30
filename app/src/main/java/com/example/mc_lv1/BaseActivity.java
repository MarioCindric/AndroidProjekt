package com.example.mc_lv1;

import android.content.Context;
import android.content.res.Configuration;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

// Abstraktna klasa jer se ne instancira, svi drugi activitiju ju nasljeđuju
// zbog mjenjanja jezika, da se program ne crasha
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void attachBaseContext(Context base) {
        // Po defaultu hrvatski, stavlja jezik aplikacije
        String lang = base.getSharedPreferences("lang", MODE_PRIVATE)
                .getString("code", "hr");
        super.attachBaseContext(applyLocale(base, lang));
    }


    private Context applyLocale(Context context, String lang) {
        Locale locale = new Locale(lang); // jezik
        Locale.setDefault(locale); //stavlja se kao jezik aplikacije

        Configuration config = context.getResources().getConfiguration();
        config.setLocale(locale);

        return context.createConfigurationContext(config);
    }
}
