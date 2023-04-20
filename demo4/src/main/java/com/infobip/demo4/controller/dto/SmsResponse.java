package com.infobip.demo4.controller.dto;

public class SmsResponse {
    private String to;
    private String messageCount;
    private String messageId;

    private StatusDto statusDto;

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getMessageCount() {
        return messageCount;
    }

    public void setMessageCount(String messageCount) {
        this.messageCount = messageCount;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public StatusDto getStatus() {
        return statusDto;
    }

    public void setStatus(StatusDto statusDto) {
        this.statusDto = statusDto;
    }
}
