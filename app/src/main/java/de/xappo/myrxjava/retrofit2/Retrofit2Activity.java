package de.xappo.myrxjava.retrofit2;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.List;

import de.xappo.myrxjava.R;
import de.xappo.myrxjava.retrofit2.github.GithubRestAdapter;
import de.xappo.myrxjava.retrofit2.github.Repo;
import de.xappo.myrxjava.retrofit2.github.ReposResponseListener;
import de.xappo.myrxjava.retrofit2.github.rxjava.GithubRestAdapterRx;
import retrofit2.HttpException;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class Retrofit2Activity extends AppCompatActivity {

    private Subscriber<Repo> mSubscriber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Making request on Github Api", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                ReposResponseListener reposResponseListener = new ReposResponseListener();
                GithubRestAdapter githubRestAdapter = new GithubRestAdapter(reposResponseListener);
                githubRestAdapter.callGetRepos("unlimited101");
            }
        });


        mSubscriber = new Subscriber<Repo>() {

            @Override
            public void onCompleted() {
                Log.i(getClass().getName(), "Repos completed!");
            }

            @Override
            public void onError(Throwable e) { }

            @Override
            public void onNext(Repo repo) {
                Log.i(getClass().getName(), "receiving repo: " + repo);

            }
        };

        Button buttonReceive = (Button) findViewById(R.id.btn_receive);
        buttonReceive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ObservableManager.getInstance().getObservable().subscribe(mSubscriber);
            }
        });

        Button buttonReceiveRx = (Button) findViewById(R.id.btn_receive_rx);
        buttonReceive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GithubRestAdapterRx githubRestAdapterRx = new GithubRestAdapterRx();
                Observable<List<Repo>> call = githubRestAdapterRx.getGithubApiRx().listRepos("unlimited101");
                Subscription subscription = call
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<List<Repo>>() {
                            @Override
                            public void onCompleted() {

                            }

                            @Override
                            public void onError(Throwable e) {
                                // cast to retrofit.HttpException to get the response code
                                if (e instanceof HttpException) {
                                    HttpException response = (HttpException)e;
                                    int code = response.code();
                                }
                            }

                            @Override
                            public void onNext(List<Repo> repos) {
                                for (Repo repo : repos) {
                                    Log.i(getClass().getName(), "RetrofitRX - repo: " + repo);
                                }
                            }
                        });

            }
        });

    }

}
