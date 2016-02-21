package de.xappo.myrxjava;

import retrofit.Callback;
import retrofit.RestAdapter;

/**
 * Created by knoppik on 21.02.16.
 */
public class GetWeatherRestAdapter {
    public static final String API_KEY = "a9d9bc5652b32376f245eaa77731336f";
    protected final String TAG = getClass().getSimpleName();
    protected RestAdapter mRestAdapter;
    protected GetWeatherApi mApi;
    static final String WEATHER_URL = "http://api.openweathermap.org";

    public GetWeatherRestAdapter() {
        mRestAdapter = new RestAdapter.Builder().setLogLevel(RestAdapter.LogLevel.FULL).setEndpoint(WEATHER_URL).build();
        mApi = mRestAdapter.create(GetWeatherApi.class);
    }

    public void testWeatherApi(String city, Callback<WeatherData> cb) {
        mApi.getWeatherFromApi(city, API_KEY, cb);
    }

    public WeatherData testWeatherApiSync(String city) {
        return mApi.getWeatherFromApiSync(city, API_KEY);
    }

}