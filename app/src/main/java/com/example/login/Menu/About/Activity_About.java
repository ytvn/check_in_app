package com.example.login.Menu.About;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.login.R;

public class Activity_About extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
    }

//    public void onClick(View v){
//        String url = "";
//        Intent i;
//        switch (v.getId()){
//            case R.id.iv_uit:
//                url = "https://www.uit.edu.vn";
//                i = new Intent(Intent.ACTION_VIEW);
//                i.setData(Uri.parse(url));
//                startActivity(i);
//                break;
//            case R.id.iv_oep:
//                url = "https://www.oep.edu.vn";
//                i = new Intent(Intent.ACTION_VIEW);
//                i.setData(Uri.parse(url));
//                startActivity(i);
//                break;
//            case R.id.iv_ctsv:
//                url = "https://www.ctsv.edu.vn";
//                i = new Intent(Intent.ACTION_VIEW);
//                i.setData(Uri.parse(url));
//                startActivity(i);
//                break;
//            default:
//                break;
//        }
//    }
}
