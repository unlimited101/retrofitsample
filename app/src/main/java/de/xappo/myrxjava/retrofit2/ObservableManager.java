package de.xappo.myrxjava.retrofit2;

import java.util.List;

import de.xappo.myrxjava.retrofit2.github.Repo;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by knoppik on 23.02.16.
 */
public class ObservableManager {

    private static ObservableManager sInstance = null;
    private Observable<Repo> mObservable;

    private ObservableManager() {

    }

    public static ObservableManager getInstance() {
        if (sInstance == null) {
            sInstance = new ObservableManager();
        }
        return sInstance;
    }

    public void emitItems(final List<Repo> repos) {
        mObservable = Observable.create(
                new Observable.OnSubscribe<Repo>() {
                    @Override
                    public void call(Subscriber<? super Repo> sub) {
                        for (Repo repo : repos) {
                            sub.onNext(repo);
                        }
                        sub.onCompleted();
                    }
                }
        );
    }

    public Observable<Repo> getObservable() {
        return mObservable;
    }
}
