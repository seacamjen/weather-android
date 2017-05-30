package com.example.guest.weathermeapp.services;


import android.util.Log;

import com.example.guest.weathermeapp.Constants;
import com.example.guest.weathermeapp.models.Weather;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import okhttp3.Callback;

import okhttp3.Call;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class WeatherService {
    public static final String TAG = WeatherService.class.getSimpleName();

    public static void findWeather(String zip, Callback callback) {
        OkHttpClient client = new OkHttpClient.Builder()
                .build();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.API_BASE_URL).newBuilder();
        urlBuilder.addQueryParameter(Constants.YOUR_QUERY_PARAMETER, zip);
        urlBuilder.addQueryParameter(Constants.API_KEY_QUERY_PARAMETER, Constants.API_KEY);
        urlBuilder.addQueryParameter(Constants.API_KEY_QUERY_IMPERIAL, Constants.API_KEY_IMPERIAL);
        String url = urlBuilder.build().toString();

        Log.d("url", url);

        Request request = new Request.Builder()
                .url(url)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);
    }

    public ArrayList<Weather> processResults(Response response) {
        ArrayList<Weather> weather = new ArrayList<>();

        try {

            if (response.isSuccessful()) {
                String jsonData = response.body().string();
                JSONObject rainJSON = new JSONObject(jsonData);
                String description = rainJSON.getJSONArray("weather").getJSONObject(0).getString("description");
                double temp = rainJSON.getJSONObject("main").getDouble("temp");
                String city = rainJSON.getString("name");

                Weather instanceOf = new Weather(description, temp, city);
                weather.add(instanceOf);


//                JSONObject weatherJSON = new JSONObject(jsonData);
//                JSONArray listJSON = weatherJSON.getJSONArray("list");
//                for (int i = 0; i < listJSON.length(); i++) {
//                    JSONObject sunnyJSON = listJSON.getJSONObject(i);
//                    String description = sunnyJSON.getJSONObject("weather").getString("description");
//                    String temp = sunnyJSON.getJSONObject("main").getString("temp");
//                    String city = sunnyJSON.getString("name");
//
//                    Weather instanceOfWeather = new Weather(description, temp, city);
//                    weather.add(instanceOfWeather);
//                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return weather;
    }


}
