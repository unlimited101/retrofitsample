package de.xappo.myrxjava.retrofit2;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import de.xappo.myrxjava.R;
import de.xappo.myrxjava.retrofit2.github.GithubRestAdapter;
import de.xappo.myrxjava.retrofit2.github.Repo;
import de.xappo.myrxjava.retrofit2.github.ReposResponseListener;
import rx.Subscriber;

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
    }

}
