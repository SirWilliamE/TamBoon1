package com.example.novafreeze.tamboon1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewResult = findViewById(R.id.text_view_result);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://virtserver.swaggerhub.com/chakritw/tamboon-api/1.0.0/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        CharitiesAPI CharitiesAPI = retrofit.create(CharitiesAPI.class);

        //Retrofit creates the implementation for this method
        Call<List<Getting>> call = CharitiesAPI.getCharities();


        //Retrofit provides this method for us to call on a background thread
        call.enqueue(new Callback<List<Getting>>() {
            @Override
            public void onResponse(Call<List<Getting>> call, Response<List<Getting>> response) {

                if (!response.isSuccessful()) {
                    textViewResult.setText("Error Code: " + response.code());
                    return;
                }

                List<Getting> charities = response.body();



            }

            @Override
            public void onFailure(Call<List<Getting>> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });

    }
}
