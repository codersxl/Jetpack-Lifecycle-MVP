package com.example.mvpdemo.base;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

/**
 * 抽象出绑定和解绑
 * 兼容多个 Ac 采取泛型
 *
 *
 */
public abstract class BaseActivity<V extends BaseView,P extends BasePresenter<V>> extends AppCompatActivity {

    private  V view;
    private P presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

         if(this.presenter==null){
             this.presenter=createPresenter();
         }
         if(this.view==null){
             this.view=createView();
         }
         if(this.presenter!=null&&this.view!=null){
             this.presenter.attchViews(this.view);
             getLifecycle().addObserver(this.presenter);
         }
    }

    protected abstract V createView();

    protected abstract P createPresenter();
    public P getPresenter(){
        return  this.presenter;
    }


}
