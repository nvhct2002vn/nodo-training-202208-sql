package edu.hanoi.spring.springwebsocket.model;

import javax.persistence.*;

@Entity
@Table(name = "hn_user", uniqueConstraints = {@UniqueConstraint(columnNames = "username")})
public class User {
    private String username;
    private String password;
    private String email;
    private Integer age;
    private Integer groupId;

    @Id
    @Column(name = "username", unique = true, nullable = true)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "password", nullable = false)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "age")
    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Column(name = "groupId", nullable = false)
    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }
}
