package de.xappo.myrxjava.rxjava;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import butterknife.ButterKnife;
import butterknife.OnClick;
import de.xappo.myrxjava.R;

public class RxJavaEntryPointActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java_entry_point);
        ButterKnife.bind(this);
    }

    @SuppressWarnings("unused")
    @OnClick(R.id.btn_go_to_rxjava_receive_activity)
    public void buttonRxJavaObserveSequantiallyFromOtherActivityClick() {
        Intent intent = new Intent(this, RxJavaReceiveActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        startActivity(intent);
    }

    @SuppressWarnings("unused")
    @OnClick(R.id.btn_go_to_rxjava_async_basics_activity)
    public void buttonRxJavaAsyncsBasicActivityClick() {
        Intent intent = new Intent(this, RxJavaAsyncBasics.class);
        startActivity(intent);
    }

    @SuppressWarnings("unused")
    @OnClick(R.id.btn_go_to_rxjava_operators_activity)
    public void buttonRxJavaOperatorsActivityClick() {
        Intent intent = new Intent(this, RxJavaOperatorsActivity.class);
        startActivity(intent);
    }
}
