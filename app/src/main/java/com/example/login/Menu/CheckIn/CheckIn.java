package com.example.login.Menu.CheckIn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.login.Database.Database;
import com.example.login.GPS.GPS;
import com.example.login.R;
import com.example.login.Retrofit.APIUtils;
import com.example.login.Retrofit.DataClient;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CheckIn extends AppCompatActivity {
    private Database database;
    private SharedPreferences sharedPreferences;
    private static  final double LATITUDE = 10.883166;
    private static  final double LONGTITUDE =106.780587;
    private  String ID ;
    private  String MAMH;
    DataClient dataClient= APIUtils.getData();
    Button btnCheckIn;
    TextView tvError;
    GPS gps;
    ArrayList<CheckInList> checkInLists;
    AdapterList adapter;
    ListView lvCheckInList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_in);
        initViews();
        final IntentIntegrator intentIntegrator =new IntentIntegrator(this);
        database.QueryData("CREATE TABLE IF NOT EXISTS DiemDanh(Id INTEGER PRIMARY KEY AUTOINCREMENT, MAMH VARCHAR(10), time datetime, MAHV VARCHAR(10))");
//        database.QueryData("INSERT INTO DiemDanh VALUES(null, 'CSDL', '2019-11-22 16:12:08', '17521287')");
//        database.deleteAll();
        getDatabase();
        btnCheckIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                api_getDatabase();

                adapter.notifyDataSetChanged();
//
//                gps.locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
//                if (!gps.locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
//                    gps.OnGPS();
//                } else {
//                    gps.getLocation();
//                }
//                Toast.makeText(CheckIn.this, gps.getStatus()+"", Toast.LENGTH_SHORT).show();
//                if(gps.getStatus()==1 && checkLocation()==1) {// Nếu đã mở location và accept access thì mở camara
//                    tvError.setText("");
//                    intentIntegrator.initiateScan();
//
//                    Toast.makeText(CheckIn.this, "ydapchai", Toast.LENGTH_SHORT).show();
//                }
//                else if(gps.getStatus() == 0)
//                    tvError.setText("Không thể điểm danh\nMở GPS và cho phép UIT truy cập rồi thử lại");
//                else if(checkLocation() == 4)
//                    tvError.setText("Điểm danh không thành công\nVào lớp học để điểm danh");
//                Toast.makeText(CheckIn.this, ""+checkLocation(), Toast.LENGTH_SHORT).show();
//                Toast.makeText(CheckIn.this, gps.getStatus()+"d"+gps.getLatitude(), Toast.LENGTH_SHORT).show();


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
                MAMH = re.split("@")[1];
                Toast.makeText(this, MAMH, Toast.LENGTH_SHORT).show();
                api_checkIn(re);// gửi token (re) lên server để điểm danh
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
    private void getDatabase(){
        Cursor data = database.GetData("SELECT * FROM DiemDanh");
        if(data.getCount()==0) {//Nếu SQLite chưa có dữ liệu, thì request lên server để lấy về
            api_getDatabase();
//            Toast.makeText(this, "11", Toast.LENGTH_SHORT).show();
            data = database.GetData("SELECT * FROM DiemDanh");

        }
        if(data.moveToFirst())
        {
            do{
                Toast.makeText(this, "hehe", Toast.LENGTH_SHORT).show();
            String mamh = data.getString(1);
            String time = data.getString(2);
            String mahv = data.getString(3);
            checkInLists.add(new CheckInList(mamh,time, mahv));
            }while(data.moveToNext());
        }
//        while (data.moveToNext()){
//            Toast.makeText(this, "hehe", Toast.LENGTH_SHORT).show();
//            String mamh = data.getString(1);
//            String time = data.getString(2);
//            String mahv = data.getString(3);
//            checkInLists.add(new CheckInList(mamh,time, mahv));
//        }
        adapter.notifyDataSetChanged();

    }

    private  void api_getDatabase(){
        Call<ArrayList<CheckInList>> callback = dataClient.getCheckInInfor(ID);
        callback.enqueue(new Callback<ArrayList<CheckInList>>() {
            @Override
            public void onResponse(Call<ArrayList<CheckInList>> call, Response<ArrayList<CheckInList>> response) {
                ArrayList<CheckInList> message = response.body();
                database.insert(message);
//                checkInLists.addAll(message);
//                adapter.notifyDataSetChanged();
//                Toast.makeText(CheckIn.this, "22", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFailure(Call<ArrayList<CheckInList>> call, Throwable t) {
                Toast.makeText(CheckIn.this, "Toang", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void api_checkIn(String TOKEN){
        Call<String> callback = dataClient.checkIn(TOKEN, ID);
        callback.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String message = response.body();
                if(message.equals("0"))
                    Toast.makeText(CheckIn.this, "Điểm Danh thất bại", Toast.LENGTH_SHORT).show();
                else if (message.equals("2"))
                    Toast.makeText(CheckIn.this, "Bạn đã điểm danh", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(CheckIn.this, "Điểm danh thành công", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(CheckIn.this, "Toang", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private int checkLocation(){
        double lat2 =10.881817; //gps.getLatitude();10.881817, 106.782604
        double lon2 =106.782604;// gps.getLongitude();
        double R = 6372.8; // Earth Radius in Kilometers

        double dLat = Deg2Rad(lat2-LATITUDE);
        double dLon = Deg2Rad(lon2-LONGTITUDE);
        double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
                Math.cos(Deg2Rad(LATITUDE)) * Math.cos(Deg2Rad(lat2)) *
                        Math.sin(dLon/2) * Math.sin(dLon/2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        double d = R * c;

//         Return Distance in Kilometers
//        return d;
        if(d<0.5)
            return 1;
        return 4;//nằm ngoài khu vực điểm danh

    }
    private double Deg2Rad(double deg){
        return deg * Math.PI / 180;
    }
    private  void initViews(){
        btnCheckIn = findViewById(R.id.btnCheckIn);
        tvError = findViewById(R.id.tvError);
        sharedPreferences = getSharedPreferences("USER", MODE_PRIVATE);
        gps = new GPS(this, CheckIn.this );
        lvCheckInList = findViewById(R.id.lvList);
        checkInLists = new ArrayList<>();
        adapter = new AdapterList(this, R.layout.check_in_list, checkInLists);
        lvCheckInList.setAdapter(adapter);
        ID = sharedPreferences.getString("ID", "");
        database = new Database(this, "diemdanh.sqlite", null, 1);
    }
}
