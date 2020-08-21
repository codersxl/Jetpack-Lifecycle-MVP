package com.example.mvpdemo.presenter;

import android.util.Log;

import androidx.lifecycle.LifecycleOwner;

import com.example.mvpdemo.Lisners.CallBack;
import com.example.mvpdemo.model.LoginModel;
import com.example.mvpdemo.presenter.imp.LoginPresenteripl;
import com.example.mvpdemo.view.LoginView;

/**
 * 持有modle vidw 引用
 * 关联
 */
public class LoginPresenter implements LoginPresenteripl {


    @Override
    public void Login() {
        if (loginView != null) {
            loginModel.Login(new CallBack() {
                @Override
                public void onSuccuess(String string) {
                    loginView.onSueccus(string);
                }

                @Override
                public void onFailed(String s) {
                    loginView.onFailed(s);
                }
            });
        }

    }

    @Override
    public LoginView attchViews(LoginView loginView) {
        this.loginView = loginView;
        return loginView;
    }

    private LoginModel loginModel;
    private LoginView loginView;

    public LoginPresenter() {
        loginModel = new LoginModel();
    }

    @Override
    public void onAny(LifecycleOwner owner) {
        Log.d("-onAny-","onAny");
    }

    @Override
    public void onCreate(LifecycleOwner owner) {
        Log.d("-onCreate-","onCreate");
    }

    @Override
    public void onStart(LifecycleOwner owner) {
        Log.d("-onStart-","onStart");
    }

    @Override
    public void onStop(LifecycleOwner owner) {
        Log.d("-onStop-","onStop");
    }

    @Override
    public void onResume(LifecycleOwner owner) {
        Log.d("-onResume-","onResume");
    }

    @Override
    public void onPause(LifecycleOwner owner) {
        Log.d("-onPause-","onPause");
    }

    @Override
    public void onDestory(LifecycleOwner owner) {
        this.loginView = null;
        this.loginModel = null;
        Log.d("-onDestory-","onDestory");
    }
}
