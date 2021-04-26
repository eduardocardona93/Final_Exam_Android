package com.example.final_exam_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static ArrayList<Country> countriesList = new ArrayList<>();
    public static ArrayList<String> countriesNamesList = new ArrayList<>();
    public static ArrayList<PointOfInterest> poiList = new ArrayList<>();
    public static ArrayList<PointOfInterest> tempItemList = new ArrayList<>();
    public static PointOfInterest selectedItem;
    Spinner countrySp;
    TextView totalTV, capitalTv, visitorsTv,selectedPoiTV, logoutTV;
    ImageView flagIv;
    ListView itemLV;
    SeekBar visitorsSB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fillData();
        logoutTV = findViewById(R.id.logoutTV);
        countrySp = findViewById(R.id.countrySp);
        itemLV = findViewById(R.id.itemLV);
        totalTV = findViewById(R.id.totalTV);
        visitorsSB = findViewById(R.id.visitorsSB);
        flagIv = findViewById(R.id.flagIv);
        capitalTv = findViewById(R.id.capitalTv);
        visitorsTv = findViewById(R.id.visitorsTv);
        selectedPoiTV = findViewById(R.id.selectedPoiTV);
        countrySp.setAdapter(new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,countriesNamesList));
        countrySp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                getTempList(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                getTempList(0);
            }
        });

        logoutTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               startActivity(new Intent(getBaseContext(), LoginActivity.class));
            }
        });

        itemLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedItem = tempItemList.get(position);
                calculateTotal();
            }
        });
        visitorsSB.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                calculateTotal();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });
    }

    private void fillData(){
        countriesList.add(new Country("Canada","Ottawa", getResources().getIdentifier("canada","mipmap",getPackageName())));
        poiList.add(new PointOfInterest(countriesList.get(0).getName(),"Niagara falls ",2,100.0));
        poiList.add(new PointOfInterest(countriesList.get(0).getName(),"CN Tower",2,30.0));
        poiList.add(new PointOfInterest(countriesList.get(0).getName(),"The Butchart Gardens",2,30.0));
        poiList.add(new PointOfInterest(countriesList.get(0).getName(),"Notre-Dame Basilica",2,50.0));

        countriesList.add(new Country("United States","Washington", getResources().getIdentifier("us","mipmap",getPackageName())));
        poiList.add(new PointOfInterest(countriesList.get(1).getName(),"The Statue of Liberty",2,90.0));
        poiList.add(new PointOfInterest(countriesList.get(1).getName(),"The White House",2,60.0));
        poiList.add(new PointOfInterest(countriesList.get(1).getName(),"Times Square",2,75.0));

        countriesList.add(new Country("England","London", getResources().getIdentifier("england","mipmap",getPackageName())));
        poiList.add(new PointOfInterest(countriesList.get(2).getName(),"Big Ben",2,30.0));
        poiList.add(new PointOfInterest(countriesList.get(2).getName(),"Westminster Abbey",2,25.0));
        poiList.add(new PointOfInterest(countriesList.get(2).getName(),"Hyde Park",2,15.0));

        countriesNamesList.add(countriesList.get(0).getName());
        countriesNamesList.add(countriesList.get(1).getName());
        countriesNamesList.add(countriesList.get(2).getName());

    }

    private void getTempList(int position){
        tempItemList.clear();
        for (PointOfInterest it: poiList) {
            if (it.getCountry().equals(countriesNamesList.get(position))){
                tempItemList.add(it);
            }
        }
        itemLV.setAdapter(new ItemAdapter(getBaseContext(), tempItemList));
        selectedItem = tempItemList.get(0);
        capitalTv.setText(countriesList.get(position).getCapital());
        flagIv.setImageResource(countriesList.get(position).getFlagImg());
        calculateTotal();
    }

    private void calculateTotal(){
        double visitors = visitorsSB.getProgress();
        visitorsTv.setText(String.valueOf(visitorsSB.getProgress()));
        double discount = 0;
        if (visitors >=15){
            discount = 0.05;
        }
        double total = selectedItem.getVisitPrice()*visitorsSB.getProgress() * (1 - discount);
        selectedPoiTV.setText(selectedItem.getName());

        totalTV.setText("$ " + String.format("%.2f", total));
    }
}