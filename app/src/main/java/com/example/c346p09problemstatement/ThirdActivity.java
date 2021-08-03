package com.example.c346p09problemstatement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Rating;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class ThirdActivity extends AppCompatActivity {

    EditText etId,etTitle2,etSingers2,etYear2;
    RatingBar rbStars2;
    Button btnUpdate,btnDelete,btnCancel;
    Song data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        etId = findViewById(R.id.etId);
        etTitle2 = findViewById(R.id.etTitle2);
        etSingers2 = findViewById(R.id.etSingers2);
        etYear2 = findViewById(R.id.etYear2);
        rbStars2 = findViewById(R.id.rbStars2);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        btnCancel = findViewById(R.id.btnCancel);

        Intent i = getIntent();
        data = (Song) i.getSerializableExtra("data");

        etId.setText(String.valueOf(data.getId()));
        etTitle2.setText(data.getTitle());
        etSingers2.setText(data.getSingers());
        etYear2.setText(String.valueOf(data.getYear()));
        rbStars2.setRating((data.getStars()));

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etTitle2.getText().toString().isEmpty()||etSingers2.getText().toString().isEmpty()||etYear2.getText().toString().isEmpty()) {
                    Toast.makeText(ThirdActivity.this,"Please fill in all field",Toast.LENGTH_SHORT).show();
                }else {
                    DBHelper dbh = new DBHelper(ThirdActivity.this);
                    data.setTitle(etTitle2.getText().toString());
                    data.setSingers(etSingers2.getText().toString());
                    data.setYear(Integer.parseInt(etYear2.getText().toString()));
                    data.setStars((int) rbStars2.getRating());
                    dbh.updateSong(data);
                    dbh.close();
                    finish();
                }
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(ThirdActivity.this);
                dbh.deleteSong(data.getId());
                finish();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}