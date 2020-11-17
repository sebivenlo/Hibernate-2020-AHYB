# Hibernate2020

Hibernate workshop 2019 repository. The workshop is provided by Andi Hermann and Yordan Baynov.

This is a project meant to give students a better grasp of the technology known as Hibernate. 

# Project Prerequisites:
1. Netbeans (recommended version 8.2 with jdk1.8.0_261)
3. Hibernate installed on Netbeans 
4. Docker Desktop installed  
5. PgAdmin III installed 

# Hibernate Installation
Open Netbeans

There are 2 paths you can take here depending on the version of IDE you are currently using.

The 1st scenario would be that Netbeans will have the Hibernate framework preinstalled.

To check if you have Hibernate on your IDE, press new FILE and look for the Hibernate option.

If you found the Hibernate Configuration wizard you are all set up.

In the case of Hibernate not being available by default, it is required to mannually install it.
To do so follow the steps below:

1. Navigate to libraries under Tools 
2. Navigate to Plugins menu under Tools.
3. Click on available plugins
4. Search for hibernate in the search bar. 
5. If found click on the box next to it and press install.

In case you can not find the pluging under avaliable pluging do the following:

1. Go to the Setting tab in the Plugins menue
2. Click on the Add button
3. Set a name of your choise 
4. Set URL to http://updates.netbeans.org/netbeans/updates/8.2/uc/final/distribution/catalog.xml.gz
5. In the Settings window, set Check Interval to "Every Startup" option
6. Restart NetBeans

This will hopefully add the Hibernate plugin under Avaliable Plugins

# Docker configuration
These steps asume that Docker Desctop has already been installed
1. Pull the postgres image from Docker hub. In command prompt type:
```
 docker pull postgres:9.1
```
2. Make shure no containers are currently running. This is done through the Docker Desctop App
3. Create Docker container. Use command:
```
docker container run -d --name=pgsql -p 8080:5432 -e POSTGRES_PASSWORD=secret -e PGDATA=/pgdata postgres:9.1
```
4. Check if container is created. Use command:
```
docker ps
```

# Starting up the Project:
For the successful participation in the Hibernate workshop, postgress as well as a netbeans project that will connect to postgress database are required.
Follow the steps bellow to set up your environment.

1. Pull the postgres image from Docker hub. (Use command X) Additionaly, describe a method to verify that you properly set up ... ( Explain step)
2. Open the files X and Y ( give name)  project provided within the repository. (Explain step)

# Basic Assignments:
1. How to insert data into a database using Hibernate
2. How to fetch data from a database using Hibernate
3. How to update data from a database using Hibernate
4. How to write in HQL, SQL, and HCQL.

Step1: Get project directory hibernateExercisesBasic running on NETBEANS IDE. There are multiple ways to do so, one of them would be to download repository zip, unzip file, get project directory out and use the "Open Project" within the IDE.

Class Guide: You have 3 classes, main class called HibernateApp and 2 other: Animal and Student. The persistence of the Student object will be provided as an example by the workshop presentors. The Animal class is expected to be finalized by the participants of the workshop.

Step2: You need the following to code snippet to be able to insert data into your database. You have the explanation of each line, present as a comment which is loacated next to the code line.
```
Configuration conf = new Configuration().configure().addAnnotatedClass(Student.class); //Used to map to annotated Student class and used to instatiate SessionFactory
        
SessionFactory sf = conf.buildSessionFactory(); //Session factory used to instantiate the Session Object, since it's an inteface (Cannot be instantiated via new)       
        
Session session = sf.openSession(); //Session object used to implement the save method
        
Transaction tc = session.beginTransaction(); //Transaction object required for ACID principles
        
session.save(s1); // to insert a Student class in the database
        
tc.commit(); // we commit transaction changes within the application
```
Step3: Make sure that you have Hibernate plugin installed on your IDE (view Hibernate Installation section). If you do add, new Hibernate Configuration Wizard file to your project (Found in the example in "Other Sources").  Additionally make sure you have the following dependencies set in your pom.xml file: 
```
 <dependency>
            <groupId>org.hibernate</groupId>
            <artifactId>hibernate-core</artifactId>
            <version>4.1.6.Final</version>
</dependency>
        
<dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>5.1.38</version>
</dependency>
```
This dependencies will allow you to use Hibernate Objects and methods.

Step4: Setup your Hibernate Configuration Wizard. For this exercise we use the following example:
```
<hibernate-configuration>
  <session-factory>
    <property name="hibernate.dialect">org.hibernate.dialect.PostgreSQLDialect</property>
    <property name="hibernate.connection.driver_class">org.postgresql.Driver</property>
    <property name="hibernate.connection.url">jdbc:postgresql://localhost:8080/postgres</property>
    <property name="hibernate.connection.username">postgres</property>
    <property name="hibernate.connection.password">secret</property> 
    <property name="hbm2ddl.auto">update</property>  // 2 option here update to maintain all rows or create for deleting all previous data to create a fresh database
  </session-factory>
</hibernate-configuration>
```
Step5: Use PgAdmin 3 to connect to the docker container mentioned in Docker Configuration section of the Git repository.
To do so go under File -> "Add Server"
and complete the form as follows: 
Name = postgres
Port = 8080
Password = secret
Finnaly press ok and you'll see the database which was created in the Docker Container

Step6: Run HibernateApp class and view changes in the database through PgAdmin SQL query
# Advanced Assignments
This exercise requires that you have a Docker image for postgreSQL pulled and a container installed

Firstly, in a terminal, run the command:
```
docker container exec -it pgsql  psql -U postgres
```
Here pgsql is the name of the container and psql is the command. This opens a command line SQL interface that comes with the postgres image.

Afterwareds, run the command:
```
\dt
```
This checks for existing relations in the database. Since we havent created anything yet, it should tell you that there are none. Leave the terminal open for now.

Once this is done, we are ready to start coding!

The Advanced Hibernate exercise is comprised of two parts. One deals with mapping an XML file, the other with data insertion and retrieval.

First we'll start with the mapping. This is done inside of the Employee.hbo.xml file in the Other Sources folder. Since we're implementing a Many to Many relaton ship, we need to use a Java set to store the classes with which another class is related to. Imagine that we have an Employee class and a CertificateType class. An employee can have multyple certificates and many employees can have the same certificate. For this reason, the Employee class has a set of certificates as one of the attributes.

This attribute has to be mapped inside the XML using a <set> element. Keep in mind that for a Many to Many relationship a third table is needed in the database.

After this, the Certificate class' attributes have to be mapped as well. Look at the Java class to see what needs to be included.

The second part has three subsections. The require that Employee objects have to be inserted, modified and deleted from the database.
For this the session methods save(), update(), delete() should be used. 

After the completion of the exercises, repeat the command:
```
\dt
```
Now the three created tables sould be seen. 


# Hibernate documentation
https://hibernate.org/orm/documentation/5.4/
