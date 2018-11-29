package com.lzx.web;

import com.lzx.dao.EmployeeMapper;
import com.lzx.entity.Employee;
import com.lzx.vo_filter.EmployeeBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class EmployeeController {
    @Autowired
    EmployeeMapper employeeMapper;

    @RequestMapping("/listAll")
    @ResponseBody
    public List<Employee> listAll(Model model){
        List<Employee> list = employeeMapper.selectAll();
        return list;
    }

    @RequestMapping("/empIndex")
    public String getEmpIndex(Model model){
        return "empIndex";
    }

    @RequestMapping("/addList")
    public String addList(@RequestBody List<Employee> list){
        for (int i = 0; i <list.size() ; i++) {
            employeeMapper.insert(list.get(i));
        }
        return "redirect:/empIndex";
    }

    @RequestMapping("/empForm")
    public String empForm(Model model){
        model.addAttribute("empList", employeeMapper.selectAll());
        return "empForm";
    }

    @RequestMapping("/addByForm")
    public String addByForm(EmployeeBean employeeBean, BindingResult result){
        List<Employee> list = employeeBean.getEmpList();
        for (int i = 0; i <list.size() ; i++) {
            employeeMapper.insert(list.get(i));
        }
        return "redirect:/empForm";
    }

    @RequestMapping("/del/{id}")
    public String delById(@PathVariable String id){
        employeeMapper.deleteByPrimaryKey(id);
        return "redirect:/empForm";
    }
}
