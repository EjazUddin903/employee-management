package com.example.employee.management.service;

import com.example.employee.management.entity.Address;
import com.example.employee.management.entity.Employee;
import com.example.employee.management.exception.EmployeeNotFoundException;
import com.example.employee.management.repository.AddressRepository;
import com.example.employee.management.repository.EmployeeRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

@Service
public class AddressService {

    private final AddressRepository addressRepository;
    private final EmployeeRepository employeerepository;

    public AddressService(AddressRepository addressRepository, EmployeeRepository employeerepository) {
        this.addressRepository = addressRepository;
        this.employeerepository = employeerepository;
    }

    public Address saveAddress(Address address, Long employeeId) {

        Employee employee = employeerepository.findById(employeeId)
                .orElseThrow(() -> new EmployeeNotFoundException("employee not found with this id"));

        address.setEmployee(employee);
        return addressRepository.save(address);
    }

//    @Transactional
//    public Address registerEmployeeWithAddress(Employee employee, Address address) {
//
//        Employee savedEmployee = employeerepository.save(employee);
//
//        address.setEmployee(savedEmployee);
//
//        Address savedAddress = addressRepository.save(address);
//
//        try {
//            throw new RuntimeException("testing caught Exception");
//        } catch (RuntimeException ex) {
//
//            System.out.println("Exception get caught");
//
//            TransactionAspectSupport
//                    .currentTransactionStatus()
//                    .setRollbackOnly();
//
//
//        }
//        return savedAddress;
//    }

// if we want @@ransational annotation to work and want rollback happen in case of Checked exception then we need to use "rollbackFor"
//    @Transactional(rollbackFor = Exception.class)
//    public Address registerEmployeeWithAddress(Employee employee, Address address) throws Exception{
//
//        Employee savedEmployee = employeerepository.save(employee);
//
//        address.setEmployee(savedEmployee);
//
//        Address savedAddress = addressRepository.save(address);
//
//         throw  new Exception("testing checked Exception");
//
//    }

    @Transactional
    public Address registerEmployeeWithAddress(
            Employee employee,
            Address address) {

        Employee savedEmployee = employeerepository.save(employee);

        address.setEmployee(savedEmployee);
        savedEmployee.setAddress(address);

        return addressRepository.save(address);
    }

    
}
