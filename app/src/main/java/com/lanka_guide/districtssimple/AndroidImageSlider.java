package com.lanka_guide.districtssimple;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class AndroidImageSlider extends AppCompatActivity {

    private Spinner spinner1;
    private ViewPager mViewPager;
    List<String> list = new ArrayList<>();
    ArrayAdapter<String> dataAdapter;
    int lastSuccessSelectPosition = -1;
    List<String> successIds = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.android_image_slider_activity);

        mViewPager = (ViewPager) findViewById(R.id.viewPageAndroid);
        AndroidImageAdapter adapterView = new AndroidImageAdapter(this);
        mViewPager.setAdapter(adapterView);
        mViewPager.addOnPageChangeListener(new CustomOnPageChangeListener());
        addListenerOnSpinnerItemSelection();
    }


    public void addListenerOnSpinnerItemSelection() {
        spinner1 = (Spinner) findViewById(R.id.spinner1);
        spinner1.setOnItemSelectedListener(new CustomOnItemSelectedListener());

        list.add("Ampara");
        list.add("Anuradhapura");
        list.add("Badulla");
        list.add("Batticaloa");
        list.add("Colombo");
        list.add("Galle");
        list.add("Gampaha");
        list.add("Hambantota");
        list.add("Jaffna");
        list.add("Kalutara");
        list.add("Kandy");
        list.add("Kegalle");
        list.add("Kilinochchi");
        list.add("Kurunegala");
        list.add("Mannar");
        list.add("Matale");
        list.add("Matara");
        list.add("Monaragala");
        list.add("Mullaitivu");
        list.add("Nuwara Eliya");
        list.add("Polonnaruwa");
        list.add("Puttalam");
        list.add("Ratnapura");
        list.add("Trincomalee");
        list.add("Vavuniya");


        list.add(0,"Select District Name");

        dataAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(dataAdapter);
    }

    private class CustomOnItemSelectedListener implements android.widget.AdapterView.OnItemSelectedListener {
        public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
            int currentDistrict = mViewPager.getCurrentItem();
            String correctName = Districts.getName(currentDistrict);
            String selectedName = parent.getItemAtPosition(pos).toString();

            String text;
            if (correctName.equals(selectedName)) {
                text = "Correct! Please slide for next one.";
                lastSuccessSelectPosition = pos;
                successIds.add(correctName);
            } else {
                text = "Wrong! Please try again.";
                spinner1.setSelection(0);
            }

            if(pos != 0) {
                Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
            }

        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    }

    private class CustomOnPageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            if(lastSuccessSelectPosition != -1) {
                list.remove(lastSuccessSelectPosition);

                lastSuccessSelectPosition = -1;
                dataAdapter.notifyDataSetChanged();
                spinner1.setSelection(0);

            }

            int currentDistrict = mViewPager.getCurrentItem();
            String correctName = Districts.getName(currentDistrict);

            if(successIds.contains(correctName)) {
                spinner1.setEnabled(false);
                Toast.makeText(AndroidImageSlider.this, correctName + " District",
                        Toast.LENGTH_SHORT).show();
            } else {
                spinner1.setEnabled(true);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }
}