/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.hibernateexercisesbasic;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author Jordan
 */
@Entity
public class Student {
    //TODO Add notations
    //TODO Add Certificate
    
    @Id
    private int student_number;
    private String fName;
    private String lname;
    private int grade;

    public int getStudent_number() {
        return student_number;
    }

    public void setStudent_number(int student_number) {
        this.student_number = student_number;
    }
    
   public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }


    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    
    
    
}
