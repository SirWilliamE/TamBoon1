package com.example.novafreeze.tamboon1;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;

public interface CharitiesAPI {

    @GET("charities")
    Call<Getting> getCharities();

}
