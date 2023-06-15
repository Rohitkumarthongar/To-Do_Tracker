package com.Group8.ToDo.Archieve.Service.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Document
public class Task {

    @Transient
    public static final String SEQUENCE_NAME = "task_sequence";


    private int taskId;
    private String taskHeading;
    private String taskCategory;
    private String taskDescription;
    private Date taskEndDate;
    private Date taskStartDate;
    private boolean status;
    private Priority priority;

}
