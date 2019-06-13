package com.example.novafreeze.tamboon1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MakingDonation extends AppCompatActivity {

    private EditText cardNumber;
    private EditText donationAmount;
    private Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_making_donation);

        submitButton = (Button) findViewById(R.id.button);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeDonation();
            }
        });



    }





    private void makeDonation() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://virtserver.swaggerhub.com/chakritw/tamboon-api/1.0.0/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        CharitiesAPI CharitiesAPI = retrofit.create(CharitiesAPI.class);

        Donation donation = new Donation("John Doe", "tokn_test_deadbeef", 100000);

        Call<Donation> donationCall = CharitiesAPI.makeDonation(donation);

        donationCall.enqueue(new Callback<Donation>() {
            @Override
            public void onResponse(Call<Donation> call, Response<Donation> response) {

                if (!response.isSuccessful()) {
//                    textViewResult.setText("Code: " + response.code());
                    Toast.makeText(MakingDonation.this, response.code(), Toast.LENGTH_LONG).show();

                    return;
                }

                Donation donationResponse = response.body();

                String donationResult = "";
                donationResult += "Code: " + response.code() + "\n";
                donationResult += "Successful : " + donationResponse.isSuccess() + "\n";
                donationResult += donationResponse.getError_code() + "\n";
                donationResult += donationResponse.getError_message() + "\n";

//                textViewResult.setText(donationResult);
                Toast.makeText(MakingDonation.this, donationResult, Toast.LENGTH_LONG).show();


            }

            @Override
            public void onFailure(Call<Donation> call, Throwable t) {
//                textViewResult.setText(t.getMessage());
            }
        });

    }





}
