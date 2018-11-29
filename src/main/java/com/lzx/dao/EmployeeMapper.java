package com.lzx.dao;

import com.lzx.entity.Employee;
import java.util.List;

public interface EmployeeMapper {
    int deleteByPrimaryKey(String id);

    int insert(Employee record);

    Employee selectByPrimaryKey(String id);

    List<Employee> selectAll();

    int updateByPrimaryKey(Employee record);
}