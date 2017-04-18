package com.ecode.ocjdemo.base;

/**
 * Created by Administrator on 2017/4/14 0014.
 */

public abstract class BasePresenter<V> {
    public V mView;

    public void setView(V mView) {
        this.mView = mView;
        this.onAttach();
    }

    public abstract void onAttach();
}
