package wanglijie.model;

import java.sql.Timestamp;

/**
 * Created by $Jason.Zhang on 1/10/16.
 */
public class Employee {
    int id;
    int idNumber;
    String name;
    String email;
    String gender;
    String role;
    int age;
    double salary;
    Timestamp updateTime;


    public Employee(){}
    public Employee(int idNumber,String name,String email,String gender,String role,int age,double salary){
        this.idNumber= idNumber;
        this.name=name;
        this.gender=gender;
        this.email=email;
        this.role=role;
        this.age=age;
        this.salary=salary;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(int idNumber) {
        this.idNumber = idNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}
