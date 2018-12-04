package com.lzx.service;

import com.lzx.dao.EmployeeMapper;
import com.lzx.entity.Employee;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.List;

@Service
public class EmployeeExcelService implements EmployeeExcel {

    @Autowired
    EmployeeMapper employeeMapper;

    private Employee employee = null;

    @Override
    public List<Employee> listAll() {
        return employeeMapper.selectAll();
    }

    @Override
    public byte[] writeExcel(List<Employee> list) throws IOException {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("客户信息表");
        HSSFRow row1 = sheet.createRow(0);
        row1.createCell(0).setCellValue("编号");
        row1.createCell(1).setCellValue("姓名");
        row1.createCell(2).setCellValue("性别");
        row1.createCell(3).setCellValue("学历");
        row1.createCell(4).setCellValue("月薪");

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

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        workbook.write(stream);

        return stream.toByteArray();
    }

    // 导入excel
    @Override
    public String readExcel(MultipartFile file) {
        try{
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
        }catch (Exception e){
            e.printStackTrace();
            return "import error";
        }
        return "import success";
    }
}
