package com.example.myapplication;


import java.util.Date;

public class Msg {
    private String content;
    private int type;
    private String time;
    public final static int TYPE_RECEIVED=0;
    public final static int TYPE_SENT=1;
    public Msg(String content,int type){
        this.content =content;
        this.type = type;
        this.time = timeData();
}
    public String getContent() {
        return content;
    }
    public int getType() {
        return type;
    }

    public String getTime() {
        return time;
    }
    /*
    写一个获取时间的方法
     */
    public String timeData(){
        Date date = new Date();
        String timeData = String.format("%tH",date)
                +String.format("%tM",date)
                +String.format("%tS",date);
        return timeData;

    }
}
