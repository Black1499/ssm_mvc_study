package com.lzx.service;

import com.lzx.entity.Employee;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface EmployeeExcel {

    List<Employee> listAll();

    byte[] writeExcel(List<Employee> list);

    String readExcel(MultipartFile file);
}
