package com.example.guest.weathermeapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.weatherButton) Button mWeatherButton;
    @Bind(R.id.locationEditText) EditText mLocationEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mWeatherButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == mWeatherButton) {
            String zip = mLocationEditText.getText().toString();
            Intent intent = new Intent(MainActivity.this, WeatherList.class);
            intent.putExtra("zip", zip);
            startActivity(intent);
        }
    }
}
