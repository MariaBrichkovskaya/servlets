package com.example.servlets.dto;

import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@ToString
@NoArgsConstructor
public class TaskDTO {
    Long id;
    private String name;
    private String description;


    private LocalDateTime time;

    public TaskDTO(String name, String description) {
        this.name = name;
        this.description = description;
        this.time=LocalDateTime.now();
    }

}
