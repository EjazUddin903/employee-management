package com.example.employee.management.controller;

import com.example.employee.management.entity.Address;
import com.example.employee.management.entity.Employee;
import com.example.employee.management.service.AddressService;
import com.example.employee.management.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/addresses")
public class AddressController {
    private final AddressService addressService;
    private final EmployeeService employeeService;

    public AddressController(AddressService addressService,EmployeeService employeeService) {
        this.addressService = addressService;
        this.employeeService=employeeService;

    }

    @PostMapping("/employee/{employeeId}")
    public Address saveEmployeeAddress(@RequestBody Address address,@PathVariable Long employeeId){

        return addressService.saveAddress(address, employeeId);


    }

    @PostMapping("/test-rollback")
    public Address test_rollback(@RequestBody  Employee employee) throws Exception {

        Address address = new Address();
        address.setState("TEST_STATE");
        address.setCity("CheckedExceptionCity");
        address.setPostalCode("999999");

        return addressService.registerEmployeeWithAddress(employee,address);


    }
}
