package com.llp.mam.enums;


import lombok.Getter;

@Getter
public enum Profile {
    EMPLOYEE(1L ,"employee"),
    ADMIN(2L, "admin");

    private final Long id;
    private final String description;

    Profile(Long id, String description) {
        this.id = id;
        this.description = description;
    }

}
