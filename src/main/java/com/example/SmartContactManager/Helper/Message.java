package com.example.SmartContactManager.Helper;

public class Message {

    private String content;
    private String msgType;


    public Message(String content, String msgType) {
        this.content = content;
        this.msgType = msgType;
    }


    public String getContent() {
        return content;
    }


    public String getMsgType() {
        return msgType;
    }


    public void setContent(String content) {
        this.content = content;
    }


    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }


    @Override
    public String toString() {
        return "Message [content=" + content + ", msgType=" + msgType + "]";
    }

    

    

    
}
