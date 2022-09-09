package edu.hanoi.spring.springwebsocket.model;


import javax.persistence.*;

@Entity
@Table(name = "hn_group", uniqueConstraints = {@UniqueConstraint(columnNames = "id")})
public class Group {

    private Integer id;
    private String name;

    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "name", nullable = false, length = 100)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
