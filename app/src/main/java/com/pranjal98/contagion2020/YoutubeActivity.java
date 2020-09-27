package com.pranjal98.contagion2020;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragmentX;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class YoutubeActivity extends AppCompatActivity {
    private YoutubeApi api;
    private Button b0, b1, b2, b3, b4, b5, b6, b7, b8, b9, s1, s2, s3, s4, s5, c1, c2, c3, c4;
    private SearchView searchView;
    private List<String> videoList, channelList;
    private String query, video;
    private static int flag = 0, check = 0;
    private TextView textView;

    private String trand1, trand2, trand3, trand4, trand5;
    int viewCount, tranding1, tranding2, tranding3, tranding4, tranding5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube);

        Button button = findViewById(R.id.backButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        query = "";
        viewCount = 1;
        tranding1 = 0;
        tranding2 = 0;
        tranding3 = 0;
        tranding4 = 0;
        tranding5 = 0;

        b0 = findViewById(R.id.button);
        b1 = findViewById(R.id.button1);
        b2 = findViewById(R.id.button2);
        b3 = findViewById(R.id.button3);
        b4 = findViewById(R.id.button4);
        b5 = findViewById(R.id.button5);
        b6 = findViewById(R.id.button6);
        b7 = findViewById(R.id.button7);
        b8 = findViewById(R.id.button8);
        b9 = findViewById(R.id.button9);

        c1 = findViewById(R.id.punjabi);
        c2 = findViewById(R.id.gujarati);
        c3 = findViewById(R.id.bengali);
        c4 = findViewById(R.id.kashmiri);

        s1 = findViewById(R.id.bsearch1);
        s2 = findViewById(R.id.bsearch2);
        s3 = findViewById(R.id.bsearch3);
        s4 = findViewById(R.id.bsearch4);
        s5 = findViewById(R.id.bsearch5);

        textView = findViewById(R.id.top_searches);

        if (flag == 0) {
            s1.setVisibility(View.GONE);
            s2.setVisibility(View.GONE);
            s3.setVisibility(View.GONE);
            s4.setVisibility(View.GONE);
            s5.setVisibility(View.GONE);
            textView.setVisibility(View.GONE);
        }

        searchView = findViewById(R.id.search_youtube);

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.googleapis.com/youtube/v3/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        api = retrofit.create(YoutubeApi.class);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                query = searchView.getQuery().toString().toLowerCase();
                check = 1;
                getVideos();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });

        s1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                video = videoList.get(0);
                playVideo();
            }
        });
        s2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                video = videoList.get(1);
                playVideo();
            }
        });
        s3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                video = videoList.get(2);
                playVideo();
            }
        });
        s4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                video = videoList.get(3);
                playVideo();
            }
        });
        s5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                video = videoList.get(4);
                playVideo();
            }
        });
        b0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                query = b0.getText().toString();
                check = 1;
                getVideos();
                //playVideo();
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                query = b1.getText().toString() + "News";
                check = 1;
                getVideos();
                //playVideo();
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                query = b2.getText().toString() + "News";
                check = 1;
                getVideos();
                //playVideo();
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                query = b3.getText().toString() + "News";
                check = 1;
                getVideos();
                //playVideo();
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                query = b4.getText().toString() + "News";
                check = 1;
                getVideos();
                //playVideo();
            }
        });
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                query = b5.getText().toString() + "News";
                check = 1;
                getVideos();
                //playVideo();
            }
        });
        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                query = b6.getText().toString() + "News";
                check = 1;
                getVideos();
                //playVideo();
            }
        });
        b7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                query = b7.getText().toString() + "News";
                check = 1;
                getVideos();
                //playVideo();
            }
        });
        b8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                query = b8.getText().toString() + "News";
                check = 1;
                getVideos();
                //playVideo();
            }
        });
        b9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                query = b9.getText().toString() + "News";
                check = 1;
                getVideos();
                //playVideo();
            }
        });
        c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                query = "ABP news channel";
                check = 1;
                getVideos();
                //playVideo();
            }
        });
        c2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                query = "ZEE news channel";
                check = 1;
                getVideos();
                //playVideo();
            }
        });
        c3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                query = "Aaj Tak news channel";
                check = 1;
                getVideos();
                //playVideo();
            }
        });
        c4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                query = "India Today news channel";
                check = 1;
                getVideos();
                //playVideo();
            }
        });
    }

    public void getVideos() {

        if (query.equals(""))
            return;
        Call<YoutubeRetrofit> call = api.getVideos("snippet",query + "corona", YouTubeConfig.getGetApiKey(), "video");

        call.enqueue(new Callback<YoutubeRetrofit>() {
            @Override
            public void onResponse(Call<YoutubeRetrofit> call, Response<YoutubeRetrofit> response) {
                if (!response.isSuccessful()) {
                    b1.setText("Code: " + response.code());
                    return;
                }

                videoList = new ArrayList<>();
                channelList = new ArrayList<>();
                YoutubeRetrofit youTubeRetrofit = response.body();
                List<ItemRetrofitY> items = youTubeRetrofit.getItems();
                for (ItemRetrofitY item : items) {
                    VideoRetrofit videoId = item.getId();
                    SnippetRetrofit snippet = item.getSnippet();

                    videoList.add(videoId.getVideoId());
                    channelList.add(snippet.getChannelTitle());
                }

                if (flag == 0) {
                    s1.setVisibility(View.VISIBLE);
                    s2.setVisibility(View.VISIBLE);
                    s3.setVisibility(View.VISIBLE);
                    s4.setVisibility(View.VISIBLE);
                    s5.setVisibility(View.VISIBLE);
                    textView.setVisibility(View.VISIBLE);
                }

                s1.setText(channelList.get(0));
                s2.setText(channelList.get(1));
                s3.setText(channelList.get(2));
                s4.setText(channelList.get(3));
                s5.setText(channelList.get(4));

                if (check == 1) {
                    video = videoList.get(0);
                    check = 0;
                    playVideo();
                }
            }

            @Override
            public void onFailure(Call<YoutubeRetrofit> call, Throwable t) {
                b1.setText(t.getMessage());
            }
        });

    }

    public void playVideo(){

        YouTubePlayerSupportFragmentX youTubePlayerFragment = YouTubePlayerSupportFragmentX.newInstance();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.youtube_view, youTubePlayerFragment).commit();

        youTubePlayerFragment.initialize(YouTubeConfig.getGetApiKey(), new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                youTubePlayer.loadVideo(video);
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

                String errorMessage = youTubeInitializationResult.toString();
                Toast.makeText(YoutubeActivity.this, errorMessage, Toast.LENGTH_LONG).show();
                Log.d("errorMessage:", errorMessage);
            }
        });
    }

}
