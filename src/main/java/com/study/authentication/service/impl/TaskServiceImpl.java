package com.study.authentication.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.study.authentication.dtos.TaskDTO;
import com.study.authentication.model.Task;
import com.study.authentication.model.User;
import com.study.authentication.repository.TaskRepository;
import com.study.authentication.repository.UsuarioRepository;
import com.study.authentication.service.TaskService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final UsuarioRepository usuarioRepository;

    public TaskServiceImpl(TaskRepository taskRepository, UsuarioRepository usuarioRepository) {
        this.taskRepository = taskRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public List<TaskDTO> findAll() {

        List<Task> foundedTasks = taskRepository.findAll();

        List<TaskDTO> returneTaskDTOs = foundedTasks.stream()
                .map(task -> {
                    TaskDTO taskDTO = new TaskDTO();

                    taskDTO.setId(task.getId());
                    taskDTO.setNome(task.getNome());
                    taskDTO.setProioridade(task.getProioridade());
                    taskDTO.setUserId(task.getId());
                    taskDTO.setDataCriacao(task.getDataCriacao());
                    taskDTO.setDescricao(task.getDescricao());

                    return taskDTO; 

                })
                .collect(Collectors.toList());

        return returneTaskDTOs;
    }

    @Override
    public TaskDTO findById(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("ID invalido"));

        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setId(task.getId());
        taskDTO.setNome(task.getNome());
        taskDTO.setDescricao(task.getDescricao());
        taskDTO.setProioridade(task.getProioridade());
        taskDTO.setDataCriacao(task.getDataCriacao());
        taskDTO.setUserId(task.getUser().getId());

        return taskDTO;
    }

    @Override
    public TaskDTO update(TaskDTO taskDTO) {
        Task task = taskRepository.findById(taskDTO.getId())
                .orElseThrow(() -> new EntityNotFoundException("ID inválido"));

        if (taskDTO.getDescricao() != null) {
            task.setDescricao(taskDTO.getDescricao());
        }
        if (taskDTO.getNome() != null) {
            task.setNome(taskDTO.getNome());
        }
        if (taskDTO.getProioridade() != null) {
            task.setProioridade(taskDTO.getProioridade());
        }

        Task savedTask = taskRepository.save(task);

        TaskDTO returnedTaskDTO = new TaskDTO();
        returnedTaskDTO.setId(savedTask.getId());
        returnedTaskDTO.setNome(savedTask.getNome());
        returnedTaskDTO.setProioridade(savedTask.getProioridade());
        returnedTaskDTO.setDataCriacao(savedTask.getDataCriacao());
        returnedTaskDTO.setUserId(savedTask.getUser().getId());

        return returnedTaskDTO;
    }

    @Override
    public void delete(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User nao encontrado"));
        taskRepository.delete(task);
    }

    @Override
    public TaskDTO createTask(TaskDTO taskDTO) {

        User user = usuarioRepository.findById(taskDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Task task = new Task();

        task.setNome(taskDTO.getNome());
        task.setProioridade(taskDTO.getProioridade());
        task.setDescricao(taskDTO.getDescricao());
        task.setDataCriacao(LocalDateTime.now());
        task.setUser(user);

        Task savedTask = taskRepository.save(task);

        TaskDTO returnedTaskDTO = new TaskDTO();
        returnedTaskDTO.setId(savedTask.getId());
        returnedTaskDTO.setNome(savedTask.getNome());
        returnedTaskDTO.setProioridade(savedTask.getProioridade());
        returnedTaskDTO.setDataCriacao(savedTask.getDataCriacao());
        returnedTaskDTO.setUserId(savedTask.getUser().getId());

        return returnedTaskDTO;
    }

}
