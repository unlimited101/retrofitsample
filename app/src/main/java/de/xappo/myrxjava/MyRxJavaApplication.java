package de.xappo.myrxjava;

import android.app.Application;
import android.util.Log;

import java.util.Date;

import rx.Observable;
import rx.subjects.ReplaySubject;
import timber.log.Timber;

import static timber.log.Timber.DebugTree;

/**
 * Created by knoppik on 12.04.16.
 */
public class MyRxJavaApplication extends Application {

    private Observable<Date> dateObservable;
    private ReplaySubject<Date> dateSubject = ReplaySubject.create();

    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            Timber.plant(new DebugTree());
        } else {
            Timber.plant(new CrashReportingTree());
        }
    }

    public Observable<Date> getDateObservable() {
        return dateObservable;
    }

    public void createDateObservable(Date date) {
        dateObservable =
                Observable.create(sub -> {
                    sub.onNext(date);
                    sub.onCompleted();
                });
    }

    public ReplaySubject<Date> getReplaySubject() {
        return dateSubject;
    }

    /** A tree which logs important information for crash reporting. */
    private static class CrashReportingTree extends Timber.Tree {
        @Override
        protected void log(int priority, String tag, String message, Throwable t) {
            if (priority == Log.VERBOSE || priority == Log.DEBUG) {
                return;
            }

            FakeCrashLibrary.log(priority, tag, message);

            if (t != null) {
                if (priority == Log.ERROR) {
                    FakeCrashLibrary.logError(t);
                } else if (priority == Log.WARN) {
                    FakeCrashLibrary.logWarning(t);
                }
            }
        }
    }
}

