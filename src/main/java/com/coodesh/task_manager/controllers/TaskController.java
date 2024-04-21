package com.coodesh.task_manager.controllers;

import com.coodesh.task_manager.dto.RequestDTO;
import com.coodesh.task_manager.dto.ResponseDTO;
import com.coodesh.task_manager.services.TaskServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/task")
public class TaskController {

    private final TaskServices taskServices;

    @Autowired
    public TaskController(TaskServices taskServices) {
        this.taskServices = taskServices;
    }

    @GetMapping
    public ResponseEntity<List<ResponseDTO>> getAllTasks() {
        return ResponseEntity.ok(taskServices.getAllTasks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO> getTaskById(@PathVariable Long id) {
        return ResponseEntity.ok(taskServices.getTaskById(id));
    }

    @PostMapping
    public ResponseEntity<ResponseDTO> registerTask(@RequestBody RequestDTO requestDTO) {
        return ResponseEntity.ok(taskServices.registerTask(requestDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDTO> updateTask(@PathVariable Long id, @RequestBody RequestDTO requestDTO) {
        return ResponseEntity.ok(taskServices.updateTask(id, requestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeTask(@PathVariable Long id) {
        taskServices.removeTask(id);
        return ResponseEntity.ok().build();
    }
}
