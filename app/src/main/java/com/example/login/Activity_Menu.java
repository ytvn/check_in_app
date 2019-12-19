package com.example.login;

import androidx.appcompat.app.AppCompatActivity;


import android.content.SharedPreferences;

import android.content.Intent;

import android.os.Bundle;
import android.view.View;

import com.example.login.Menu.CheckIn.CheckIn;
import com.example.login.Menu.TKB.Activity_TKB;
import com.example.login.Menu.Grade.Activity_Grade;


public class Activity_Menu<card_checkin> extends AppCompatActivity  implements View.OnClickListener {
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__menu);
        sharedPreferences = getSharedPreferences("USER", MODE_PRIVATE);

        initViews();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.cardview_checkin:
                Intent intent_cardview_checkin = new Intent(Activity_Menu.this, CheckIn.class);
                startActivity(intent_cardview_checkin);
                break;
            case R.id.cardview_grades:
                Intent intent_cardview_grades = new Intent(Activity_Menu.this, Activity_Grade.class);
                startActivity(intent_cardview_grades);
                break;
            case R.id.cardview_timetable:
                Intent intent_cardview_timeTable = new Intent(Activity_Menu.this, Activity_TKB.class);
                startActivity(intent_cardview_timeTable);
             default:
                 break;

        }
    }
    private void initViews(){
        findViewById(R.id.cardview_checkin).setOnClickListener(this);
        findViewById(R.id.cardview_grades).setOnClickListener(this);
        findViewById(R.id.cardview_deadline).setOnClickListener(this);
        findViewById(R.id.cardview_infor).setOnClickListener(this);
        findViewById(R.id.cardview_timetable).setOnClickListener(this);
        findViewById(R.id.cardview_tuitionfee).setOnClickListener(this);

    }
}
