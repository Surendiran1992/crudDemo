package com.springboot.crudDemo.dao;

import com.springboot.crudDemo.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO{

    //create a field for entityManager
    private EntityManager entityManager;

    //inject the entityManager in Constructor, so @AutoWired annotation should be used
    @Autowired
    public EmployeeDAOImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    public List<Employee> findAll() {
        TypedQuery<Employee> employeeTypedQuery = entityManager.createQuery("from Employee",Employee.class);
        return employeeTypedQuery.getResultList();
    }

    @Override
    public Employee save(Employee theEmployee) {
       Employee employee = entityManager.merge(theEmployee);
       return employee;
    }

    @Override
    public Employee findById(int id) {
        return entityManager.find(Employee.class,id);
    }

    @Override
    public void deleteById(int id) {
        Employee theEmployee = entityManager.find(Employee.class,id);
        entityManager.remove(theEmployee);
    }
}
