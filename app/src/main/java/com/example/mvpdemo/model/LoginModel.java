package com.example.mvpdemo.model;

import com.example.mvpdemo.Lisners.CallBack;

import java.util.ArrayList;

public class LoginModel implements  BaseModel<CallBack> {
    @Override
    public void Login(CallBack callBack){
        ArrayList<String> list = new ArrayList<>();
                 list.add(0,"成功");
                 callBack.onSuccuess(list.get(0)+"");
                 list.add(1,"失败");
                 callBack.onFailed(list.get(1)+"");
    }

}
