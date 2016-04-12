package de.xappo.myrxjava.rxjava;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import butterknife.ButterKnife;
import butterknife.OnClick;
import de.xappo.myrxjava.R;

public class RxJavaReceiveActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java_receive);
        ButterKnife.bind(this);
    }

    @SuppressWarnings("unused")
    @OnClick(R.id.btn_go_to_rx_java_emit_sequentially)
    public void buttonGoToRxEmitSequantiallyClick() {
        Intent intent = new Intent(this, RxJavaEmitSequentiallyActivity.class);
        startActivity(intent);
    }
}
