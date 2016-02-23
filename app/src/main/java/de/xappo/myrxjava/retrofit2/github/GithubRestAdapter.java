package de.xappo.myrxjava.retrofit2.github;

import java.util.List;

import retrofit2.Call;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;

/**
 * Created by knoppik on 23.02.16.
 */

public class GithubRestAdapter {

    private GithubApi mGithubApi;
    private ReposResponseListener mResponseListener;

    public GithubRestAdapter(ReposResponseListener responseListener) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mGithubApi = retrofit.create(GithubApi.class);
        mResponseListener = responseListener;
    }

    public void callGetRepos(String user) {
        Call<List<Repo>> call = mGithubApi.listRepos(user);
        call.enqueue(mResponseListener);
    }


}
