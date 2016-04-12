package de.xappo.myrxjava;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import butterknife.ButterKnife;
import butterknife.OnClick;
import de.xappo.myrxjava.layout_behavior.ScrollingActivity;
import de.xappo.myrxjava.retrofit1_9.GetWeatherRestAdapter;
import de.xappo.myrxjava.retrofit1_9.WeatherData;
import de.xappo.myrxjava.retrofit2.Retrofit2Activity;
import de.xappo.myrxjava.rxjava.RxJavaEntryPointActivity;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";
    private GetWeatherRestAdapter mGetWeatherRestAdapter;
    private Callback<WeatherData> mWeatherDataCallback = new Callback<WeatherData>() {


        @Override
        public void success(WeatherData weatherData, Response response) {
            Log.d(TAG, "success: weatherData: name: " + weatherData.getName() + ", cod: " + weatherData.getCod());
        }

        @Override
        public void failure(RetrofitError error) {
            Log.e(TAG, "Failure: " + error);

        }
    };

    public void runRetrofitTestAsync() {
        if (mGetWeatherRestAdapter == null) {
            mGetWeatherRestAdapter = new GetWeatherRestAdapter();
        }
        mGetWeatherRestAdapter.testWeatherApi("Sunnyvale,USA", mWeatherDataCallback);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button startButton = (Button) findViewById(R.id.start_btn);

        startButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                runRetrofitTestAsync();
            }
        });

        Button retrofit2Button = (Button) findViewById(R.id.btn_retrofit2);
        retrofit2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Retrofit2Activity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });

    }

    public String longRunningOperation() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            // error
        }
        return "Complete!";
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("unused")
    @OnClick(R.id.btn_layout_behavior)
    public void buttonLayoutBehaviorClick() {
        Intent intent = new Intent(this, ScrollingActivity.class);
        startActivity(intent);
    }

    @SuppressWarnings("unused")
    @OnClick(R.id.btn_rx_java)
    public void buttonRxJavaClick() {
        Intent intent = new Intent(this, RxJavaEntryPointActivity.class);
        startActivity(intent);
    }

}
