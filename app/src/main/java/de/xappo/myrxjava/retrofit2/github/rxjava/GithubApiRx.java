package de.xappo.myrxjava.retrofit2.github.rxjava;

import java.util.List;

import de.xappo.myrxjava.retrofit2.github.Repo;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by knoppik on 23.02.16.
 */
public interface GithubApiRx {
    @GET("users/{user}/repos")
    Observable<List<Repo>> listRepos(@Path("user") String user);
}
