/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.hibernateexercisesadvanced;

import java.util.Set;

/**
 *
 * @author Jordan
 */
public class Employee {
    
    private int id;
    private String firstName; 
    private String lastName;   
    private int salary;
    private Set certificates;

    public Employee() {
    }
    
    
    public Employee(String firstName, String lastName, int salary, Set certificates) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
        this.certificates = certificates;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public Set getCertificates() {
        return certificates;
    }

    public void setCertificates(Set certificates) {
        this.certificates = certificates;
    }
    
    
}
