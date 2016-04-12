package de.xappo.myrxjava.rxjava;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.Date;

import butterknife.ButterKnife;
import butterknife.OnClick;
import de.xappo.myrxjava.R;
import timber.log.Timber;

public class RxJavaEmitSequentiallyActivity extends AppCompatActivity {

    Date mDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java_emit_sequentially);
        ButterKnife.bind(this);
    }

    @SuppressWarnings("unused")
    @OnClick(R.id.btn_create_date)
    public void buttonCreateDateClick() {
        mDate = new Date();
        Timber.i("Date is: %s", mDate.toString());
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
