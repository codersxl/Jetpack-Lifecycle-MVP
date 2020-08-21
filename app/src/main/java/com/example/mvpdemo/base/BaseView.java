package com.example.mvpdemo.base;

import com.example.mvpdemo.model.BaseModel;

public interface BaseView{
    void onSueccus(String S);
    void onFailed(String data);
    /**
     * 显示正在加载view
     */
    void showLoading();
    /**
     * 关闭正在加载view
     */
    void hideLoading();
    /**
     * 显示提示
     * @param msg
     */
    void showToast(String msg);


}
