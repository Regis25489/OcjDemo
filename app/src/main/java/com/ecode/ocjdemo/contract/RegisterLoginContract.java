package com.ecode.ocjdemo.contract;

import com.ecode.ocjdemo.base.BasePresenter;
import com.ecode.ocjdemo.base.BaseView;

/**
 * Created by Administrator on 2017/4/14 0014.
 */

public interface RegisterLoginContract {

    interface RegisterView extends BaseView {

        void startCountTime();

        void countingTime(Long time);

        boolean checkParams();

        void registerSuccess();

        void registerFail();
    }

    abstract class RegisterPresenter extends BasePresenter<RegisterView> {
        public abstract void getVerifyCode(int time);

        public abstract void register();
    }

    interface LoginView extends RegisterView {
    }

    abstract class LoginPresenter extends RegisterPresenter {

    }
}
