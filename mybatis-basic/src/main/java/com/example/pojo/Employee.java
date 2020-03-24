package com.example.pojo;

import java.io.Serializable;

public class Employee implements Serializable {

    private Integer id;

    private String lastName;

    private String gender;

    private String email;

    private Long version;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", lastName='" + lastName + '\'' +
                ", gender='" + gender + '\'' +
                ", email='" + email + '\'' +
                ", version=" + version +
                '}';
    }

    public Employee(String lastName, String gender, String email, Long version) {
        super();
        this.lastName = lastName;
        this.gender = gender;
        this.email = email;
        this.version = version;
    }

    public Employee(String lastName, String gender, String email) {
        super();
        this.lastName = lastName;
        this.gender = gender;
        this.email = email;
    }

    public Employee() {
        super();
    }
}
