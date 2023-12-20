package com.springboot.crudDemo.entity;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CustomerDataSource {
    public static List<Customer> getCustomerData() {
        return Stream.of(new Customer(1, "Patel", "patel123@gmail.com"),
                new Customer(2, "Vicky", "vicky123@gmail.com"),
                new Customer(3, "Rakesh", "rakesh@gmail.com"),
                new Customer(4, "Ishaan", "ishaan@gmail.com"),
                new Customer(5, "Ajay", "ajay@gmail.com"),
                new Customer(6, "Harold", "harold@gmail.com"),
                new Customer(7, "Suren", "suren@gmail.com"),
                new Customer(8, "Suren", "suren1@gmail.com")
        ).collect(Collectors.toList());
    }
}
