package com.pranjal98.contagion2020;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Covid19Activity extends AppCompatActivity {

    Button knowMore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_covid19);

        knowMore = findViewById(R.id.knowMore);
        knowMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Covid19Activity.this, MainActivity.class));
            }
        });
    }
}
