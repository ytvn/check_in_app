package com.example.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;


import android.content.SharedPreferences;

import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class Activity_Menu<card_checkin> extends AppCompatActivity {

    SharedPreferences sharedPreferences;


    CardView card_checkin;
    CardView card_grades;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__menu);


        sharedPreferences = getSharedPreferences("USER", MODE_PRIVATE);

        Toast.makeText(this, sharedPreferences.getString("ID", "") + "df", Toast.LENGTH_SHORT).show();


        //Activity CHECKIN
        card_checkin = (CardView) findViewById(R.id.cardview_checkin);
        card_checkin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_cardview_checkin = new Intent(Activity_Menu.this, Activity_Checkin.class);
                startActivity(intent_cardview_checkin);
            }
        });

        //Activity GRADES
        card_grades = (CardView) findViewById(R.id.cardview_grades);
        card_grades.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_cardview_grades = new Intent(Activity_Menu.this, Activity_Grades.class);
                startActivity(intent_cardview_grades);
            }
        });
    }

}
