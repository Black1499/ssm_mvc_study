package com.lzx.web;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
public class DownLoadController {

    @GetMapping("/download")
    public ResponseEntity<InputStreamResource> downloadFile() throws IOException {
        FileSystemResource file = new FileSystemResource("C:\\Users\\admin\\Pictures\\Saved Pictures\\1.jpg");
        HttpHeaders headers = new HttpHeaders();
        headers.setCacheControl("no-cache, no-store, must-revalidate");
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentLength(file.contentLength());
        headers.setContentDispositionFormData("attachment", file.getFilename());

        return ResponseEntity.ok().headers(headers).body(new InputStreamResource(file.getInputStream()));
    }



    @GetMapping("/test")
    public ResponseEntity<InputStreamResource> excelTest() throws IOException {
        HSSFWorkbook sheets = createExcel();

        File files = new File("d:\\"+ UUID.randomUUID()+".xls");
        sheets.write(files);

        FileSystemResource file = new FileSystemResource(files);
        HttpHeaders headers = new HttpHeaders();
        headers.setCacheControl("no-cache, no-store, must-revalidate");
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentLength(file.contentLength());
        headers.setContentDispositionFormData("attachment", file.getFilename());

        return ResponseEntity.ok().headers(headers).body(new InputStreamResource(file.getInputStream()));
    }

    public HSSFWorkbook createExcel(){
        HSSFWorkbook sheets = new HSSFWorkbook();
        HSSFSheet sheet = sheets.createSheet("我的第一个表格");
        HSSFRow row1 = sheet.createRow(0);
        row1.createCell(0).setCellValue("asfsfsdfa");
        return sheets;
    }
}
