package com.study.authentication;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.study.authentication.dtos.TaskDTO;
import com.study.authentication.dtos.UserDTO;
import com.study.authentication.model.Task;
import com.study.authentication.service.TaskService;
import com.study.authentication.service.UserService;
import java.util.List;

@SpringBootApplication
public class AuthenticationApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthenticationApplication.class, args);
    }

    @Bean
    CommandLineRunner run(UserService userService, TaskService taskService) {
        return args -> {
            // Criando um UserDTO para teste
            UserDTO userRegisterDTO = new UserDTO();
            userRegisterDTO.setNome("João Silva");
            userRegisterDTO.setEmail("joao.silva@example.com");
            userRegisterDTO.setSenha("senhaSegura123");

            // Chamando o método registerUser para salvar o usuário
            UserDTO savedUser = userService.registerUser(userRegisterDTO);

            // Exibindo o usuário salvo no console
            System.out.println("Usuário salvo com sucesso:");
            System.out.println("ID: " + savedUser.getId());
            System.out.println("Nome: " + savedUser.getNome());
            System.out.println("Email: " + savedUser.getEmail());

            TaskDTO createTaskDTO = new TaskDTO();
            createTaskDTO.setNome("teste");
            createTaskDTO.setUserId(1L);
            createTaskDTO.setProioridade("Urgente");
            createTaskDTO.setDescricao("tarefa para teste");

            TaskDTO savedTask = taskService.createTask(createTaskDTO);

            System.out.println("Tarefa salva no database:");

            System.out.println("Nome:" + savedTask.getNome());
            System.out.println("ID:" + savedTask.getId());
            System.out.println("Usuário responsável: " + savedTask.getUserId());
            System.out.println("Prioridade: " + savedTask.getProioridade());
            System.out.println("Descrição:" + savedTask.getDescricao());

            System.out.println("TESTANDO FIND BY ID" + taskService.findById(1L));

            // Testando UPDATE

            TaskDTO updateTask = new TaskDTO();
        
            updateTask.setProioridade("Sem pressa");

            updateTask.setId(1L);

            TaskDTO savedTask2 = taskService.update(updateTask);

             List<TaskDTO> foundedTasks = taskService.findAll();

             System.out.println("Executando o método FindALL" +     foundedTasks);
        };      
    }
}