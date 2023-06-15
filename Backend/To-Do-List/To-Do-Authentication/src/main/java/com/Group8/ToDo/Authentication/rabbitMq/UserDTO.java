package com.Group8.ToDo.Authentication.rabbitMq;

import lombok.*;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class UserDTO {

        private String userEmailId;
        private String userName;
        private String userPassword;
}
