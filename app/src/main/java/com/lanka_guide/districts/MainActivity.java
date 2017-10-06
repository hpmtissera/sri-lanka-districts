package com.lanka_guide.districts;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.lanka_guide.districts.play.PlayActivity;
import com.lanka_guide.districts.quiz.QuizActivity;

import java.util.Locale;

/**
 * Created by chanya on 2017/09/27.
 */

public class MainActivity extends Activity {

    Preferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        final Spinner spinner = (Spinner) findViewById(R.id.selectLanguage);
        preferences = new Preferences(this);

        final Locale savedLocale = preferences.getLocale();
        if (savedLocale == null) {
            spinner.setSelection(0);
        } else if (savedLocale.equals(Locale.ENGLISH)) {
            spinner.setSelection(1);
        } else {
            spinner.setSelection(2);
        }

        // TODO: 2017/10/06 check whether this is necessary
        setLocale(savedLocale);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int selectedItem = spinner.getSelectedItemPosition();
                Locale selectedLocale;

                if (selectedItem == 2) {
                    selectedLocale = new Locale("si");
                } else {
                    selectedLocale = Locale.ENGLISH;
                }
                preferences.setLocale(selectedLocale);
                setLocale(selectedLocale);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                if (savedLocale == null) {
                    preferences.setLocale(Locale.ENGLISH);
                    setLocale(Locale.ENGLISH);
                }
            }
        });
    }

    public void setLocale(Locale locale) {
        Configuration config = new Configuration();
        config.setLocale(locale);
//        this.getApplicationContext().createConfigurationContext(config);

        getBaseContext().getResources().updateConfiguration(config,
                getBaseContext().getResources().getDisplayMetrics());

//        setContentView(R.layout.main_activity);
    }
    public void onClickQuiz(View view) {
        Intent intent = new Intent(this, QuizActivity.class);
        startActivity(intent);
    }

    public void onClickPlay(View view) {
        Intent intent = new Intent(this, PlayActivity.class);
        startActivity(intent);
    }
}
