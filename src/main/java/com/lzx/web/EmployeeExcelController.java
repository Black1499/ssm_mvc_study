package com.lzx.web;

import com.lzx.dao.EmployeeMapper;
import com.lzx.entity.Employee;
import com.lzx.service.EmployeeExcelService;
import jdk.internal.util.xml.impl.Input;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/emp")
public class EmployeeExcelController {

    @Autowired
    EmployeeMapper employeeMapper;

    @Autowired
    EmployeeExcelService service;

    private Employee employee = null;

    @GetMapping("/outExcel")
    public ResponseEntity outExcel() throws IOException {

//        HSSFWorkbook workbook = writeExcel();
//        File files = new File( UUID.randomUUID() + ".xls");
//        workbook.write(files);
//        FileSystemResource file = new FileSystemResource(files);
        // 这种方法也是可行的
//        HttpHeaders headers = new HttpHeaders();
//        headers.setCacheControl("no-cache, no-store, must-revalidate");
//        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
//        headers.setContentLength(file.contentLength());
//        headers.setContentDispositionFormData("attachment", file.getFilename());
//        return ResponseEntity.ok().headers(headers).body(new InputStreamResource(file.getInputStream()));

        byte[] file = service.writeExcel(service.listAll());
        HttpHeaders headers = new HttpHeaders();
        headers.setCacheControl("no-cache, no-store, must-revalidate");
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentLength(file.length);
        headers.setContentDispositionFormData("attachment", UUID.randomUUID()+".xls");
        return ResponseEntity.ok().headers(headers).body(file);

    }

    @PostMapping("/inExcel")
    @ResponseBody
    public String inExcel(MultipartFile file) throws IOException {
        return service.readExcel(file);
    }













    // 导出excel
    public HSSFWorkbook writeExcel() {

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("客户信息表");
        HSSFRow row1 = sheet.createRow(0);
        row1.createCell(0).setCellValue("编号");
        row1.createCell(1).setCellValue("姓名");
        row1.createCell(2).setCellValue("性别");
        row1.createCell(3).setCellValue("学历");
        row1.createCell(4).setCellValue("月薪");

        List<Employee> list = employeeMapper.selectAll();

        HSSFRow row = null;
        for (int i = 1; i <= list.size(); i++) {
            employee = list.get(i - 1);
            row = sheet.createRow(i);
            row.createCell(0).setCellValue(employee.getId());
            row.createCell(1).setCellValue(employee.getName());
            row.createCell(2).setCellValue(employee.getSex());
            row.createCell(3).setCellValue(employee.getEducation());
            row.createCell(4).setCellValue(String.valueOf(employee.getSalary()));
        }
        return workbook;
    }

    // 导入excel
    public void readExcel(MultipartFile file) throws IOException {

        Workbook workbook = null;
        String fileName = file.getOriginalFilename();
        InputStream inputStream = file.getInputStream();

        if (fileName.endsWith("xlsx")) {
            workbook = new XSSFWorkbook(inputStream);//Excel 2007
        } else if (fileName.endsWith("xls")) {
            workbook = new HSSFWorkbook(inputStream);//Excel 2003
        }

        Sheet sheet = workbook.getSheetAt(0);
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            if (row != null) {
                String id = row.getCell(0).getStringCellValue();
                String name = row.getCell(1).getStringCellValue();
                String sex = row.getCell(2).getStringCellValue();
                String education = row.getCell(3).getStringCellValue();
                String salary = row.getCell(4).getStringCellValue();
                employee = new Employee(id, name, sex, education, new BigDecimal(salary));
                employeeMapper.insert(employee);
            }
        }
    }
}
