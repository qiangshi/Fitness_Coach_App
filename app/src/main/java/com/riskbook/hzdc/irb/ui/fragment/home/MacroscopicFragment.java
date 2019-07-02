package com.riskbook.hzdc.irb.ui.fragment.home;

import com.riskbook.hzdc.irb.R;
import com.riskbook.hzdc.irb.mvp.presenter.home.MacroscopicPresenter;
import com.riskbook.hzdc.irb.mvp.view.home.MacroscopicView;
import com.riskbook.hzdc.irb.ui.fragment.BaseFragment;

/**
 * @Description: java类作用描述
 * @Author: 曾海强
 * @CreateDate: 2019/3/1 10:22
 */
public class MacroscopicFragment extends BaseFragment<MacroscopicPresenter> implements MacroscopicView {


    @Override
    protected void initEventAndData() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_macroscopic;
    }

    @Override
    protected MacroscopicPresenter getPresenter() {
        return null;
    }

    @Override
    protected void onFragmentVisibleChange(boolean isVisible) {

    }
}
