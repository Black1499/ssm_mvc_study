package com.lzx.entity;

public class AreaCode {
    int id;
    String code;
    String province;
    String city;
    String district;
    String detail;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public AreaCode() {
    }

    public AreaCode(int id, String code, String province, String city, String district, String detail) {
        this.id = id;
        this.code = code;
        this.province = province;
        this.city = city;
        this.district = district;
        this.detail = detail;
    }
}
