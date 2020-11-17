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
        s1.setStudent_number(338860);
        s1.setLname("Hanz");
        s1.setfName("Pierre");
        s1.setGrade(8);
        
        Student s2 = new Student();
        s2.setStudent_number(393921);
        s2.setLname("Holland");
        s2.setfName("Smith");
        s2.setGrade(3);
        
        
        //TODO make the necessarry arrangements so that NINI and REX are inside the database
        Animal a1 = new Animal();
        a1.setId(2);
        a1.setSpecies("feline");
        a1.setName("CHILLY");
        a1.setAge(3);
        
        Animal a2 = new Animal();
        a2.setId(3);
        a2.setSpecies("canine");
        a2.setName("BEETHOVEN");
        a2.setAge(5);
        /////////////////////////////////////////////////////////////////////////////////
        
        Configuration conf = new Configuration().configure().addAnnotatedClass(Student.class);
        
        //TODO Create the ANIMAL TABLE within the database; TIP addAnnotatedClass is required
        conf.addAnnotatedClass(Animal.class);
        
        
        SessionFactory sf = conf.buildSessionFactory();
        
        
        Session session = sf.openSession();
        
        Transaction tc = session.beginTransaction();
        
        session.save(s1);
        session.save(s2);
        
        Student get_student = (Student) session.get(Student.class, 338860);
        System.out.println(get_student.getfName());
        
        get_student.setfName("Steve");
        session.update(get_student);
        
        
        session.delete(s1);
        
        
        //HQL 
        Query query = session.createQuery("from Student");
        List<Student> list = query.list();
        for(int i=0;i<list.size();i++){
            System.out.println("HQL list " + list.get(i).getfName());
        } 
        
        //HCQL
        Criteria crit_query = session.createCriteria(Student.class);
        crit_query.add(Restrictions.eq("grade", 8));
        List<Student> results = crit_query.list();
        for(int i=0;i<results.size();i++){
            System.out.println("HCQL list " + results.get(i).getfName());
        } 
        
        //TODO1 Save NINI and REX in the Animal Table
        session.save(a1);
        session.save(a2);
        
        //TODO2 Change NINI's age to 4 
        a1.setAge(4);
        session.update(a1);
        
        //TODO3 ADD for NINI and REX SQL and HCQL 
        //1. Fetch NINI from database using SQL 
        // TIPS after you create the query object 
        //Does not work
        SQLQuery sql_query = session.createSQLQuery("SELECT * FROM ANIMAL WHERE id = 0");
        sql_query.addEntity(Animal.class);
        List<Animal> nini_result = sql_query.list();
        for(int i=0;i<nini_result.size();i++){
            System.out.println("SQL Animal list " + nini_result.get(i).getName());
        } 
        //2. Fetch animals that are younger then 6 years old using HCQL
        Criteria crit_query_animal = session.createCriteria(Animal.class);
        crit_query_animal.add(Restrictions.lt("age", 6));
        List<Animal> results_crit_animaal = crit_query_animal.list();
        for(int i=0;i<results_crit_animaal.size();i++){
            System.out.println("HCQL Animal list " + results_crit_animaal.get(i).getName());
        } 
        
        tc.commit();
        

    }
}
