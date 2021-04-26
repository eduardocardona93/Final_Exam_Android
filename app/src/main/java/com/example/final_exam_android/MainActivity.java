package com.example.final_exam_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

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
    ListView poiLV;
    SeekBar visitorsSB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fillData(); // Sets the Initial Data

        // id assignation for the activity elements
        logoutTV = findViewById(R.id.logoutTV);
        countrySp = findViewById(R.id.countrySp);
        poiLV = findViewById(R.id.poiLV);
        totalTV = findViewById(R.id.totalTV);
        visitorsSB = findViewById(R.id.visitorsSB);
        flagIv = findViewById(R.id.flagIv);
        capitalTv = findViewById(R.id.capitalTv);
        visitorsTv = findViewById(R.id.visitorsTv);
        selectedPoiTV = findViewById(R.id.selectedPoiTV);


        countrySp.setAdapter(new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,countriesNamesList)); // sets the countries spinner adapter and list

        // country spinner change event handler
        countrySp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                getTempList(position);  // gets the temporal list of poi for the list view
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                getTempList(0); // gets the temporal list of poi for the list view
            }
        });
        // logout click event handler
        logoutTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               startActivity(new Intent(getBaseContext(), LoginActivity.class)); // return to the login page
                Toast.makeText(getBaseContext(),"Bye!!", Toast.LENGTH_LONG).show();

            }
        });
        // poi listview item click event handler
        poiLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedItem = tempItemList.get(position); // sets the selected item
                selectedPoiTV.setText(selectedItem.getName()); // shows the seleted poi into the textview
                calculateTotal(); // calculates the total amount
            }
        });
        // visitors seekback change event handler
        visitorsSB.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                calculateTotal();// calculates the total amount
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });
    }
    // Sets the Initial Data
    private void fillData(){
        // Canada and its POI
        countriesList.add(new Country("Canada","Ottawa", getResources().getIdentifier("canada","mipmap",getPackageName())));
        poiList.add(new PointOfInterest(countriesList.get(0).getName(),"Niagara falls ",getResources().getIdentifier("niagara","mipmap",getPackageName()),100.0));
        poiList.add(new PointOfInterest(countriesList.get(0).getName(),"CN Tower",getResources().getIdentifier("cntower","mipmap",getPackageName()),30.0));
        poiList.add(new PointOfInterest(countriesList.get(0).getName(),"The Butchart Gardens",getResources().getIdentifier("butchart","mipmap",getPackageName()),30.0));
        poiList.add(new PointOfInterest(countriesList.get(0).getName(),"Notre-Dame Basilica",getResources().getIdentifier("notredame","mipmap",getPackageName()),50.0));
        // USA and its POI
        countriesList.add(new Country("United States","Washington", getResources().getIdentifier("us","mipmap",getPackageName())));
        poiList.add(new PointOfInterest(countriesList.get(1).getName(),"The Statue of Liberty",getResources().getIdentifier("statueliberty","mipmap",getPackageName()),90.0));
        poiList.add(new PointOfInterest(countriesList.get(1).getName(),"The White House",getResources().getIdentifier("whitehouse","mipmap",getPackageName()),60.0));
        poiList.add(new PointOfInterest(countriesList.get(1).getName(),"Times Square",getResources().getIdentifier("timesquare","mipmap",getPackageName()),75.0));
        // England and its POI
        countriesList.add(new Country("England","London", getResources().getIdentifier("england","mipmap",getPackageName())));
        poiList.add(new PointOfInterest(countriesList.get(2).getName(),"Big Ben",getResources().getIdentifier("bigben","mipmap",getPackageName()),30.0));
        poiList.add(new PointOfInterest(countriesList.get(2).getName(),"Westminster Abbey",getResources().getIdentifier("westminster","mipmap",getPackageName()),25.0));
        poiList.add(new PointOfInterest(countriesList.get(2).getName(),"Hyde Park",getResources().getIdentifier("hyde","mipmap",getPackageName()),15.0));
        //Countries names for the spinner
        countriesNamesList.add(countriesList.get(0).getName());
        countriesNamesList.add(countriesList.get(1).getName());
        countriesNamesList.add(countriesList.get(2).getName());

    }
    // gets the temporal list of poi for the list view
    private void getTempList(int position){
        tempItemList.clear(); // clears the list
        for (PointOfInterest it: poiList) { // iterates all the poi
            if (it.getCountry().equals(countriesNamesList.get(position))){ // validates if it belongs to the selected country
                tempItemList.add(it); // adds it to the list
            }
        }
        poiLV.setAdapter(new ItemAdapter(getBaseContext(), tempItemList)); // sets the adapter for the poi listview
        selectedItem = tempItemList.get(0); // sets the selected item as the first of the list
        selectedPoiTV.setText(selectedItem.getName()); // shows the seleted poi into the textview
        capitalTv.setText(countriesList.get(position).getCapital()); // shows the capital of the country
        flagIv.setImageResource(countriesList.get(position).getFlagImg()); // shows the flag of the country
        calculateTotal(); // calculates the total amount
    }
    // calculates the total amount based on the poi price and number of visitors
    private void calculateTotal(){

        double visitors = visitorsSB.getProgress(); // gets the seekbar visitors value
        visitorsTv.setText(String.valueOf(visitorsSB.getProgress())); // shows the visitors value into the textview
        double discount = 0;
        if (visitors >=15){ // if there is 15 or more visitors
            discount = 0.05; // sets the discount as 5%
        }
        double total = selectedItem.getVisitPrice() * visitorsSB.getProgress() * (1 - discount); // calculates the total amount
        totalTV.setText("$ " + String.format("%.2f", total)); // shows the total value into the textview
    }
}