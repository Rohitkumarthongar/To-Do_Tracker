package com.Group8.ToDo.Authentication.model;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class User {
    @Id
    private String userEmailId;
    private String userName;
    private String userPassword;
}
