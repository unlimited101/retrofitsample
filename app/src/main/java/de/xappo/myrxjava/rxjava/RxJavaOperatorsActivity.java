package de.xappo.myrxjava.rxjava;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;
import de.xappo.myrxjava.R;
import rx.Observable;
import rx.Subscriber;
import timber.log.Timber;

public class RxJavaOperatorsActivity extends AppCompatActivity {

    private String http = "http://";
    private String https = "https://";
    private String de = ".de";
    private String com = ".com";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java_operators);
        ButterKnife.bind(this);
    }

    private Observable<List<String>> query(String text) {
        List<String> strList = new ArrayList<String>();
        strList.add("http://www.zdf.de");
        strList.add("http://www.google.com");
        strList.add("http://www.heise.de");
        strList.add("https://www.mk-xappo.de");
        Observable<List<String>> strListObservable = Observable.create(
                new Observable.OnSubscribe<List<String>>() {
                    @Override
                    public void call(Subscriber<? super List<String>> sub) {
                        sub.onNext(strList);
                        sub.onCompleted();
                    }
                }
        );
        return strListObservable;
    }

    // Returns the title of a website, or null if 404
    private Observable<String> getTitle(String url) {
        String title = url;
        if (url.startsWith(http)) {
            title = title.replaceAll(http, "");
        } else if (url.startsWith(https)) {
            title = title.replaceAll(https, "");
        }
        if (url.endsWith(de)) {
            title = title.replaceAll(de, "");
        } else if (url.endsWith(com)) {
            title = title.replaceAll(com, "");
        }
        title = title.replaceAll("www.", "");
        final String finalTitle = title;
        Observable<String> strObservable = Observable.create(
                new Observable.OnSubscribe<String>() {
                    @Override
                    public void call(Subscriber<? super String> sub) {
                        sub.onNext(finalTitle);
                        sub.onCompleted();
                    }
                }
        );
        return strObservable;
    }

    @SuppressWarnings("unused")
    @OnClick(R.id.btn_rx_java_operators)
    public void buttonRxJavaOperatorsClick() {
        Observable<String> s = Observable.from(new String[]{"x"});

        // This
        query("Hello, world!")
                .flatMap(urls -> Observable.from(urls))
                .filter(url -> url.endsWith(".de"))
                .subscribe(url -> Timber.i(url));
        // is equivalent to
        query("Hello, world!")
                .flatMap(Observable::from)
                .filter(url -> url.endsWith(".de"))
                .subscribe(Timber::i);

        query("Hello, world!")
                .flatMap(Observable::from)
                .filter(url -> url.endsWith(".de"))
                .filter(url -> url.startsWith("http://"))
                .subscribe(Timber::i);

        query("Hello, world!")
                .flatMap(urls -> Observable.from(urls))
                .flatMap(url -> getTitle(url))
                .subscribe(title -> Timber.i(title));

        Observable<String> q1 = query("Hello, world!")
                .flatMap(urls -> Observable.from(urls))
                .flatMap(url -> getTitle(url));

        Observable<Observable<String>> q2 = query("Hello, world!")
                .flatMap(urls -> Observable.from(urls))
                .map(url -> getTitle(url));


    }
}
