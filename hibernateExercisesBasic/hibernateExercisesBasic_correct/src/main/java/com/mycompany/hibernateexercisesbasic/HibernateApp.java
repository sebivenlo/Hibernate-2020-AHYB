/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.hibernateexercisesbasic;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Jordan
 */
public class HibernateApp {
    public static void main(String[] args) {
        
        
        //Create students
        Student s1 = new Student();
        s1.setStudent_number(362190);
        s1.setLname("Holanda");
        s1.setfName("Michele");
        s1.setGrade(8);
        
        Student s2 = new Student();
        s2.setStudent_number(335722);
        s2.setLname("Obama");
        s2.setfName("Melania");
        s2.setGrade(3);
        
        
        //TODO make the necessarry arrangements so that NINI and REX are inside the database
        Animal a1 = new Animal();
        a1.setId(0);
        a1.setSpecies("feline");
        a1.setName("NINI");
        a1.setAge(3);
        
        Animal a2 = new Animal();
        a2.setId(1);
        a2.setSpecies("canine");
        a2.setName("REX");
        a2.setAge(5);
        /////////////////////////////////////////////////////////////////////////////////
        
        Configuration conf = new Configuration().configure().addAnnotatedClass(Student.class);
        
        //TODO Create the ANIMAL TABLE within the database; TIP a new configuration is required
        
        SessionFactory sf = conf.buildSessionFactory();
        
        
        Session session = sf.openSession();
        
        Transaction tc = session.beginTransaction();
        
        session.save(s1);
        session.save(s2);
        
        
        Student get_student = (Student) session.get(Student.class, 362190);
        System.out.println(get_student.getfName());
        
        
        get_student.setfName("Steve");
        session.update(get_student);
        
        
        //session.delete(s1);
        
        
        //HQL fetch
        Query query = session.createQuery("from Student");
        List<Student> list = query.list();
        for(int i=0;i<list.size();i++){
            System.out.println("HQL list " + list.get(i).getfName());
        } 
        
        //HCQL fetch 
        Criteria crit_query = session.createCriteria(Student.class);
        crit_query.add(Restrictions.eq("grade", 8));
        List<Student> results = crit_query.list();
        for(int i=0;i<results.size();i++){
            System.out.println("HCQL list " + results.get(i).getfName());
        } 
        
        //SQL fetch
        SQLQuery sql_query_student = session.createSQLQuery("SELECT * FROM STUDENT WHERE student_number = 335722");
        sql_query_student.addEntity(Student.class);
        List<Student> student_result = sql_query_student.list();
        for(int i=0;i<student_result.size();i++){
            System.out.println("SQL Student list " + student_result.get(i).getLname() + " " + student_result.get(i).getfName());
        } 
        
        
        //TODO1 Save NINI and REX in the Animal Table
        
        
        //TODO2 Change NINI's age to 4 
        
       
        
        //TODO3 ADD for NINI and REX SQL and HCQL 
        //1. Fetch NINI from database using SQL 
        //2. Fetch animals that are younger then 6 years old using HCQL
        
        tc.commit();
        

    }
}
