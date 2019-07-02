package com.riskbook.hzdc.irb.ui.fragment.home;


import android.widget.ImageView;
import android.widget.TextView;
import com.riskbook.hzdc.irb.R;
import com.riskbook.hzdc.irb.mvp.presenter.home.SelfSelectPresenter;
import com.riskbook.hzdc.irb.mvp.view.home.SelfSelectView;
import com.riskbook.hzdc.irb.ui.fragment.BaseFragment;
import com.riskbook.hzdc.irb.utils.MLog;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

/**
 * Created by zenghaiqiang on 2019/1/15. 消息Fragment
 */
public class SelfSelectFragment extends BaseFragment<SelfSelectPresenter> implements SelfSelectView, OnRefreshListener {

    @BindView(R.id.rv_message)
    RecyclerView rvMessage;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.tv_add)
    TextView tvAdd;
    @BindView(R.id.iv_avatar)
    ImageView ivAvatar;


    @Override
    protected void initEventAndData() {
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_self_select;
    }

    @Override
    protected SelfSelectPresenter getPresenter() {
        return new SelfSelectPresenter(this);
    }

    @Override
    protected void onFragmentVisibleChange(boolean isVisible) {
        MLog.d("onFragmentVisibleChange");
    }


    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        refreshLayout.finishRefresh(2000);
    }


}
