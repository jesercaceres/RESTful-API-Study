package com.study.authentication.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.study.authentication.dtos.TaskDTO;
import com.study.authentication.model.Task;

@Mapper
public interface TaskMapper {
    TaskMapper INSTANCE = Mappers.getMapper(TaskMapper.class);

    TaskDTO toTaskDTO(Task task);

    Task toTask(TaskDTO taskDTO);

    List<TaskDTO> toTaskListDTO(List<Task> tasks);
    
    List<Task> toTaskList(List <TaskDTO> taskDTOS);
}
