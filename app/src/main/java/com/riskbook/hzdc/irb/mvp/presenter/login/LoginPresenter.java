package com.riskbook.hzdc.irb.mvp.presenter.login;

import android.text.TextUtils;
import android.widget.EditText;

import com.riskbook.hzdc.irb.R;
import com.riskbook.hzdc.irb.bean.LoginBean;
import com.riskbook.hzdc.irb.mvp.presenter.basePresenter.BasePresenter;
import com.riskbook.hzdc.irb.mvp.view.login.LoginView;
import com.riskbook.hzdc.irb.net.BaseApiResponse;
import com.riskbook.hzdc.irb.net.BaseObserver;
import com.riskbook.hzdc.irb.net.exception.ResponseException;
import com.riskbook.hzdc.irb.utils.DataCheckUtils;
import com.riskbook.hzdc.irb.utils.MLog;

import io.reactivex.disposables.Disposable;

/**
 * Created by zenghaiqiang on 2019/1/16.
 */

public class LoginPresenter extends BasePresenter<LoginView> {


    public LoginPresenter(LoginView view) {
        super(view);
    }


    /**
     * 手机验证码登录
     *
     * @param mobilePhone
     * @param verificationCode
     */
    public void requestLogin(String mobilePhone, String verificationCode, boolean isChecked) {

        if (TextUtils.isEmpty(mobilePhone)) {
            //请输入手机号
            mView.toastMessage(R.string.hint_input_phone_num);
            return;
        } else if (!DataCheckUtils.checkPhone(mobilePhone)) {
            mView.toastMessage(R.string.msg_phone_num_error);
            return;
        }
        if (null == verificationCode || verificationCode.length() != 6) {
            mView.toastMessage(R.string.msg_verification_code_error);
            return;
        }
        if (!isChecked) {
            mView.toastMessage(R.string.read_user_agreement);
            return;
        }
        addSubscription(
                apiStores.requestLogin(mobilePhone, verificationCode),
                new BaseObserver<BaseApiResponse<LoginBean>>() {
                    @Override
                    public void onNext(BaseApiResponse<LoginBean> data) {
                        if (data.getData() == null) {
                            mView.toastMessage(R.string.please_get_code);
                        } else {
                            mView.loginSuccess(data.getData());
                        }
                    }

                    @Override
                    public void onError(ResponseException e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {
                        MLog.d("SelfSelectPresenter onComplete:");
                    }
                }
        );
    }


    //发送手机验证码的方法
    public void sendVerificationCode(String mobilePhone, EditText etCode) {
        if (TextUtils.isEmpty(mobilePhone)) {
            //请输入手机号
            mView.toastMessage(R.string.hint_input_phone_num);
            return;
        } else if (!DataCheckUtils.checkPhone(mobilePhone)) {
            mView.toastMessage(R.string.msg_phone_num_error);
            return;
        }
        //改变按钮的点击状态,倒计时
        mView.changeBtnStatus();

        //发送手机验证码
        addSubscription(
                apiStores.requestVerificationCode(mobilePhone),
                new BaseObserver<BaseApiResponse<String>>() {

                    @Override
                    public void onSubscribe(Disposable d) {
                        super.onSubscribe(d);
                    }

                    @Override
                    public void onNext(BaseApiResponse<String> data) {
                        if (!TextUtils.isEmpty(data.getData().toString())) {
                            mView.toastMessage(R.string.get_success);
                            etCode.setText(data.getData().toString());
                        }
                    }

                    @Override
                    public void onError(ResponseException e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {

                    }
                }
        );
    }

}
