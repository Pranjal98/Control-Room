package com.pranjal98.contagion2020;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertPathBuilder;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.List;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NewsActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener{

    RecyclerView recyclerView;
    SwipeRefreshLayout swipeRefreshLayout;

    NewsApi newsApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        Button button = findViewById(R.id.backButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        swipeRefreshLayout = findViewById(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);

        recyclerView = findViewById(R.id.recycler_view_requests);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://newsapi.org/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(getUnsafeOkHttpClient().build())
                .build();

        newsApi = retrofit.create(NewsApi.class);

        onLoadingSwipeRefresh("", "");
    }

    public void LoadJson(final String keyword, String country){

        swipeRefreshLayout.setRefreshing(true);

        Call<News> call = newsApi.getNews(country,"covid-19","ba1d0e9caca44798b5ac1823639d5ace");

        call.enqueue(new Callback<News>() {
            @Override
            public void onResponse(Call<News> call, Response<News> response) {

                if(!response.isSuccessful()){
//                    textView.setText("code : " + response.code());
                    return;
                }

                News state = response.body();

                List<Article> items = state.getArticles();

                RecyclerView.Adapter adapter = new NewsRecyclerViewAdapter(items, NewsActivity.this, 2);
                recyclerView.setAdapter(adapter);
                swipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onFailure(Call<News> call, Throwable t) {

                swipeRefreshLayout.setRefreshing(true);
//                textView.setText(t.getMessage());
            }
        });
    }

    public static OkHttpClient.Builder getUnsafeOkHttpClient(){

        try {
            final TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
                        }

                        @Override
                        public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
                        }

                        @Override
                        public X509Certificate[] getAcceptedIssuers() {
                            return new java.security.cert.X509Certificate[]{};
                        }
                    }
            };

            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());


            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.sslSocketFactory(sslSocketFactory, (X509TrustManager) trustAllCerts[0]);
            builder.hostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String s, SSLSession sslSession) {
                    return true;
                }
            });

            return builder;

        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onRefresh() {
        LoadJson("", "");
    }

    private void onLoadingSwipeRefresh(final String keyword, String country){

        swipeRefreshLayout.post(
                new Runnable() {
                    @Override
                    public void run() {
                        LoadJson(keyword, country);
                    }
                }
        );
    }

    public void showMenu(View v) {

        PopupMenu popup = new PopupMenu(this, v);
        // This activity implements OnMenuItemClickListener
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.all:
                        onLoadingSwipeRefresh("", "");
                        return true;
                    case R.id.indian:
                        onLoadingSwipeRefresh("", "in");
                        return true;
                    default:
                        return false;
                }
            }
        });
        popup.inflate(R.menu.news_menu);
        popup.show();
    }

}
