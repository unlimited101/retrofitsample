package de.xappo.myrxjava.rxjava;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import butterknife.ButterKnife;
import butterknife.OnClick;
import de.xappo.myrxjava.R;

public class RxJavaEmitSequentiallyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java_emit_sequentially);
        ButterKnife.bind(this);
    }

    @SuppressWarnings("unused")
    @OnClick(R.id.btn_rx_java_emit_sequentially)
    public void buttonRxEmitSequantiallyClick() {
        finish();
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @SuppressWarnings("unused")
    @OnClick(R.id.btn_close_all_new_tasks)
    public void buttonCloseAllNewTasksClick() {
        finishAffinity();
    }

}
