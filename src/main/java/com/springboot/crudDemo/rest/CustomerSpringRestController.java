package com.springboot.crudDemo.rest;

import com.springboot.crudDemo.dao.CustomerDAO;
import com.springboot.crudDemo.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerSpringRestController {
    private CustomerDAO customerDAO;

    //injecting employee DAO in Constructor
    @Autowired
    public CustomerSpringRestController(CustomerDAO customerDAO){
        this.customerDAO = customerDAO;
    }

    @GetMapping("/customers")
    public List<Customer> getAllCustomers(){
        return customerDAO.findAll();
    }

    @GetMapping("/customers/{customerName}")
    public List<Customer> getCustomerByName(@PathVariable String customerName){
        return customerDAO.findByName(customerName);
    }

/*    @GetMapping("/customers/{customerId}")
    public Customer getCustomerById(@PathVariable int customerId){
        return customerDAO.findById(customerId);
    }*/

    @PostMapping("/customers")
    public Customer createNewCustomer(@RequestBody Customer newCustomer){
        return customerDAO.save(newCustomer);
    }

    @PutMapping("/customers")
    public Customer upsertCustomer(@RequestBody Customer updateCustomer){
        return customerDAO.upsertCustomer(updateCustomer);
    }

    @PatchMapping("customers/{customerId}")
    public Customer patchCustomer(@PathVariable int customerId, @RequestBody Customer patchCustomer){
        return customerDAO.updateCustomer(customerId,patchCustomer);
    }
    @DeleteMapping("/customers/{customerId}")
    public String deleteCustomer(@PathVariable int customerId){
        try{
            customerDAO.deleteById(customerId);
        } catch (IndexOutOfBoundsException e){
            throw new RuntimeException("Customer id not found "+ customerId);
        }

        return "The Customer data have been deleted "+ customerId;
    }
}
