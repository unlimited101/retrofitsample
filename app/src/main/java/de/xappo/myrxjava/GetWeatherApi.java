package de.xappo.myrxjava;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by knoppik on 21.02.16.
 */
public interface GetWeatherApi {
    @GET("/data/2.5/weather")
    void getWeatherFromApi(@Query("q") String cityName, @Query("APPID") String appId, Callback<WeatherData> callback);
    @GET("/data/2.5/weather")
    WeatherData getWeatherFromApiSync(@Query("q") String cityName, @Query("APPID") String appId);

}
