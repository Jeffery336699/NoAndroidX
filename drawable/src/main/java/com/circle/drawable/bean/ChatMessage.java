package com.circle.drawable.bean;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ChatMessage implements Cloneable{

    /**
     * 消息类型
     */
    private Type type;
    /**
     * 消息内容
     */
    private String msg;
    /**
     * 日期
     */
    private Date date;
    /**
     * 日期的字符串格式
     */
    private String dateStr;
    /**
     * 发送人
     */
    private String name;

    public enum Type {
        INPUT, OUTPUT
    }

    public ChatMessage() {
    }

    public ChatMessage(Type type, String msg) {
        super();
        this.type = type;
        this.msg = msg;
        setDate(new Date());
    }

    public String getDateStr() {
        return dateStr;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.dateStr = df.format(date);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * 关于深浅拷贝,参考:
     * https://www.cnblogs.com/qian123/p/5710533.html
     */
    @Override
    public Object clone()  {
        ChatMessage chatMessage=null;
        try {
            chatMessage= (ChatMessage) super.clone(); // 这仅是浅拷贝
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return chatMessage;
    }
}