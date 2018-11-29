package com.lzx.web;

import com.lzx.dao.EmployeeMapper;
import com.lzx.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller // 该注解表示这是一个控制器
@RequestMapping("/emp") // 所有请求的公共部分
@SessionAttributes(value = {"empList"})
public class EmpController {

    // 注入EmployeeMapper对象
    @Autowired
    EmployeeMapper employeeMapper;

    // 两种返回页面方式

    // 第一种
    //请求，默认为get请求
    @RequestMapping("/index")
    public ModelAndView index(){
        ModelAndView mv = new ModelAndView();
        // 添加属性，类似request.setAttribute(name,value);
        mv.addObject("empList",employeeMapper.selectAll());
        // 设置视图名称
        mv.setViewName("index");
        return mv;
    }

    // 第二种
    @RequestMapping(value = "indexs",method = RequestMethod.POST)
    public String indexs(Model model){
        // 添加属性，类似request.setAttribute(name,value);
        model.addAttribute("empList",employeeMapper.selectAll());
        // 返回页面
        return "indexs";
    }


    @PostMapping("/addEmp")
    public String addEmp(String name,String sex,String phone){
        // ......
        return "";
    }

    @GetMapping("/findById")
    public String findById(@RequestParam(value = "empId",required = false)long empId){
        // ....
        return "";
    }

    @GetMapping("/deleteById")
    public String deleteById(@RequestAttribute("empId") long empId){
        // ....
        return "";
    }

    @GetMapping("/findById/{id}")
    public String getById(@PathVariable("id") long id){
        // ....
        return "";
    }

    @PostMapping("/add")
    public String add(@RequestBody Employee employee){
        // ....
        return "";
    }

    // 该注解最为常用的用法，用来设置一个实体类，
    // 该controller类中的每个方法调用前，都会调用一下这个方法
    @ModelAttribute
    public Employee setEmployee(){
        Employee employee = new Employee();
        employee.setId("1");
        // ......
        return employee;
    }

    @PostMapping("listAll")
    @ResponseBody
    public List<Employee> listAll(){
        // ssm会帮助我们把返回对象自动转换成json字符串
        return employeeMapper.selectAll();
    }
}
