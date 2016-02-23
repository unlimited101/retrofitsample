package de.xappo.myrxjava.retrofit2.github;

import android.util.Log;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by knoppik on 23.02.16.
 */
public class ReposResponseListener implements Callback<List<Repo>> {

    @Override
    public void onResponse(Response<List<Repo>> response) {
        List<Repo> repos = response.body();
        for (Repo repo : repos) {
            Log.i(getClass().getName(), "repo: " + repo);
        }
    }

    @Override
    public void onFailure(Throwable t) {
        Log.e(getClass().getName(), "Throwable: " + t);
    }
}
