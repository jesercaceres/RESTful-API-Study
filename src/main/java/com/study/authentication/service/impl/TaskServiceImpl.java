package com.study.authentication.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.study.authentication.dtos.TaskDTO;
import com.study.authentication.dtos.UserDTO;
import com.study.authentication.mappers.TaskMapper;
import com.study.authentication.mappers.UserMapper;
import com.study.authentication.model.Task;
import com.study.authentication.model.User;
import com.study.authentication.repository.TaskRepository;
import com.study.authentication.repository.UsuarioRepository;
import com.study.authentication.service.TaskService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final UsuarioRepository usuarioRepository;

    public TaskServiceImpl(TaskRepository taskRepository, UsuarioRepository usuarioRepository) {
        this.taskRepository = taskRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    @Transactional
    // Este ID É o do usuário que listaremos todas as tarefas.
    public List<TaskDTO> findAll(Long id) {

        User user = usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado."));
        List<Task> foundedTasks = taskRepository.findAll();

        /*
         * List<TaskDTO> returneTaskDTOs = foundedTasks.stream()
         * .map(task -> {
         * TaskDTO taskDTO = new TaskDTO();
         * 
         * taskDTO.setId(task.getId());
         * taskDTO.setNome(task.getNome());
         * taskDTO.setPrioridade(task.getPrioridade());
         * taskDTO.setOwnerId(task.getOwner().getId());
         * taskDTO.setDataCriacao(task.getDataCriacao());
         * taskDTO.setDescricao(task.getDescricao());
         * //taskDTO.setColaboradores
         * 
         * return taskDTO;
         * 
         * })
         * .collect(Collectors.toList());
         */

        List<TaskDTO> taskDTO = TaskMapper.INSTANCE.toTaskListDTO(foundedTasks);
        return taskDTO;
    }

    @Override
    public TaskDTO findById(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("ID invalido"));

        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setId(task.getId());
        taskDTO.setNome(task.getNome());
        taskDTO.setDescricao(task.getDescricao());
        taskDTO.setPrioridade(task.getPrioridade());
        taskDTO.setDataCriacao(task.getDataCriacao());
        taskDTO.setOwnerId(task.getOwner().getId());

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
        if (taskDTO.getPrioridade() != null) {
            task.setPrioridade(taskDTO.getPrioridade());
        }

        Task savedTask = taskRepository.save(task);

        TaskDTO returnedTaskDTO = new TaskDTO();
        returnedTaskDTO.setId(savedTask.getId());
        returnedTaskDTO.setNome(savedTask.getNome());
        returnedTaskDTO.setPrioridade(savedTask.getPrioridade());
        returnedTaskDTO.setDataCriacao(savedTask.getDataCriacao());
        returnedTaskDTO.setOwnerId(savedTask.getOwner().getId());

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

        User user = usuarioRepository.findById(taskDTO.getOwnerId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        Task task = new Task();

        task.setNome(taskDTO.getNome());
        task.setPrioridade(taskDTO.getPrioridade());
        task.setDescricao(taskDTO.getDescricao());
        task.setDataCriacao(LocalDateTime.now());
        task.setOwner(user);

        Task savedTask = taskRepository.save(task);

        TaskDTO returnedTaskDTO = new TaskDTO();
        returnedTaskDTO.setId(savedTask.getId());
        returnedTaskDTO.setNome(savedTask.getNome());
        returnedTaskDTO.setPrioridade(savedTask.getPrioridade());
        returnedTaskDTO.setDataCriacao(savedTask.getDataCriacao());
        returnedTaskDTO.setOwnerId(savedTask.getOwner().getId());

        return returnedTaskDTO;
    }

    @Transactional
    @Override
    public TaskDTO addMoreUsers(Long taskId, List<Long> ids) {

        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Tarefa não encontrada"));

        List<User> existingUsers = usuarioRepository.findAllById(ids);

        existingUsers.stream()
                .filter(user -> !task.getUsuarios().contains(user))
                .forEach(task.getUsuarios()::add);

        Task savedTask = taskRepository.save(task);

        List<UserDTO> existingUsersDTO = UserMapper.INSTANCE.toUserDTOList(existingUsers);

        TaskDTO foundedTaskDTO = new TaskDTO();
        foundedTaskDTO.setId(task.getId());
        foundedTaskDTO.setNome(task.getNome());
        foundedTaskDTO.setOwnerId(task.getOwner().getId());
        foundedTaskDTO.setColaboradores(existingUsersDTO);
        foundedTaskDTO.setDataCriacao(task.getDataCriacao());
        foundedTaskDTO.setDescricao(task.getDescricao());
        foundedTaskDTO.setPrioridade(task.getPrioridade());

        /*
         * if (foundedTaskDTO.getColaboradores() == null) {
         * foundedTaskDTO.setColaboradores(new ArrayList<>());
         * }
         */

        return foundedTaskDTO;
    }

}
