package com.example.mvpdemo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;



import com.example.mvpdemo.base.BaseActivity;
import com.example.mvpdemo.presenter.LoginPresenter;
import com.example.mvpdemo.view.LoginView;

public class MainActivity extends BaseActivity<LoginView,LoginPresenter> implements LoginView {

    private Button btn_start;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_start = (Button) findViewById(R.id.btn_start);
         btn_start.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
               getPresenter().Login();
             }
         });
    }

    @Override
    protected LoginView createView() {

        return this;
    }

    @Override
    protected LoginPresenter createPresenter() {

        return new LoginPresenter();
    }

    @Override
    public void onSueccus(String string) {
        Toast.makeText(this, string+"", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFailed(String data) {
        Toast.makeText(this, data+"", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showToast(String msg) {

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
