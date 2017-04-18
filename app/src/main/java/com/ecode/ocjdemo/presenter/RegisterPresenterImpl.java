package com.ecode.ocjdemo.presenter;

import com.ecode.ocjdemo.contract.RegisterLoginContract;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

/**
 * Created by Administrator on 2017/4/14 0014.
 */

public class RegisterPresenterImpl extends RegisterLoginContract.RegisterPresenter{

    @Override
    public void onAttach() {

    }

    @Override
    public void getVerifyCode(final int countTime) {
        Observable.interval(0, 1, TimeUnit.SECONDS)
                .take(countTime + 1)
                .map(new Function<Long, Long>() {
                    @Override
                    public Long apply(Long aLong) throws Exception {
                        return countTime - aLong;
                    }
                })
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        mView.startCountTime();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(Long value) {
                        mView.countingTime(value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.registerFail();
                    }

                    @Override
                    public void onComplete() {
                        mView.registerSuccess();
                    }
                });
    }

    @Override
    public void register() {
        mView.checkParams();
    }

}
