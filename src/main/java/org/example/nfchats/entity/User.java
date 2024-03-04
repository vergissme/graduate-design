package org.example.nfchats.entity;

import javax.persistence.*;

@Table(name="user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer user_id;
    @Column(name = "username")
    private  String username;
    @Column(name = "password")
    private  String password;
    @Transient
    private String token;
    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token=token;
    }
    public User() {
    }

    @Column(name = "email")
    private  String email;
    @Column(name = "phone_number")
    private  String phone_number;
    @Column(name = "age")
    private Integer age;
    @Column(name = "sex")
    private  String sex;
    private  String user_type;

    public User(Integer user_id, String username, String password, String email, String phone_number, Integer age, String sex, String user_type) {
        this.user_id = user_id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone_number = phone_number;
        this.age = age;
        this.sex = sex;
        this.user_type = user_type;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public User(Integer user_id, String username, String password, String email, String phone_number) {
        this.user_id = user_id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone_number = phone_number;
    }

    public User(Integer user_id, String username, String password, String email, String phone_number, Integer age, String sex) {
        this.user_id = user_id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone_number = phone_number;
        this.age = age;
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getUser_type() {
        return user_type;
    }

    public void setUser_type(String user_type) {
        this.user_type = user_type;
    }
}
