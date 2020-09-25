package com.glepoch.provider.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @Author 江南一点雨
 * @Site www.javaboy.org 2019-08-11 17:11
 */
public class Menu implements Serializable {
    private Integer id;
    private String pattern;
    private List<Role> roles;

    public Menu() {
    }

    public Menu(Integer id, String pattern, List<Role> roles) {
        this.id = id;
        this.pattern = pattern;
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", pattern='" + pattern + '\'' +
                ", roles=" + roles +
                '}';
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }
}
