package com.example.novafreeze.tamboon1;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface CharitiesAPI {

    @GET("charities")
    Call<Getting> getCharities();

    @POST("donations")
    Call<Donation> makeDonation(@Body Donation donation);
}
