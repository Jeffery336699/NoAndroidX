package com.circle.drawable.mode.observer_auth;

import java.util.Observable;

public class SubjectFor3d extends Observable {
    private String msg;


    public String getMsg() {
        return msg;
    }


    /**
     * 主题更新消息
     *
     * @param msg
     */
    public void setMsg(String msg) {
        this.msg = msg;
        // 标记有改变，该内部做了优化，有改变才会执行通知服务
        setChanged();
        // 通知所有观察者
        notifyObservers();
    }
}