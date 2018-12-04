package com.lzx.entity;

import com.lzx.annotation.IdNumberFormat;

import java.util.Date;

public class PostAuthor {

    private Integer id;

    private String name;

    private Date birthday;

    private String sex;


    @IdNumberFormat
    private String idNum;



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

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getIdNum() {
        return idNum;
    }

    public void setIdNum(String idNum) {
        this.idNum = idNum;
    }

    public PostAuthor() {
    }

    public PostAuthor(Integer id, String name, Date birthday, String sex, String idNum) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
        this.sex = sex;
        this.idNum = idNum;
    }
}