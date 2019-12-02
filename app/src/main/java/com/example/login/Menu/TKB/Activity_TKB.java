package com.example.login.Menu.TKB;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.login.R;
import com.example.login.Retrofit.APIUtils;
import com.example.login.Retrofit.DataClient;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Activity_TKB extends AppCompatActivity {
    Button btn2;
    Button btn3;
    Button btn4;
    Button btn5;
    Button btn6;
    Button btn7;
    Button btn8;
    List<TKB> tkbs ;
    public DB_TKB db_tkb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tkb);
        Calendar c = Calendar.getInstance();
        int day = c.get(Calendar.DAY_OF_WEEK);
        Init();
        OnClick(day);
        db_tkb = new DB_TKB(getBaseContext());
        db_tkb.clearTable();

        DataClient dataClient = APIUtils.getData();
        Call<List<TKB>> call = dataClient.getTKB();
        call.enqueue(new Callback<List<TKB>>() {
            @Override
            public void onResponse(Call<List<TKB>> call, Response<List<TKB>> response) {
                tkbs = (List<TKB>) response.body();
                Toast.makeText(Activity_TKB.this, tkbs+"", Toast.LENGTH_SHORT).show();
                for(int i = 0 ; i < tkbs.size();i++){
                    TKB tkb = new TKB();
                    tkb.setId(tkbs.get(i).getId());
                    tkb.setMamh(tkbs.get(i).getMamh());
                    tkb.setName(tkbs.get(i).getName());
                    tkb.setTiet(tkbs.get(i).getTiet());
                    tkb.setPhong(tkbs.get(i).getPhong());
                    db_tkb.addTKB(tkb);

                }
            }

            @Override
            public void onFailure(Call<List<TKB>> call, Throwable t) {
                Toast.makeText(Activity_TKB.this, "FAIL", Toast.LENGTH_SHORT).show();
            }
        });
//        TKB tkb = new TKB(2,"CSDL","Pham nhat tuan","123","E11.8");
//        TKB tkb1 = new TKB(2,"Android","Pham nhat tuan","123","E11.8");
//        TKB tkb2 = new TKB(3,"Java","Pham nhat tuan","123","E11.8");
//        TKB tkb3 = new TKB(4,"Golang","Pham nhat tuan","123","E11.8");
//        TKB tkb4 = new TKB(5,"Golang","Pham nhat tuan","123","E11.8");
//        TKB tkb5 = new TKB(5,"Golang","Pham nhat tuan","123","E11.8");
//        TKB tkb6 = new TKB(6,"Golang","Pham nhat tuan","123","E11.8");
//        TKB tkb7 = new TKB(7,"Golang","Pham nhat tuan","123","E11.8");
//        TKB tkb8 = new TKB(7,"Golang","Pham nhat tuan","123","E11.8");
//        TKB tkb9 = new TKB(7,"Golang","Pham nhat tuan","123","E11.8");
//        db_tkb.addTKB(tkb);
//        db_tkb.addTKB(tkb1);
//        db_tkb.addTKB(tkb2);
//        db_tkb.addTKB(tkb3);
//        db_tkb.addTKB(tkb4);
//        db_tkb.addTKB(tkb5);
//        db_tkb.addTKB(tkb6);
//        db_tkb.addTKB(tkb7);
//        db_tkb.addTKB(tkb8);
//        db_tkb.addTKB(tkb9);

    }
    public void AddFragment(View view){
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        FragmentTKB fragmentTKB = null;
        switch (view.getId()){
            case R.id.btn_2:
                fragmentTKB = new FragmentTKB(2);
                changeButton();
                btn2.setBackgroundColor(Color.GRAY);
                break;
            case R.id.btn_3:
                fragmentTKB = new FragmentTKB(3);
                changeButton();
                btn3.setBackgroundColor(Color.GRAY);
                break;
            case R.id.btn_4:
                fragmentTKB = new FragmentTKB(4);
                changeButton();
                btn4.setBackgroundColor(Color.GRAY);
                break;
            case R.id.btn_5:
                fragmentTKB = new FragmentTKB(5);
                changeButton();
                btn5.setBackgroundColor(Color.GRAY);
                break;
            case R.id.btn_6:
                fragmentTKB = new FragmentTKB(6);
                changeButton();
                btn6.setBackgroundColor(Color.GRAY);
                break;
            case R.id.btn_7:
                fragmentTKB = new FragmentTKB(7);
                changeButton();
                btn7.setBackgroundColor(Color.GRAY);
                break;
//            case R.id.btn_8:
//                fragmentTKB = new FragmentTKB(8);
//                break;

        }
        Bundle bundle = new Bundle();
        bundle.putString("1","2");
        fragmentTKB.setArguments(bundle);
        fragmentTransaction.replace(R.id.frameContent,fragmentTKB);
        fragmentTransaction.commit();
    }
    public void changeButton(){
        btn2.setBackgroundColor(Color.WHITE);
        btn3.setBackgroundColor(Color.WHITE);
        btn4.setBackgroundColor(Color.WHITE);
        btn5.setBackgroundColor(Color.WHITE);
        btn6.setBackgroundColor(Color.WHITE);
        btn7.setBackgroundColor(Color.WHITE);
    }
    public void OnClick(int day) {
        switch (day){
            case 2:
                btn2.callOnClick();
                break;
            case 3:
                btn3.callOnClick();
                break;
            case 4:
                btn4.callOnClick();
                break;
            case 5:
                btn5.callOnClick();
                break;
            case 6:
                btn6.callOnClick();
                break;
            case 7:
                btn7.callOnClick();
                break;
//            case 8:
//                btn8.callOnClick();
//                break;
        }
    }
    public void Init(){
        btn2 = (Button) findViewById(R.id.btn_2);
        btn3 = (Button) findViewById(R.id.btn_3);
        btn4 = (Button) findViewById(R.id.btn_4);
        btn5 = (Button) findViewById(R.id.btn_5);
        btn6 = (Button) findViewById(R.id.btn_6);
        btn7 = (Button) findViewById(R.id.btn_7);
        //btn8 = (Button) findViewById(R.id.btn_8);
    }
}
