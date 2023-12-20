package com.springboot.crudDemo.dao;

import com.springboot.crudDemo.entity.Customer;
import com.springboot.crudDemo.entity.CustomerDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class CustomerDAOImpl implements CustomerDAO{

    //create a field for entityManager
    private List<Customer> entityManager;

    @Autowired
    public CustomerDAOImpl(){
        this.entityManager = CustomerDataSource.getCustomerData();
    }

    @Override
    public List<Customer> findAll() {
        return entityManager;
    }

    @Override
    public Customer save(Customer theCustomer) {
        int customerId = theCustomer.getId()-1;
        entityManager.add(customerId,theCustomer);
        return entityManager.get(customerId);
    }

    @Override
    public List<Customer> findByName(String name) {
        return entityManager.stream().filter(customer ->
                customer.getName().equalsIgnoreCase(name)).collect(Collectors.toList());
    }

    @Override
    public void deleteById(int id) {
        Customer theCustomer = entityManager.get(id-1);
        entityManager.remove(theCustomer);
    }

    @Override
    public Customer findById(int id) {
        return entityManager.get(id-1);
    }

    @Override
    public Customer updateCustomer(int id, Customer reqCustomerBody) {
        Customer customer = entityManager.get(id-1);
        if (reqCustomerBody.getEmail() != null) { customer.setEmail(reqCustomerBody.getEmail());}

        if(reqCustomerBody.getName()!=null) { customer.setName(reqCustomerBody.getName()); }

        return customer;
    }

    @Override
    public Customer upsertCustomer(Customer reqCustomerBody) {
        int customerId = reqCustomerBody.getId()-1;
        boolean idExists = entityManager.stream().anyMatch(cust -> cust.getId() == customerId);
        if(idExists){
            Customer customer = entityManager.get(customerId);
            if (reqCustomerBody.getEmail() != null) { customer.setEmail(reqCustomerBody.getEmail());}

            if(reqCustomerBody.getName()!=null) { customer.setName(reqCustomerBody.getName()); }

        }else entityManager.add(customerId,reqCustomerBody);

        return entityManager.get(customerId);
    }


}
