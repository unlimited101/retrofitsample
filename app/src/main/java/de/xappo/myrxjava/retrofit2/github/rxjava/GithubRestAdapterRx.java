package de.xappo.myrxjava.retrofit2.github.rxjava;

import java.util.List;

import de.xappo.myrxjava.retrofit2.github.GithubApi;
import de.xappo.myrxjava.retrofit2.github.Repo;
import de.xappo.myrxjava.retrofit2.github.ReposResponseListener;
import retrofit2.Call;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;
import retrofit2.RxJavaCallAdapterFactory;

/**
 * Created by knoppik on 23.02.16.
 */

public class GithubRestAdapterRx {

    private GithubApiRx mGithubApiRx;


    public GithubRestAdapterRx() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        mGithubApiRx = retrofit.create(GithubApiRx.class);
    }

    public GithubApiRx getGithubApiRx() {
        return mGithubApiRx;
    }


}
