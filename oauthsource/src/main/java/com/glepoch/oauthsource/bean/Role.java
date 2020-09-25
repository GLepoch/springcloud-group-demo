package com.glepoch.oauthsource.bean;

import java.io.Serializable;

/**
 * @Author 江南一点雨
 * @Site www.javaboy.org 2019-08-11 17:08
 */
public class Role implements Serializable {
    private Integer id;
    private String name;
    private String nameZh;

    public Role() {
    }

    public Role(Integer id, String name, String nameZh) {
        this.id = id;
        this.name = name;
        this.nameZh = nameZh;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", nameZh='" + nameZh + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameZh() {
        return nameZh;
    }

    public void setNameZh(String nameZh) {
        this.nameZh = nameZh;
    }
}