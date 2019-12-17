package com.example.login.Menu.Info;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.login.R;
import com.example.login.Retrofit.APIUtils;
import com.example.login.Retrofit.DataClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Activity_Info extends AppCompatActivity {
    private SharedPreferences sharedPreferences;
    private  String ID ;
    TextView tvName;
    TextView tvMssv;
    TextView tvMail;
    TextView tvDate;
    TextView tvSex;
    TextView tvDiachi;
    TextView tvLop;
    ImageView ivSex;
    String sex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        Init();
        sharedPreferences = getSharedPreferences("USER", MODE_PRIVATE);
        ID = sharedPreferences.getString("ID", "");
        tvMssv.setText(ID.toString());
        DataClient dataClient = APIUtils.getData();
        Call<List<Info>> call = dataClient.getInfo(ID);
        call.enqueue(new Callback<List<Info>>() {
            @Override
            public void onResponse(Call<List<Info>> call, Response<List<Info>> response) {
                List<Info> info =  response.body();
                tvMail.setText(info.get(0).getEMAIL());
                tvDate.setText(info.get(0).getNGSINH());
                tvDiachi.setText(info.get(0).getNOISINH());
                tvLop.setText(info.get(0).getMALOP());
                tvSex.setText(info.get(0).getGIOITINH());
                sex = info.get(0).getGIOITINH();
                if (sex.equals("Nam")){
                    ivSex.setBackgroundResource(R.drawable.icon_male);
                }
            }

            @Override
            public void onFailure(Call<List<Info>> call, Throwable t) {
                Toast.makeText(Activity_Info.this,  "FAIL", Toast.LENGTH_SHORT).show();
            }
        });





    }
    public void Init(){
        tvName = (TextView)findViewById(R.id.tv_name);
        tvMssv = (TextView)findViewById(R.id.tv_mssv);
        tvMail = (TextView)findViewById(R.id.tv_mail);
        tvDate = (TextView)findViewById(R.id.tv_ngsinh);
        tvSex = (TextView)findViewById(R.id.tv_sex);
        tvDiachi = (TextView)findViewById(R.id.tv_diachi);
        tvLop = (TextView)findViewById(R.id.tv_lop);
        ivSex = (ImageView) findViewById(R.id.iv_sex);
    }
}
