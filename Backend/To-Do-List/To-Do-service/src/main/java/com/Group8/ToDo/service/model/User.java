package com.Group8.ToDo.service.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    @ToString
    @Document
@Data
    public class User {
        @Id
        private String userEmailId;
        private String userName;
        private String userGender;
        private Date dateOfBirth;
        private String userPassword;
        private List<Task> userTask;
    private List<Task> archieveTask;
    private List<Task> deletedTask;
}

