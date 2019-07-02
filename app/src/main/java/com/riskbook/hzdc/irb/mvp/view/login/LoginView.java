package com.riskbook.hzdc.irb.mvp.view.login;

import com.riskbook.hzdc.irb.bean.LoginBean;
import com.riskbook.hzdc.irb.mvp.view.IView;

/**
 * Created by zenghaiqiang on 2019/1/16.
 */

public interface LoginView extends IView {

    /**
     * 验证码倒计时
     */
    void changeBtnStatus();


    void loginSuccess(LoginBean loginBean);

}
