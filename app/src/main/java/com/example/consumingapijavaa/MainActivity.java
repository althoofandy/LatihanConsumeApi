package com.example.consumingapijavaa;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    ListView superListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        superListView = findViewById(R.id.ListView);
        getListHotel();
    }
    private void getListHotel(){
        Call<HotelResponse> call = RetrofitClient.getInstance().getMyApi().hotel();
        call.enqueue(new Callback<HotelResponse>() {
            @Override
            public void onResponse(Call<HotelResponse> call, Response<HotelResponse> response) {
                HotelResponse hotelResponse = response.body();

                List<Hotel> myHotelList = hotelResponse.getListHotel();

                String[] hotel = new String[myHotelList.size()];

                for(int i = 0; i<myHotelList.size();i++){
                    hotel[i] = myHotelList.get(i).getNama();
                }
                superListView.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1,hotel));
                superListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        String selectedHotel = (String) adapterView.getItemAtPosition(i);
                        Hotel hotel = myHotelList.get(i);
                        Toast.makeText(getApplicationContext(), "Detail hotel :" + hotel.getAlamat() + ":"+ hotel.getNomor_telp(),Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onFailure(Call<HotelResponse> call, Throwable t) {

            }
        });
    }
}