package com.lzx.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

@Controller
public class FileController {

    private MultipartFile file;

    @PostMapping("/myUp")
    @ResponseBody
    public String upFile(@RequestPart("file") MultipartFile file) throws IOException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String newName = "upLoad_";
        if (!file.isEmpty()) {
            String name = file.getOriginalFilename();
            String[] names = name.split("[.]");
            String last = names[names.length - 1];
            if (Pattern.matches("^(txt|jpg|png|gif)$", last)) {
                for (int i = 0; i < names.length - 1; i++) {
                    newName += names[i] + "_";
                }
                newName += simpleDateFormat.format(new Date());
                newName += "." + last;
                File fileName = new File(newName);
                file.transferTo(fileName);
            } else {
                return "bad files";
            }
        }
        return newName;
    }

    @GetMapping("/up")
    public String getIndex(Model model) {
        return "upFile";
    }

    @PostMapping("/up")
    public String upFiles(@RequestPart("file") MultipartFile file, HttpServletRequest re,Model model) {

        // 获得上传的文件格式
        String contextType = file.getContentType();

        // 判断文件是否符合要求的类型
        if (!contextType.contains("image/")) {
            model.addAttribute("msg", "只允许上传图片");
            return "upFile";
        }
        // 判断上传的文件大小
        if (file.getSize() > 1024 * 1024 * 5) {
            model.addAttribute("msg", "文件过大");
            return "upFile";
        }

        try {
            // 获取当前类加载的路径
            String path = re.getServletContext().getRealPath(File.separator+"img");
            File img = new File(path);
            // 如果文件夹不存在就创建一个
            if(!img.exists()){
                img.mkdir();
            }
            // file.getOriginalFilename()为文件的逻辑名
            File fileName= new File(path+File.separator+getNewName(file.getOriginalFilename()));
            // 这里是写入文件
            file.transferTo(fileName);
        } catch (IOException e) {
            model.addAttribute("msg", "写入失败");
        }
        return "upFile";
    }


    public String getNewName(String name){
        int index = name.lastIndexOf(".");
        String firstName = name.substring(0, index);
        String lastName = name.substring(index);
        String date = new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss").format(new Date());
        return "upload_"+firstName+"_"+date+lastName;
    }


    @PostMapping("/jsFile")
    public @ResponseBody String jsUp(MultipartFile file) throws IOException {
        file.transferTo(new File("d:/aa.jpg"));
        System.out.println("success");
        return "success";
    }

    @GetMapping("/jsFile")
    public String jsFile(Model model){
        return "jsFile";
    }

}
