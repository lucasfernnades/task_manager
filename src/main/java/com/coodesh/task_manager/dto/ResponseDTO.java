package com.coodesh.task_manager.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDTO {

    private Long id;

    private String name;

    private String description;

    private LocalDateTime createdAt;

    private LocalDateTime finishedAt;

    private Boolean closed;
}
