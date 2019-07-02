package com.riskbook.hzdc.irb.mvp.presenter.mine;

import com.riskbook.hzdc.irb.R;
import com.riskbook.hzdc.irb.mvp.presenter.basePresenter.BasePresenter;
import com.riskbook.hzdc.irb.ui.activity.BaseActivity;

public class SettingActivity extends BaseActivity {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_setting;
    }

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    protected void initDataAndEvent() {
        initTitle(getResources().getString(R.string.set));
    }
}
