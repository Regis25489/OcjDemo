package com.ecode.ocjdemo;

import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ecode.ocjdemo.base.BaseActivity;
import com.ecode.ocjdemo.contract.RegisterLoginContract;
import com.ecode.ocjdemo.presenter.LoginPresenterImpl;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;

/**
 * Created by Administrator on 2017/4/14 0014.
 */

public class LoginActivity extends BaseActivity<RegisterLoginContract.LoginPresenter> implements RegisterLoginContract.LoginView {
    @BindView(R.id.btn_close)
    ImageButton btnClose;
    @BindView(R.id.title_bar)
    RelativeLayout titleBar;
    @BindView(R.id.logo)
    ImageView logo;
    @BindView(R.id.number)
    EditText number;
    @BindView(R.id.btn_num_clear)
    ImageButton btnNumClear;
    @BindView(R.id.pwd)
    EditText pwd;
    @BindView(R.id.btn_pwd_clear)
    ImageButton btnPwdClear;
    @BindView(R.id.btn_pwd_hide)
    ImageButton btnPwdHide;
    @BindView(R.id.verify_code)
    EditText verifyCode;
    @BindView(R.id.btn_code_clear)
    ImageButton btnCodeClear;
    @BindView(R.id.btn_get_code)
    TextView btnGetCode;
    @BindView(R.id.name)
    EditText name;
    @BindView(R.id.btn_name_clear)
    ImageButton btnNameClear;
    @BindView(R.id.btn_register)
    Button btnRegister;
    public int countTime = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        getSupportActionBar().hide();
        presenter = new LoginPresenterImpl();
        presenter.setView(this);
    }


    @OnTextChanged(value = R.id.number, callback = OnTextChanged.Callback.TEXT_CHANGED)
    public void onNumChanged(CharSequence s) {
        if (s != null && s.length() > 0) {
            btnNumClear.setVisibility(View.VISIBLE);
        } else {
            btnNumClear.setVisibility(View.INVISIBLE);
        }
    }

    @OnTextChanged(value = R.id.verify_code, callback = OnTextChanged.Callback.TEXT_CHANGED)
    public void onCodeChanged(CharSequence s) {
        if (s != null && s.length() > 0) {
            btnCodeClear.setVisibility(View.VISIBLE);
        } else {
            btnCodeClear.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void startCountTime() {
        btnGetCode.setEnabled(false);
        btnGetCode.setTextColor(Color.parseColor("#666666"));
        btnGetCode.setBackgroundResource(R.drawable.btn_verify_code_retry);
    }

    @Override
    public void countingTime(Long time) {
        btnGetCode.setText(time + " 重新发送");
    }

    @OnClick({R.id.btn_close, R.id.btn_code_clear, R.id.btn_num_clear, R.id.btn_register, R.id.btn_get_code})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_close:
                closePage();
                break;
            case R.id.btn_code_clear:
                verifyCode.setText("");
                break;
            case R.id.btn_num_clear:
                number.setText("");
                break;
            case R.id.btn_get_code:
                presenter.getVerifyCode(countTime);
                break;
            case R.id.btn_register:
                presenter.register();
                break;
            default:
        }
    }

    public boolean checkParams() {
        if (TextUtils.isEmpty(number.getText())) {
            showMsg("请输入手机号");
            return false;
        }
        if (TextUtils.isEmpty(verifyCode.getText())) {
            showMsg("请输入验证码");
            return false;
        }
        showMsg("注册");
        return true;
    }


    @Override
    public void registerSuccess() {
        btnGetCode.setEnabled(true);
        btnGetCode.setTextColor(Color.parseColor("#E5290D"));
        btnGetCode.setBackgroundResource(R.drawable.btn_verify_code);
        btnGetCode.setText("获取验证码");
        showMsg("验证码获取成功！");
    }

    @Override
    public void registerFail() {
        btnGetCode.setEnabled(true);
        btnGetCode.setTextColor(Color.parseColor("#E5290D"));
        btnGetCode.setBackgroundResource(R.drawable.btn_verify_code);
        btnGetCode.setText("获取验证码");
        showMsg("验证码获取失败！");
    }
}
