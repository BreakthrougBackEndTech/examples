package com.common.domain;

import java.io.Serializable;

public class CallBackMessage implements Serializable {

    private Long messageId;
    private String messageStatus;

    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    public String getMessageStatus() {
        return messageStatus;
    }

    public void setMessageStatus(String messageStatus) {
        this.messageStatus = messageStatus;
    }

    @Override
    public String toString() {
        return "CallBackMessage{" +
                "messageId=" + messageId +
                ", messageStatus='" + messageStatus + '\'' +
                '}';
    }
}
