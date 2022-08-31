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
public class User {
    @Id
    private String username;

    private String password;

    private String email;

    private Integer age;

    private int groupId;

}
