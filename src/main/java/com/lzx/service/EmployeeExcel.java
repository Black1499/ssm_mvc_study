package com.lzx.service;

import com.lzx.entity.Employee;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface EmployeeExcel {

    List<Employee> listAll();

    byte[] writeExcel(List<Employee> list) throws IOException;

    String readExcel(MultipartFile file);
}
