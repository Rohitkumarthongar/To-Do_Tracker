package com.Group8.ToDo.service.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Data

public class Task {
    @Transient
    public static final String SEQUENCE_NAME="task_sequence";

    @Id
    private int taskId;
    private String taskHeading ;
    private String taskCategory;
    private String taskDescription;
    private Date taskEndDate;
    private Date taskStartDate;
    private boolean status;
    private Priority priority;

    }
