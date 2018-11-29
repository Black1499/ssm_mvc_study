package com.lzx.entity;

import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class Order {

    @NotBlank(message = "{err.id}")
    private String id;

    @NotBlank(message = "{err.name}")
    private String name;


    @Email(message = "{err.email}")
    private String email;

    @Min(value = 100,message = "{err.price}")
    private float price;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }


    public Order(@NotBlank(message = "{err.id}") String id, @NotBlank(message = "{err.name}") String name, @Email(message = "{err.email}") String email, @Min(value = 100, message = "{err.price}") float price) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.price = price;
    }
}
