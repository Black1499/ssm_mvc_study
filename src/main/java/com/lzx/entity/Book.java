package com.lzx.entity;

import com.lzx.annotation.CellPhone;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.util.Date;
import java.util.List;

public class Book {

    @Min(0)
    private int id;

    @NotBlank(message = "姓名不能为空")
    private String name;

    @DateTimeFormat(pattern = "yyyy-MM-dd")//用于格式转换
    @NotNull
    @Past
    private Date writeDate;

    @NotNull(message = "我是存在的！！！！")
    private Employee employee;


    @DecimalMin(value = "10.00",message = "最低价格是10元")
    private float price;

//    @NotBlank
//    @Pattern(regexp = "^[0-9]{11}$")
    @CellPhone
    private String phone;

    @Email
    private String email;

    @NotNull
    List list;

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

    public Date getWriteDate() {
        return writeDate;
    }

    public void setWriteDate(Date writeDate) {
        this.writeDate = writeDate;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }
}
