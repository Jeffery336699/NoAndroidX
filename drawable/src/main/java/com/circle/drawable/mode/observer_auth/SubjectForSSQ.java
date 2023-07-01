package com.circle.drawable.mode.observer_auth;

import java.util.Observable;

public class SubjectForSSQ extends Observable
{
    private String msg ;


    public String getMsg()
    {
        return msg;
    }


    /**
     * 主题更新消息
     *
     * @param msg
     */
    public void setMsg(String msg)
    {
        this.msg = msg  ;
        setChanged();
        notifyObservers();
    }
}