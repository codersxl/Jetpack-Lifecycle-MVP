package com.example.mvpdemo.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public abstract class BaseFragment<V extends BaseView,P extends BasePresenter<V>> extends Fragment {

    private V view;
    private P presenter;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if(presenter==null){
            this.presenter=createPresenter();
        }
        if(view==null){
          this.view=createView();
        }
        if(this.presenter!=null&&this.view!=null){
             this.presenter.attchViews(view);
             getLifecycle().addObserver(this.presenter);
        }
    }

    protected abstract V createView();

    protected abstract P createPresenter();

    @Override
    public void onDestroy() {
        super.onDestroy();

    }
}
