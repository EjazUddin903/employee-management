package com.example.employee.management.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String name;
    @Email
    @NotBlank
    @Column(unique = true)
    private String email;
    private String  department;
    @Positive
    private int salary;

    public Employee(){

    }

    public long getId(){
        return id;
    }

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


     public void setName(String name){
        this.name= name;

    }

     public void setEmail(String email){
        this.email= email;
    }

     public void setDepartment(String department){
        this.department=department;
    }

     public void setSalary(int salary){
        this.salary=salary;
    }

    public void setId(Long id){
        this.id= id;
    }

}

