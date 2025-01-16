package com.study.authentication.service;

import java.util.List;
import com.study.authentication.dtos.TaskDTO;

public interface TaskService {
    
    List<TaskDTO> findAll();

    TaskDTO findById(Long id);

    TaskDTO createTask(TaskDTO task);

    TaskDTO update(TaskDTO taskDTO);

    void delete(Long id);
}
