package com.springboot.crudDemo.dao;

import com.springboot.crudDemo.entity.Employee;

import java.util.List;

public interface EmployeeDAO {

    List<Employee> findAll();

    Employee save(Employee theEmployee);

    Employee findById(int id);

    void deleteById(int id);
}
