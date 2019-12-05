package com.example.login.Retrofit;

public class APIUtils {
    public static final String Base_Url="http:10.80.247.191:8000";
//public static final String Base_Url="http://192.168.240.1:8000";
//    public static final String Base_Url="http://192.168.223.78:8000";
//    public static final String Base_Url="http://10.45.14.213:8000";
//    public static final String Base_Url="http://192.168.43.224:8000";
    //nhan va gui data di, lam trung gian cho interface
    public static DataClient getData(){
        return RetrofitClient.getClient(Base_Url).create(DataClient.class);
    }
}
