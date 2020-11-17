/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.hibernateexercisesadvanced;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import org.hibernate.HibernateException; 
import org.hibernate.Session; 
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 *
 * @author Jordan
 */
public class HibernateApp {
    
    private static SessionFactory factory;
    
    private void listEmployees(List empList){
        Iterator iter = empList.iterator();
        while(iter.hasNext()){
            Employee employee = (Employee) iter.next(); 
            System.out.println("----------------");
            System.out.print("First Name: " + employee.getFirstName()); 
            System.out.print("  Last Name: " + employee.getLastName()); 
            System.out.println("  Salary: " + employee.getSalary());
        }
    }
    
    public static void main(String[] args) {
        
        
        try {
            factory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) { 
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex); 
        }
        
        //Create Objects to persist        
        HashSet certificates = new HashSet();
        certificates.add(new Certificate("MCA"));
        certificates.add(new Certificate("MBA"));
        certificates.add(new Certificate("PMP")); 
        Employee emp1 = new Employee("John", "Doe", 10000, certificates);
        Employee emp2 = new Employee("Jane", "Doe", 11000, certificates);
        Employee emp3 = new Employee("Jim", "Doe", 9000, certificates);
        
        
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();
        //To do: Insert first employee into database 
        //To do: Use query to retrieve all stored employees
        tx.commit();
        session.close();
        
        
        
        Session session2 = factory.openSession();
        Transaction tx2 = session2.beginTransaction();
        //To do: Insert second employee and chainge salary of first 
        //To do: Use query to retrieve all stored employees
        tx2.commit();
        session2.close();
        
        
        
        Session session3 = factory.openSession();
        Transaction tx3 = session3.beginTransaction();
        //To do: Insert third employee and remove second
        //To do: Use query to retrieve all stored employees
        tx3.commit();
        session3.close();
    }
    
}
