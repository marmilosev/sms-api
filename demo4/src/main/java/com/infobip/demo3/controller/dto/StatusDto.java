package com.infobip.demo3.controller.dto;

public class StatusDto {
    private int groupId;
    private String groupName;
    private int id;
    private String name;
    private String description;
    private String action;

    public StatusDto() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
