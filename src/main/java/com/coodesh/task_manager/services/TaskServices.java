package com.coodesh.task_manager.services;

import com.coodesh.task_manager.dto.RequestDTO;
import com.coodesh.task_manager.dto.ResponseDTO;
import com.coodesh.task_manager.entities.Task;
import com.coodesh.task_manager.repositories.TaskRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TaskServices {

    private final TaskRepository taskRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public TaskServices(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
        this.modelMapper = new ModelMapper();
    }

    public List<ResponseDTO> getAllTasks() {
        List<Task> tasks = taskRepository.findAll();

        List<ResponseDTO> responseList = new ArrayList<>();

        for (Task task: tasks) {
            responseList.add(modelMapper.map(task, ResponseDTO.class));
        }

        return responseList;
    }

    public ResponseDTO getTaskById(Long id) {
        Task task = taskRepository.findById(id).orElseThrow();

        return modelMapper.map(task, ResponseDTO.class);
    }

    public ResponseDTO registerTask(RequestDTO requestDTO) {
        Task task = modelMapper.map(requestDTO, Task.class);

        task.setCreatedAt(LocalDateTime.now());
        task.setFinishedAt(null);
        task.setClosed(false);

        return modelMapper.map(taskRepository.save(task), ResponseDTO.class);
    }

    public ResponseDTO updateTask(Long id, RequestDTO requestDTO) {
        Task task = taskRepository.findById(id).orElseThrow();

        task.setName(requestDTO.getName() == null ? task.getName() : requestDTO.getName());
        task.setDescription(requestDTO.getDescription() == null ? task.getDescription() : requestDTO.getDescription());
        if (requestDTO.getClosed() != null) {
            task.setClosed(requestDTO.getClosed());
            task.setFinishedAt(LocalDateTime.now());
        }

        return modelMapper.map(taskRepository.save(task), ResponseDTO.class);
    }

    public void removeTask(Long id) {
        taskRepository.deleteById(id);
    }
}
