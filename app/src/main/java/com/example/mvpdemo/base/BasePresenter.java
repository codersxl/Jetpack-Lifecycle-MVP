package com.example.mvpdemo.base;

import com.example.mvpdemo.view.LoginView;

public interface BasePresenter<V extends BaseView> extends BaseLifecycleObserver {
    LoginView attchViews(V v);

}
