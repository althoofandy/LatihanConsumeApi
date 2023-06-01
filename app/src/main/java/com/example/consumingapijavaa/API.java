package com.example.consumingapijavaa;

import retrofit2.Call;
import retrofit2.http.GET;
interface API {
    @GET("hotel")
    Call<HotelResponse> hotel();
}
