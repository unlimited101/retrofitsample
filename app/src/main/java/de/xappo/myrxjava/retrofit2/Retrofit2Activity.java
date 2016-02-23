package de.xappo.myrxjava.retrofit2;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import de.xappo.myrxjava.R;
import de.xappo.myrxjava.retrofit2.github.GithubRestAdapter;
import de.xappo.myrxjava.retrofit2.github.ReposResponseListener;

public class Retrofit2Activity extends AppCompatActivity {

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
    }

}
