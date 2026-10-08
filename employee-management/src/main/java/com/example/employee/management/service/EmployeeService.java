package com.example.employee.management.service;

import com.example.employee.management.dto.EmployeeRequestDTO;
import com.example.employee.management.dto.EmployeeResponseDTO;
import com.example.employee.management.entity.Address;
import com.example.employee.management.entity.Employee;
import com.example.employee.management.exception.DuplicateEmailException;
import com.example.employee.management.exception.EmployeeNotFoundException;
import com.example.employee.management.repository.EmployeeRepository;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository repository;

    public EmployeeService(EmployeeRepository repository){
        this.repository=repository;

    }

    public  EmployeeResponseDTO createEmployee(EmployeeRequestDTO employeeRequest){
        Employee employee = new Employee();
        employee.setName(employeeRequest.getName());
        employee.setEmail(employeeRequest.getEmail());
        employee.setDepartment(employeeRequest.getDepartment());
        employee.setSalary(employeeRequest.getSalary());

        if (repository.existsByEmail(employeeRequest.getEmail())){
            throw new DuplicateEmailException(
                    "An employee with this email already exists"
            );
        }

        Employee savedEmployee = repository.save(employee);

         EmployeeResponseDTO response = new  EmployeeResponseDTO();

        response.setId(savedEmployee.getId());
        response.setName(savedEmployee.getName());
        response.setEmail(savedEmployee.getEmail());
        response.setDepartment(savedEmployee.getDepartment());
        response.setSalary(savedEmployee.getSalary());
        return  response;


    }

    public List< EmployeeResponseDTO> allEmployees(){

        List<Employee> employees =repository.findAll();
        List< EmployeeResponseDTO> response = new ArrayList<>();

        for(Employee employee: employees){
             EmployeeResponseDTO dto = new  EmployeeResponseDTO();

            dto.setId(employee.getId());
            dto.setName(employee.getName());
            dto.setEmail(employee.getEmail());
            dto.setDepartment(employee.getDepartment());
            dto.setSalary(employee.getSalary());

            response.add(dto);
        }

        return  response;
    }

    public  EmployeeResponseDTO employeeById(Long id){
         Employee employee= repository.findById(id)
                 .orElseThrow(()->
                         new EmployeeNotFoundException("Employee Not Found with this id: " + id));

          EmployeeResponseDTO response = new  EmployeeResponseDTO();


        response.setId(employee.getId());
        response.setName(employee.getName());
        response.setEmail(employee.getEmail());
        response.setDepartment(employee.getDepartment());
        response.setSalary(employee.getSalary());

        return   response;
    }

    public void deleteEmployee(Long id){
        Employee employee = repository.findById(id)
                .orElseThrow(() ->
                        new EmployeeNotFoundException(
                                "Employee Not Found with this id: " + id));

        repository.deleteById(id);
    }

    public EmployeeResponseDTO updateEmployee(EmployeeRequestDTO employeeRequest, Long id){
        // 1. Fetch the existing employee or throw an exception
        Employee employee = repository.findById(id)
                .orElseThrow(() ->
                        new EmployeeNotFoundException(
                                "Employee Not Found with this id: " + id));

        if(repository.existsByEmailAndIdNot(employeeRequest.getEmail(),id)){
            throw new DuplicateEmailException(
                    "An employee with this email already exists"
            );
        }

        // 2. Update the employee's fields
        employee.setName(employeeRequest.getName());
        employee.setEmail(employeeRequest.getEmail());
        employee.setDepartment(employeeRequest.getDepartment());
        employee.setSalary(employeeRequest.getSalary());

// 3. Save the updated employee
        Employee emp = repository.save(employee);

        EmployeeResponseDTO response = new  EmployeeResponseDTO();

        response.setId(emp.getId());
        response.setName(emp.getName());
        response.setEmail(emp.getEmail());
        response.setDepartment(emp.getDepartment());
        response.setSalary(emp.getSalary());

        return   response;

    }

    public Address  getEmployeeAddess(Long id){
        Employee employee = repository.findById(id).orElseThrow(() -> new EmployeeNotFoundException("employee with this id is not found: " + id));

        return  employee.getAddress();


    }

}
