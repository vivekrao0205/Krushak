package com.acdevs.krushak.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.acdevs.krushak.R;

import java.util.Locale;

public class LanguageSettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile); // Correct layout file

        Button btnEnglish = findViewById(R.id.btn_english);
        Button btnTelugu = findViewById(R.id.btn_telugu);

        btnEnglish.setOnClickListener(v -> setLocale("en"));
        btnTelugu.setOnClickListener(v -> setLocale("te"));
    }

    private void setLocale(String languageCode) {
        Locale locale = new Locale(languageCode);
        Locale.setDefault(locale);

        // Update the resources configuration
        Resources resources = getResources();
        Configuration configuration = new Configuration(resources.getConfiguration());
        configuration.setLocale(locale);

        // Apply the new configuration
        resources.updateConfiguration(configuration, resources.getDisplayMetrics());

        // Save the selected language to SharedPreferences
        SharedPreferences.Editor editor = getSharedPreferences("AppSettings", MODE_PRIVATE).edit();
        editor.putString("language", languageCode);
        editor.apply();

        // Restart activity to apply language changes
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }
}
