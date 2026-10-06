package com.example.employee.management.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public class EmployeeRequestDTO {
    @NotBlank
    private String name;
    @NotBlank
    @Email
    private String email;
    private String department;
    @Positive
    private int salary;

    public String getName(){
        return name;
    }

    public String getEmail(){
        return email;
    }

    public String getDepartment(){
        return department;
    }

    public int getSalary(){
        return salary;
    }

    public void  setName(String name){
        this.name = name;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void setDepartment(String department){
        this.department=department;
    }

    public void setSalary(int salary){
        this.salary=salary;

    }

}
