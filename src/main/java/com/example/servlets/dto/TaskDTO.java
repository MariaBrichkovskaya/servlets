package com.example.servlets.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@ToString
public class TaskDTO {
    private String name;
    private String description;


    private LocalDateTime time;

    public TaskDTO(String name, String description) {
        this.name = name;
        this.description = description;
        this.time=LocalDateTime.now();
    }

}
