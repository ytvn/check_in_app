package com.example.login.Menu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.login.R;
import com.example.login.Retrofit.APIUtils;
import com.example.login.Retrofit.DataClient;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CheckIn extends AppCompatActivity {
    private SharedPreferences sharedPreferences;
    DataClient dataClient= APIUtils.getData();
    Button btnCheckIn;
    TextView tvShow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_in);
        initViews();
//        final String MAHV = sharedPreferences.getString("ID", "");
        final String MAHV = "17521287";
        final String TOKEN ="$2y$10$OzZADo4veyzCOIbQ73R9OOqU110ZsKmJadh72Ehow.hrVQkubKNIa@CSDL";
        final IntentIntegrator intentIntegrator =new IntentIntegrator(this);
        btnCheckIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentIntegrator.initiateScan();
//                api_checkIn(TOKEN, MAHV);
            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null) {
            if(result.getContents() == null) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
            } else {
                String re = result.getContents();
                api_checkIn(re, "17521287");
                tvShow.setText(re);
//                Toast.makeText(this, "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
    private void api_checkIn(String TOKEN, String MAHV){
        Call<String> callback = dataClient.checkIn(TOKEN, MAHV);
        callback.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String message = response.body();
                if(message.equals("0"))
                    Toast.makeText(CheckIn.this, "Điểm Danh thất bại", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });

    }

    private  void initViews(){
        btnCheckIn = findViewById(R.id.btnCheckIn);
        tvShow = findViewById(R.id.tvShow);
        sharedPreferences = getSharedPreferences("User", MODE_PRIVATE);
    }
}
