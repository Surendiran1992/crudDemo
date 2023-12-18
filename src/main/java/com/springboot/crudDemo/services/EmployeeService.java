package com.springboot.crudDemo.services;

import com.springboot.crudDemo.entity.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> findAll();

    Employee save(Employee theEmployee);

    Employee findById(int id);

    void deleteById(int id);
}
