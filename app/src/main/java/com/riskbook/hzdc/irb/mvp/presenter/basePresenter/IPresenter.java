package com.riskbook.hzdc.irb.mvp.presenter.basePresenter;


import com.riskbook.hzdc.irb.mvp.view.IView;

/**
 * Created by pantianxiong on 2018/4/23.
 * 描述：
 */

public interface IPresenter<V extends IView> {
    void detachView();
}
