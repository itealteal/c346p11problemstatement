package com.example.c346p09problemstatement;

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
import java.util.logging.Handler;

public class SecondActivity extends AppCompatActivity {

    Button btnToggle;
    Spinner spinner;
    ListView lv;
    CustomAdapter aa;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        btnToggle = findViewById(R.id.btnToggle);
        spinner = findViewById(R.id.spinner);
        lv = findViewById(R.id.lv);

        spinnerData();
        ArrayList<Song> as = new ArrayList<Song>();
//        ArrayAdapter<Song> aa = new ArrayAdapter<Song>(SecondActivity.this, android.R.layout.simple_list_item_1,as);
//        lv.setAdapter(aa);

        aa = new CustomAdapter(this, R.layout.row, as);
        lv.setAdapter(aa);


        DBHelper dbh = new DBHelper(SecondActivity.this);
        as.clear();
        as.addAll(dbh.getAllSongs());
        aa.notifyDataSetChanged();


        btnToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btnToggle.getText().toString().equalsIgnoreCase("Show Only Songs with 5 Stars")){
                    btnToggle.setText("Show All Songs");
                    btnToggle.setBackgroundColor(getResources().getColor(R.color.dodger));
                    DBHelper dbh = new DBHelper(SecondActivity.this);
                    ArrayList<Song> temp = new ArrayList<Song>();
//                    ArrayAdapter<Song> aatemp = new ArrayAdapter<Song>(SecondActivity.this, android.R.layout.simple_list_item_1,temp);
                    CustomAdapter aatemp = new CustomAdapter(SecondActivity.this, R.layout.row, temp);
                    lv.setAdapter(aatemp);
                    temp.clear();
                    temp.addAll(dbh.getAllSongsByStar());
                    aatemp.notifyDataSetChanged();
                }else{
                    btnToggle.setText("Show Only Songs with 5 Stars");
                    btnToggle.setBackgroundColor(getResources().getColor(R.color.grey));
                    DBHelper dbh = new DBHelper(SecondActivity.this);
                    ArrayList<Song> temp = new ArrayList<Song>();
//                    ArrayAdapter<Song> aatemp = new ArrayAdapter<Song>(SecondActivity.this, android.R.layout.simple_list_item_1,temp);
                    CustomAdapter aatemp = new CustomAdapter(SecondActivity.this, R.layout.row, temp);
                    lv.setAdapter(aatemp);
                    temp.clear();
                    temp.addAll(dbh.getAllSongs());
                    aatemp.notifyDataSetChanged();
                }
            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int
                    position, long identity) {
                Song data = as.get(position);
                Intent i = new Intent(SecondActivity.this,
                        ThirdActivity.class);
                i.putExtra("data", data);
                startActivity(i);
            }
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position==0){
                    DBHelper dbh = new DBHelper(SecondActivity.this);
                    ArrayList<Song> temp = new ArrayList<Song>();
//                    ArrayAdapter<Song> aatemp = new ArrayAdapter<Song>(SecondActivity.this, android.R.layout.simple_list_item_1,temp);
                    CustomAdapter aatemp = new CustomAdapter(SecondActivity.this, R.layout.row, temp);
                    lv.setAdapter(aatemp);
                    temp.clear();
                    temp.addAll(dbh.getAllSongs());
                    aatemp.notifyDataSetChanged();
                }else {
                    DBHelper dbh = new DBHelper(SecondActivity.this);
                    ArrayList<Song> temp = new ArrayList<Song>();
//                    ArrayAdapter<Song> aatemp = new ArrayAdapter<Song>(SecondActivity.this, android.R.layout.simple_list_item_1, temp);
                    CustomAdapter aatemp = new CustomAdapter(SecondActivity.this, R.layout.row, temp);
                    lv.setAdapter(aatemp);
                    temp.clear();
                    temp.addAll(dbh.getAllSongsByYear(Integer.parseInt(spinner.getSelectedItem().toString())));
                    aatemp.notifyDataSetChanged();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        spinnerData();
        ArrayList<Song> as = new ArrayList<Song>();
        ArrayAdapter<Song> aa = new ArrayAdapter<Song>(SecondActivity.this, android.R.layout.simple_list_item_1,as);
        lv.setAdapter(aa);

        DBHelper dbh = new DBHelper(SecondActivity.this);
        as.clear();
        as.addAll(dbh.getAllSongs());
        aa.notifyDataSetChanged();
    }

    private void spinnerData(){
        ArrayList<Integer> years = new ArrayList<Integer>();
        ArrayAdapter<Integer> ay = new ArrayAdapter<Integer>(SecondActivity.this, android.R.layout.simple_spinner_dropdown_item,years);
        spinner.setAdapter(ay);
        DBHelper dbh = new DBHelper(SecondActivity.this);
        ArrayList<Integer> temp = dbh.getAllYears();
        years.clear();
        years.add(0);
        for(int i =0;i < temp.size();i++){
            if(!years.contains(temp.get(i))){
                years.add(temp.get(i));
            }
        }
        ay.notifyDataSetChanged();
    }

}