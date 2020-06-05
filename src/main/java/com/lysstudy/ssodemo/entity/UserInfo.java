package com.lysstudy.ssodemo.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo{
    @Id
    private long id;

    private String name;

    private String password;

    private int roleId;

    private int addressId;

    private long telno;

    private String email;

    private String photo;
}

