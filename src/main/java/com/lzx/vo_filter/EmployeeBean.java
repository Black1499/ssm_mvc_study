package com.lzx.vo_filter;

import com.lzx.entity.Employee;

import java.util.List;
import java.util.Map;

public class EmployeeBean {
    private List<Employee> empList;
    private Map<String,Employee> empMap;

    public List<Employee> getEmpList() {
        return empList;
    }

    public void setEmpList(List<Employee> empList) {
        this.empList = empList;
    }

    public Map<String, Employee> getEmpMap() {
        return empMap;
    }

    public void setEmpMap(Map<String, Employee> empMap) {
        this.empMap = empMap;
    }
}
