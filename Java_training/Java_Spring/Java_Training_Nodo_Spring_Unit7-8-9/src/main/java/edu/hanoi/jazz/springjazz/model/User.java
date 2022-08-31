package edu.hanoi.jazz.springjazz.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "hn_user")
public class User implements Comparable<User> {
    @Id
    private String username;

    private String password;

    private String email;

    private Integer age;

//    private int groupId;

    @ManyToOne
    @JoinColumn(name = "groupId")
    private Group group;

    @Override
    public int compareTo(User o) {
        return age - o.age;
    }
}
