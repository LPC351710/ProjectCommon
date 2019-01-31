package com.ppm.rxjava;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private Disposable mDisposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        init();
        async();
    }

    private void init() {
        Observable<String> novel = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                emitter.onNext("EmitterOne");
                emitter.onNext("EmitterTwo");
                emitter.onNext("EmitterThree");
                emitter.onComplete();
            }
        });

        Observer<String> observer = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable dispose) {
                mDisposable = dispose;
                Log.d("rxJava", "onSubscribe");
            }

            @Override
            public void onNext(String value) {
                if ("2".equals(value)) {
                    mDisposable.dispose();
                    return;
                }
                Log.d("rxJava", "onNext: " + value);
            }

            @Override
            public void onError(Throwable e) {
                Log.d("rxJava", "onError");
            }

            @Override
            public void onComplete() {
                Log.d("rxJava", "onComplete");
            }
        };

        novel.subscribe(observer);
    }

    private void async() {
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                Log.d("rxJava", "thread: " + Thread.currentThread().getName());
                emitter.onNext("emitter1");
                emitter.onNext("emitter2");
                emitter.onNext("emitter3");
                emitter.onComplete();
            }
        })
                .observeOn(AndroidSchedulers.mainThread()) //主线程回调
                .subscribeOn(Schedulers.io()) //io线程执行
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d("rxJava", "onSubscribe");
                    }

                    @Override
                    public void onNext(String s) {
                        Log.d("rxJava", "onNext:" + s);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("rxJava", "onError: " + e.getLocalizedMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.d("rxJava", "onComplete");
                    }
                });
    }

    private void rxAndroid() {
        Observable<String> observable = Observable.just("Hello RxAndroid");
        Consumer<String> consumer = new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.d("rxAndroid", "s: " + s);
            }
        };

        Disposable disposable = observable.subscribe(consumer);
    }

    private void rxAndroid1() {
        Observable<String> observable = Observable.just("Hello RxAndroid");
        Action completeAction = new Action() {
            @Override
            public void run() throws Exception {

            }
        };

        Consumer<String> nextConsumer = new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {

            }
        };

        Consumer<Throwable> errorConsumer = new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {

            }
        };

        Disposable disposable = observable.subscribe(nextConsumer, errorConsumer, completeAction);
    }

    private void rxAndroid2() {
        Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
            }
        });

        Consumer<Integer> consumer = new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {

            }
        };

        Disposable disposable = observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(consumer);
    }

    private void rxAndroid3() {
        Disposable disposable = Observable.just(getDrawableFromUrl())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Drawable>() {
                    @Override
                    public void accept(Drawable drawable) throws Exception {

                    }
                });
    }

    private Drawable getDrawableFromUrl() {
        try {
            Thread.sleep(60_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return getResources().getDrawable(R.drawable.ic_launcher_background);
    }

    private void rxAndroid4() {
        Disposable disposable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {

            }
        }).map(new Function<Integer, String>() {
            @Override
            public String apply(Integer integer) throws Exception {
                return null;
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {

            }
        });
    }

    private void rxAndroid5() {
        Disposable disposable = Observable.just(getDrawableFromUrl())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<Drawable, Bitmap>() {
                    @Override
                    public Bitmap apply(Drawable drawable) throws Exception {
                        BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
                        return bitmapDrawable.getBitmap();
                    }
                }).subscribe(new Consumer<Bitmap>() {
                    @Override
                    public void accept(Bitmap bitmap) throws Exception {

                    }
                });
    }

    private void rxAndroid6() {

    }
}