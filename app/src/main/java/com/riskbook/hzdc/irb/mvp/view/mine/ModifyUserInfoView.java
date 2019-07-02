package com.riskbook.hzdc.irb.mvp.view.mine;

import com.riskbook.hzdc.irb.bean.LoginBean;
import com.riskbook.hzdc.irb.mvp.view.IView;

/**
 * Created by zenghaiqiang on 2019/1/24.
 */

public interface ModifyUserInfoView extends IView {
    /**
     * 获取用户信息成功
     * @param bean
     */
    void getUserInfoSuccess(LoginBean.UserBean bean);

    void updateUserInfoSuccess();


    void selectPhoto();

    void onUploadSuccess(String imgUrl);
}
