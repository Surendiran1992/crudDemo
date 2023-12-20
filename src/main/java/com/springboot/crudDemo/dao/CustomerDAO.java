package com.springboot.crudDemo.dao;

import com.springboot.crudDemo.entity.Customer;

import java.util.List;

public interface CustomerDAO {

    List<Customer> findAll();

    Customer save(Customer theCustomer);

    List<Customer> findByName(String name);

    void deleteById(int id);

    Customer findById(int id);

    Customer updateCustomer(int id, Customer reqCustomerBody);

    Customer upsertCustomer(Customer reqCustomerBody);
}
