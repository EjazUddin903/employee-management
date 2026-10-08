package com.example.employee.management.controller;


import com.example.employee.management.dto.EmployeeRequestDTO;
import com.example.employee.management.dto.EmployeeResponseDTO;
import com.example.employee.management.entity.Address;
import com.example.employee.management.entity.Employee;
import com.example.employee.management.service.EmployeeService;
import jakarta.validation.Path;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@RestController
public class EmployeeController {

    private final EmployeeService employeeservice;


    public EmployeeController(EmployeeService employeeservice){
        this.employeeservice = employeeservice;
    }

    @GetMapping("/")
    public String message(){
        return "Employee Management Application";
    }



    @PostMapping("/create")
    public ResponseEntity<EmployeeResponseDTO> createEmployee(@Valid @RequestBody EmployeeRequestDTO employeeRequest){
          EmployeeResponseDTO savedEmployee = employeeservice.createEmployee(employeeRequest);
          return  ResponseEntity.status(HttpStatus.CREATED).body(savedEmployee);
    }
    @GetMapping("/employees")
    public ResponseEntity<List<EmployeeResponseDTO>> allEmployees(){

        List<EmployeeResponseDTO> response = employeeservice.allEmployees();
        return new ResponseEntity<>(response,HttpStatus.OK) ;

    }
    @GetMapping("employee/{id}")
    public ResponseEntity<EmployeeResponseDTO> findById(@PathVariable Long id){
        EmployeeResponseDTO foundEmployee = employeeservice.employeeById(id);
       return  new ResponseEntity<>(foundEmployee,HttpStatus.OK);

//        if(foundEmployee.isPresent()){
//            return new ResponseEntity<>(foundEmployee.get(),HttpStatus.OK);
//        }
//        else{
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
    }
    @DeleteMapping("employees/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        employeeservice.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/employees/{id}")
    public ResponseEntity<EmployeeResponseDTO> updateEmployee(@RequestBody EmployeeRequestDTO employee, @PathVariable Long id){
        EmployeeResponseDTO updatedEmployee = employeeservice.updateEmployee(employee,id);
        return new ResponseEntity<>(updatedEmployee,HttpStatus.OK);


    }

    @GetMapping("employee/address/{id}")
    public Address getEmpAddress(@PathVariable Long id){

         Address address = employeeservice.getEmployeeAddess(id);

//         return  new ResponseEntity<>(address,HttpStatus.OK);
        return address;

    }





}
