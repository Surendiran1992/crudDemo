package com.springboot.crudDemo.rest;

import com.springboot.crudDemo.entity.Employee;
import com.springboot.crudDemo.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeSpringRestController {
    private EmployeeService employeeService;

    //injecting employee DAO in Constructor
    @Autowired
    public EmployeeSpringRestController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    @GetMapping("/employees")
    public List<Employee> getAllEmployees(){
        return employeeService.findAll();
    }

    @GetMapping("/employees/{employeeId}")
    public Employee getEmployeeById(@PathVariable int employeeId){
        return employeeService.findById(employeeId);
    }

    @PostMapping("/employees")
    public Employee createNewEmployee(@RequestBody Employee newEmployee){
        //setting the id to 0 incase the requestbody contains id, bcoz id is autoincremented in database
        newEmployee.setId(0);
        return employeeService.save(newEmployee);
    }

    @PutMapping("/employees")
    public Employee updateExistingEmployee(@RequestBody Employee updateEmployee){
        return employeeService.save(updateEmployee);
    }

    @DeleteMapping("/employees/{employeeId}")
    public String deleteEmployee(@PathVariable int employeeId){
        Employee toDelete = employeeService.findById(employeeId);
        if(toDelete == null){
            throw new RuntimeException("Employee id not found "+ employeeId);
        }
        employeeService.deleteById(employeeId);
        return "The employee data have been deleted "+ employeeId;
    }
}
