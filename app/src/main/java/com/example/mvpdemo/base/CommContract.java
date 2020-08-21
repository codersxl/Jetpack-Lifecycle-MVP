package com.example.mvpdemo.base;

import com.example.mvpdemo.view.LoginView;

/**
 * 契约类
 */
public interface CommContract {

    public interface BasePresenter<V extends BaseView> extends BaseLifecycleObserver {
        LoginView attchViews(V v);
        void detechViews();
    }

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

}
