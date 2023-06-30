package org.contactApp.models;

import lombok.Data;

@Data
public class FullName {
    private String name;
    private String surname;

    public FullName(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }
}
