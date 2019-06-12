package com.example.novafreeze.tamboon1;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CharitiesAPI {

    @GET("charities")
    Call<List<Getting>> getCharities();

}
