package com.poolcar.model.dashboard;

import com.poolcar.model.Status;

import java.io.Serializable;

public class ProfileData extends Status implements Serializable{
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
