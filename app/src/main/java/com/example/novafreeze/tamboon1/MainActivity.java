package com.example.novafreeze.tamboon1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private ListView theListView;

    ArrayList<String> listItems = new ArrayList<String>();
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        theListView = (ListView) findViewById(R.id.list_view_result);

        getCharities();

        theListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Could include if statements here to check for which charity was clicked, then putExtra
                Intent intent = new Intent(MainActivity.this, MakingDonation.class);
                startActivity(intent);
            }
        });



    }


    private void getCharities() {

        // Set up the ListView Adapter
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                listItems);
        theListView.setAdapter(adapter);

        // Build Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://virtserver.swaggerhub.com/chakritw/tamboon-api/1.0.0/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        CharitiesAPI CharitiesAPI = retrofit.create(CharitiesAPI.class);

        // Retrofit creates the implementation for this method
        Call<Getting> call = CharitiesAPI.getCharities();

        // Retrofit provides this method for us to make the call on a background thread
        call.enqueue(new Callback<Getting>() {

            @Override
            public void onResponse(Call<Getting> call, Response<Getting> response) {

                if (!response.isSuccessful()) {
                    listItems.add("Error Code: " + response.code());
                    adapter.notifyDataSetChanged();
                    return;
                }

                Getting charities = response.body();

                List<Getting.Data> DataList = charities.data;

                // Iterate through responses and append data to the View
                for(Getting.Data charity : DataList) {
                    String content = "";
                    content += "ID: " + charity.getId() + "\n";
                    content += "Name: " + charity.getName() + "\n";
                    content += "Logo: " + charity.getLogo_url() + "\n\n";

                    listItems.add(content);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<Getting> call, Throwable t) {
                listItems.add(t.getMessage());
                adapter.notifyDataSetChanged();
            }
        });
    }

//    private void makeDonation() {
//
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("https://virtserver.swaggerhub.com/chakritw/tamboon-api/1.0.0/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//        CharitiesAPI CharitiesAPI = retrofit.create(CharitiesAPI.class);
//
//        Donation donation = new Donation("John Doe", "tokn_test_deadbeef", 100000);
//
//        Call<Donation> donationCall = CharitiesAPI.makeDonation(donation);
//
//        donationCall.enqueue(new Callback<Donation>() {
//            @Override
//            public void onResponse(Call<Donation> call, Response<Donation> response) {
//
//                if (!response.isSuccessful()) {
//                    textViewResult.setText("Code: " + response.code());
//                    return;
//                }
//
//                Donation donationResponse = response.body();
//
//                String donationResult = "";
//                donationResult += "Code: " + response.code() + "\n";
//                donationResult += "Successful : " + donationResponse.isSuccess() + "\n";
//                donationResult += donationResponse.getError_code() + "\n";
//                donationResult += donationResponse.getError_message() + "\n";
//
//                textViewResult.setText(donationResult);
//
//            }
//
//            @Override
//            public void onFailure(Call<Donation> call, Throwable t) {
//                textViewResult.setText(t.getMessage());
//            }
//        });
//
//    }

}
