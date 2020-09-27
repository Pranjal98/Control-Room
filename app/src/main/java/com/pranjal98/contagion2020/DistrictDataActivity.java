package com.pranjal98.contagion2020;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DistrictDataActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener{

    DistrictApi districtApi;
    ZoneApi zoneApi;

    TextView state, active, confirmed, recovered, decreased, till;

    RecyclerView recyclerView;
    List<DistrictClass> requests;

    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_district_data);

        swipeRefreshLayout = findViewById(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);

        recyclerView = findViewById(R.id.recycler_view_requests);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        requests = new ArrayList<>();

        Button button = findViewById(R.id.backButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        state = findViewById(R.id.covid_cases_in_india);
        active = findViewById(R.id.active);
        confirmed = findViewById(R.id.confirmed);
        decreased = findViewById(R.id.decreased);
        recovered = findViewById(R.id.recovered);
        till = findViewById(R.id.till);

        state.setText(getIntent().getStringExtra("stateName"));
        confirmed.setText(getIntent().getStringExtra("confirmed"));
        active.setText("Active\n" + getIntent().getStringExtra("active"));
        decreased.setText("Deceased\n" + getIntent().getStringExtra("decreased"));
        recovered.setText("Recovered\n" + getIntent().getStringExtra("recovered"));
        till.setText("Last updated time : " + getIntent().getStringExtra("till"));

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.covid19india.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        districtApi = retrofit.create(DistrictApi.class);
        zoneApi = retrofit.create(ZoneApi.class);

        onLoadingSwipeRefresh();
    }

    public void ditrictData(){

        swipeRefreshLayout.setRefreshing(true);

        Call<List<District>> call = districtApi.getDistricts();

        call.enqueue(new Callback<List<District>>() {
            @Override
            public void onResponse(Call<List<District>> call, Response<List<District>> response) {

                if(!response.isSuccessful()){
//                    textView.setText("code : " + response.code());
                    return;
                }

                List<District> districts = response.body();

                for (District district : districts){

                    if(district.getState().equals(getIntent().getStringExtra("stateName"))){

                        List<ItemRetrofit> items = district.districtData();
                        for (ItemRetrofit item : items) {

                            if(item.getDistrict().equals("Unknown")){
                            }

                            else {

                                Call<Zone> call2 = zoneApi.getZones();

                                call2.enqueue(new Callback<Zone>() {
                                    @Override
                                    public void onResponse(Call<Zone> call, Response<Zone> response) {


                                        if (!response.isSuccessful()) {
//                    textView.setText("code : " + response.code());
                                            return;
                                        }

                                        Zone zone = response.body();

                                        List<ZoneRetrofit> items = zone.getZones();
                                        for (ZoneRetrofit i : items) {

                                            if (i.getDistrict().equals(item.getDistrict())) {

//                                                zoneName = i.getDistrict() + " " +  + " " +  + "\n";

                                                DistrictClass requestsRVModel = new DistrictClass(item.getDistrict(), item.getActive(), item.getConfirmed(), item.getDeceased(), item.getRecovered(), i.getZone(), i.getLastupdated());
                                                requests.add(requestsRVModel);
                                                RecyclerView.Adapter adapter = new DistrictRecyclerViewAdapter(requests, DistrictDataActivity.this, 1);
                                                recyclerView.setAdapter(adapter);
                                                swipeRefreshLayout.setRefreshing(false);
                                            }
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<Zone> call, Throwable t) {
                                    }
                                });
                            }
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<List<District>> call, Throwable t) {

                swipeRefreshLayout.setRefreshing(true);
//                textView.setText(t.getMessage());
            }
        });
    }

    @Override
    public void onRefresh() {
        ditrictData();
    }

    private void onLoadingSwipeRefresh(){

        swipeRefreshLayout.post(
                new Runnable() {
                    @Override
                    public void run() {
                        ditrictData();
                    }
                }
        );
    }
}
