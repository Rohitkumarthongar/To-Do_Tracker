package com.Group8.ToDo.service.rabbitMQ;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
  private String userName;
  private String userEmailId;
  private String userPassword;
}
