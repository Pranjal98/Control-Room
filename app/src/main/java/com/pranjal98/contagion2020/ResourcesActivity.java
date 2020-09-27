package com.pranjal98.contagion2020;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ResourcesActivity extends AppCompatActivity  implements ExampleBottomSheetDialog.BottomSheetListener {

    TextView active, confirmed, recovered, decreased, till;
    ResourcesApi resourcesApi;
    List<ResourceClass> requests;

    ProgressBar progressBar;

    ListView listView;

    SearchView searchView;

    String query;

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resources);

        progressBar = findViewById(R.id.progress_load_photo);

        searchView = findViewById(R.id.search);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                query = searchView.getQuery().toString().toLowerCase();
                stateData(query, "city");
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });

        Button buttonOpenBottomSheet = findViewById(R.id.button_open_bottom_sheet);
        buttonOpenBottomSheet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ExampleBottomSheetDialog bottomSheet = new ExampleBottomSheetDialog();
                bottomSheet.show(getSupportFragmentManager(), "exampleBottomSheet");
            }
        });

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

        active = findViewById(R.id.active);
        confirmed = findViewById(R.id.confirmed);
        decreased = findViewById(R.id.decreased);
        recovered = findViewById(R.id.recovered);
        till = findViewById(R.id.till);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.covid19india.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        resourcesApi = retrofit.create(ResourcesApi.class);
        stateData("", "");
    }

    public void stateData(String name, String type){

        requests.clear();

        Call<Resource> call = resourcesApi.getResource();

        call.enqueue(new Callback<Resource>() {
            @Override
            public void onResponse(Call<Resource> call, Response<Resource> response) {

                if(!response.isSuccessful()){
//                    textView.setText("code : " + response.code());
                    return;
                }

                Resource resource = response.body();

                List<ResourceRetrofit> items = resource.getResources();
                for (ResourceRetrofit item : items) {

                    if(name.equals("") && type.equals("")){

                        ResourceClass requestsRVModel = new ResourceClass(item.getCategory(), item.getCity(), item.getContact(), item.getDescriptionandorserviceprovided(), item.getNameoftheorganisation(), item.getPhonenumber(), item.getState());
                        requests.add(requestsRVModel);
                    }

                    else if(type.equals("category") && name.equals(item.getCategory().toLowerCase())){

                        ResourceClass requestsRVModel = new ResourceClass(item.getCategory(), item.getCity(), item.getContact(), item.getDescriptionandorserviceprovided(), item.getNameoftheorganisation(), item.getPhonenumber(), item.getState());
                        requests.add(requestsRVModel);
                    }

                    else if(type.equals("state") && name.equals(item.getState().toLowerCase())){

                        ResourceClass requestsRVModel = new ResourceClass(item.getCategory(), item.getCity(), item.getContact(), item.getDescriptionandorserviceprovided(), item.getNameoftheorganisation(), item.getPhonenumber(), item.getState());
                        requests.add(requestsRVModel);
                    }

                    else if(type.equals("city") && name.equals(item.getCity().toLowerCase())){

                        ResourceClass requestsRVModel = new ResourceClass(item.getCategory(), item.getCity(), item.getContact(), item.getDescriptionandorserviceprovided(), item.getNameoftheorganisation(), item.getPhonenumber(), item.getState());
                        requests.add(requestsRVModel);
                    }
                }

                if(requests.size() > 0){
                    progressBar.setVisibility(View.INVISIBLE);

                    RecyclerView.Adapter adapter = new ResourcesRecyclerViewAdapter(requests, ResourcesActivity.this, 2);
                    recyclerView.setAdapter(adapter);
                }
                else {
                    progressBar.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<Resource> call, Throwable t) {
//                textView.setText(t.getMessage());
            }
        });
    }

    @Override
    public void onButtonClicked(String text) {

        if(text.equals("")){
            stateData("", text);
        }
        else {

            listView = findViewById(R.id.list_item);
            listView.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.VISIBLE);

            ArrayList<String> type = new ArrayList<String>();

            Call<Resource> call = resourcesApi.getResource();

            call.enqueue(new Callback<Resource>() {
                @Override
                public void onResponse(Call<Resource> call, Response<Resource> response) {

                    if(!response.isSuccessful()){
//                    textView.setText("code : " + response.code());
                        return;
                    }

                    Resource resource = response.body();

                    List<ResourceRetrofit> items = resource.getResources();
                    for (ResourceRetrofit item : items) {

                        if(text.equals("category")){
                            if(!type.contains(item.getCategory()))
                                type.add(item.getCategory());
                        }
                        else if(text.equals("state")){
                            if(!type.contains(item.getState()))
                                type.add(item.getState());
                        }
                        else if(text.equals("city")){
                            if(!type.contains(item.getCity()))
                                type.add(item.getCity());
                        }
                        progressBar.setVisibility(View.INVISIBLE);
                        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(ResourcesActivity.this, android.R.layout.simple_list_item_1, type);
                        listView.setAdapter(arrayAdapter);
                    }

                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                            stateData(type.get(i).toLowerCase(), text);
                            progressBar.setVisibility(View.VISIBLE);
                            listView.setVisibility(View.INVISIBLE);
                            listView.setAdapter(null);
                        }
                    });
                }

                @Override
                public void onFailure(Call<Resource> call, Throwable t) {

//                textView.setText(t.getMessage());
                }
            });
        }
    }
}
