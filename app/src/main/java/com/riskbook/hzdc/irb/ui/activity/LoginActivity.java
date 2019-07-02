package com.riskbook.hzdc.irb.ui.activity;

import android.content.Intent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.riskbook.hzdc.irb.R;
import com.riskbook.hzdc.irb.bean.LoginBean;
import com.riskbook.hzdc.irb.constants.Constant;
import com.riskbook.hzdc.irb.manager.DataCacheManager;
import com.riskbook.hzdc.irb.mvp.presenter.login.LoginPresenter;
import com.riskbook.hzdc.irb.mvp.view.login.LoginView;
import com.riskbook.hzdc.irb.router.RouterCons;
import com.riskbook.hzdc.irb.utils.SharePreferenceUtil;
import com.riskbook.hzdc.irb.utils.StatusBar.StatusBarUtil;
import com.riskbook.hzdc.irb.view.TimeCounter;
import com.sankuai.waimai.router.annotation.RouterUri;
import com.sankuai.waimai.router.common.DefaultUriRequest;

import butterknife.BindView;
import butterknife.OnClick;

@RouterUri(path = {RouterCons.CREATE_LOGIN})
public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginView {

    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_code)
    EditText etCode;
    @BindView(R.id.tv_code)
    TextView tvCode;
    @BindView(R.id.cb_read)
    CheckBox cbRead;
    @BindView(R.id.tv_user_agreement)
    TextView tvUserAgreement;
    @BindView(R.id.tv_login)
    TextView tvLogin;
    private TimeCounter mTimeCounter;//验证码倒计时

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected LoginPresenter getPresenter() {
        return new LoginPresenter(this);
    }

    @Override
    protected void initDataAndEvent() {
        StatusBarUtil.setRootViewFitsSystemWindows(this, false);
    }


    @OnClick({R.id.tv_code, R.id.tv_user_agreement, R.id.tv_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_code:
                mPresenter.sendVerificationCode(etPhone.getText().toString(), etCode);
                break;
            case R.id.tv_user_agreement:
                new DefaultUriRequest(this,RouterCons.WEB_VIEW_INFO)
                        .putExtra(Constant.URL,"file:///android_asset/apps/web/html/service.html")
                        .putExtra(WebViewContainerActivity.TITLE,getResources().getString(R.string.user_argument))
                        .start();
                break;
            case R.id.tv_login:
                mPresenter.requestLogin(etPhone.getText().toString(), etCode.getText().toString(),
                    cbRead.isChecked());
                break;
        }
    }


    @Override
    public void changeBtnStatus() {
        mTimeCounter = new TimeCounter(60000, 1000, tvCode, R.string.btn_re_send_code);
        mTimeCounter.start();
    }

    @Override
    public void loginSuccess(LoginBean loginBean) {
        saveLoginInfo(loginBean);
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    /**
     * 保存用户信息
     */
    private void saveLoginInfo(LoginBean loginBean) {
        DataCacheManager.saveToken(loginBean.getToken());
        DataCacheManager.saveUserInfo(loginBean.getUser());
        SharePreferenceUtil.putBoolean(Constant.IS_LOGIN, true);
    }
}
