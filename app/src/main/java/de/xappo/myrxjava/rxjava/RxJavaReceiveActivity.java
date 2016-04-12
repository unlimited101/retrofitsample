package de.xappo.myrxjava.rxjava;

import android.content.Intent;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ThemedSpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;

import butterknife.Bind;
import butterknife.BindString;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.xappo.myrxjava.MyRxJavaApplication;
import de.xappo.myrxjava.R;
import rx.Subscriber;
import timber.log.Timber;

public class RxJavaReceiveActivity extends AppCompatActivity {

    @Bind(R.id.textview_date)
    protected TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java_receive);
        ButterKnife.bind(this);
        Timber.i("thread id: %d", Thread.currentThread().getId());
    }

    @BindString(R.string.textview_date)
    String dateStr;

    Subscriber<Date> dateSubscriber = new Subscriber<Date>() {

        @Override
        public void onCompleted() {
            Toast.makeText(RxJavaReceiveActivity.this, "Receiving completed!", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(Date date) {
            Timber.i("Received date: %s", date.toString());
            Timber.i("thread id: %d", Thread.currentThread().getId());
            mTextView.setText(String.format(dateStr, date.toString()));
            //mTextView.setText("xxx");
        }
    };

    @SuppressWarnings("unused")
    @OnClick(R.id.btn_go_to_rx_java_emit_sequentially)
    public void buttonGoToRxEmitSequantiallyClick() {
        Intent intent = new Intent(this, RxJavaEmitSequentiallyActivity.class);
        startActivity(intent);
    }

    @SuppressWarnings("unused")
    @OnClick(R.id.btn_receive_date_manually)
    public void buttonReceiveDateManuallyClick() {
        ((MyRxJavaApplication)getApplication()).getDateObservable().subscribe(dateSubscriber);
    }


}
