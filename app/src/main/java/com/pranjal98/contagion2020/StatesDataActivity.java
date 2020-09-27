package com.pranjal98.contagion2020;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class StatesDataActivity extends AppCompatActivity  implements SwipeRefreshLayout.OnRefreshListener{

    TextView active, confirmed, recovered, decreased, till;
    StateApi stateApi;
    List<StateClass> requests;

    RecyclerView recyclerView;

    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_states_data);

        recyclerView = findViewById(R.id.recycler_view_requests);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        swipeRefreshLayout = findViewById(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);

        requests = new ArrayList<>();

        Button button = findViewById(R.id.backButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        active = findViewById(R.id.active);
        confirmed = findViewById(R.id.confirmed);
        decreased = findViewById(R.id.decreased);
        recovered = findViewById(R.id.recovered);
        till = findViewById(R.id.till);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.covid19india.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        stateApi = retrofit.create(StateApi.class);
        onLoadingSwipeRefresh();
    }

    public void stateData(){

        swipeRefreshLayout.setRefreshing(true);

        Call<State> call = stateApi.getStates();

        call.enqueue(new Callback<State>() {
            @Override
            public void onResponse(Call<State> call, Response<State> response) {

                if(!response.isSuccessful()){
//                    textView.setText("code : " + response.code());
                    return;
                }

                State state = response.body();

                List<StateItemRetrofit> items = state.getStatewise();
                for (StateItemRetrofit item : items) {


                    if(item.getState().equals("State Unassigned")){
                    }

                    else {
                        if(item.getState().equals("Total")){
                            confirmed.setText(item.getConfirmed());
                            active.setText("Active\n" + item.getActive());
                            decreased.setText("Deceased\n" + item.getDeaths());
                            recovered.setText("Recovered\n" + item.getRecovered());
                            till.setText("Last updated time : " + item.getLastupdatedtime());
                        }
                        else {

                            StateClass requestsRVModel = new StateClass(item.getState(), item.getActive(), item.getConfirmed(), item.getDeaths(), item.getRecovered(), item.getLastupdatedtime());
                            requests.add(requestsRVModel);

                            RecyclerView.Adapter adapter = new RecyclerViewAdapter(requests, StatesDataActivity.this, 2);

                            recyclerView.setAdapter(adapter);

                            swipeRefreshLayout.setRefreshing(false);
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<State> call, Throwable t) {

//                textView.setText(t.getMessage());

                swipeRefreshLayout.setRefreshing(true);
            }
        });
    }

    @Override
    public void onRefresh() {
        stateData();
    }

    private void onLoadingSwipeRefresh(){

        swipeRefreshLayout.post(
                new Runnable() {
                    @Override
                    public void run() {
                        stateData();
                    }
                }
        );
    }
}
