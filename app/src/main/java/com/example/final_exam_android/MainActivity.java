package com.example.final_exam_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static String categories[] = {""};
    public static ArrayList<Item> itemList = new ArrayList<>();
    public static ArrayList<Item> tempItemList = new ArrayList<>();
    public static Item selectedItem;
    Spinner categoriesSp;
    TextView totalTV;
    ListView itemLV;
    Button button1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fillData();
        button1 = findViewById(R.id.button);
        categoriesSp = findViewById(R.id.categoriesSp);
        itemLV = findViewById(R.id.itemLV);
        totalTV = findViewById(R.id.totalTV);

        categoriesSp.setAdapter(new ArrayAdapter<>(this,R.layout.support_simple_spinner_dropdown_item,categories));
        categoriesSp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                getTempList(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                getTempList(0);
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(getBaseContext(), SecondActivity.class));
            }
        });

        itemLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedItem = tempItemList.get(position);

            }
        });




    }

    private void fillData(){
        itemList.add(new Item());
    }

    private void getTempList(int position){
        tempItemList.clear();
        for (Item it: itemList) {
            if (it.getCategory().equals(categories[position])){
                tempItemList.add(it);
            }
        }
        itemLV.setAdapter(new ItemAdapter(getBaseContext(), tempItemList));
        selectedItem = tempItemList.get(0);
    }
}