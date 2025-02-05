package com.study.authentication.service;

import java.util.List;
import com.study.authentication.dtos.TaskDTO;
import com.study.authentication.dtos.UserDTO;

public interface TaskService {
    
    //necessário adicionar validação para listar tarefas de usuario x
    List<TaskDTO> findAll(Long id);

    TaskDTO findById(Long id);

    TaskDTO createTask(TaskDTO task);

    TaskDTO update(TaskDTO taskDTO);

    TaskDTO addMoreUsers(Long taskId, List<Long> ids);

    void delete(Long id);
}
