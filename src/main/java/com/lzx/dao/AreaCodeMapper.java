package com.lzx.dao;

import com.lzx.entity.AreaCode;
public interface AreaCodeMapper {

    AreaCode selectByCode(String code);
}
