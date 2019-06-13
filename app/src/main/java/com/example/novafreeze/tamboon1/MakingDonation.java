package com.example.novafreeze.tamboon1;

import android.content.Intent;
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

    private EditText name;
    private EditText cardNumber;
    private EditText donationAmount;
    private Button submitButton;

    private int theCardNumber;
    private int theDonationAmount;
    private String custName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_making_donation);

        cardNumber = findViewById(R.id.cardNumber);
        donationAmount = findViewById(R.id.donationAmount);
        name = findViewById(R.id.name);

        submitButton = findViewById(R.id.button);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeDonation();

                Intent intent = new Intent(MakingDonation.this, SuccessActivity.class);
                startActivity(intent);
            }
        });

    }

    private void makeDonation() {

        theCardNumber = Integer.parseInt(String.valueOf(cardNumber.getText()));
        theDonationAmount = Integer.parseInt(String.valueOf(donationAmount.getText()));
        custName = name.toString();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://virtserver.swaggerhub.com/chakritw/tamboon-api/1.0.0/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        CharitiesAPI CharitiesAPI = retrofit.create(CharitiesAPI.class);

        Donation donation = new Donation(custName, "tokn_test_deadbeef", theDonationAmount);

        Call<Donation> donationCall = CharitiesAPI.makeDonation(donation);

        donationCall.enqueue(new Callback<Donation>() {
            @Override
            public void onResponse(Call<Donation> call, Response<Donation> response) {

                if (!response.isSuccessful()) {
                    Toast.makeText(MakingDonation.this, "Something went wrong! Error code: " + response.code(), Toast.LENGTH_LONG).show();
                    return;
                }

                Donation donationResponse = response.body();

                String donationResult = "";
                donationResult += "Code: " + response.code() + "\n";
                donationResult += "Successful : " + donationResponse.isSuccess() + "\n";
//                donationResult += donationResponse.getError_code() + "\n";
//                donationResult += donationResponse.getError_message() + "\n";

                Toast.makeText(MakingDonation.this, donationResult, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<Donation> call, Throwable t) {
                Toast.makeText(MakingDonation.this, "Something went wrong! Error code: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }

}
