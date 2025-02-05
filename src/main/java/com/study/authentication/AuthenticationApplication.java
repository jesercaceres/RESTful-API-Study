package com.study.authentication;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.study.authentication.dtos.TaskDTO;
import com.study.authentication.dtos.UserDTO;
import com.study.authentication.model.Task;
import com.study.authentication.model.User;
import com.study.authentication.repository.UsuarioRepository;
import com.study.authentication.service.TaskService;
import com.study.authentication.service.UserService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class AuthenticationApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthenticationApplication.class, args);
    }

    @Bean
    CommandLineRunner run(UserService userService, TaskService taskService, UsuarioRepository usuarioRepository) {
        return args -> {
            

            // Criando e salvando o primeiro usuário
            UserDTO userRegisterDTO = new UserDTO();
            userRegisterDTO.setNome("João Silva");
            userRegisterDTO.setEmail("joao.silva@example.com");
            userRegisterDTO.setSenha("123456");
            UserDTO savedUser = userService.registerUser(userRegisterDTO);

            System.out.println("Usuário salvo com sucesso:");
            System.out.println("ID: " + savedUser.getId());
            System.out.println("Nome: " + savedUser.getNome());
            System.out.println("Email: " + savedUser.getEmail());

            // Criando e salvando o segundo usuário
            UserDTO userRegisterDTO2 = new UserDTO();
            userRegisterDTO2.setNome("Carlos Cardoso");
            userRegisterDTO2.setEmail("carlos.cardoso@example.com");
            userRegisterDTO2.setSenha("ABCDEFGH");
            UserDTO savedUser2 = userService.registerUser(userRegisterDTO2);

            System.out.println("Usuário salvo com sucesso:");
            System.out.println("ID: " + savedUser2.getId());
            System.out.println("Nome: " + savedUser2.getNome());
            System.out.println("Email: " + savedUser2.getEmail());

            // Criando e salvando uma tarefa
            TaskDTO createTaskDTO = new TaskDTO();
            createTaskDTO.setNome("Tarefa de Teste");
            createTaskDTO.setOwnerId(savedUser.getId()); // Associando o primeiro usuário como dono
            createTaskDTO.setPrioridade("Urgente");
            createTaskDTO.setDescricao("Descrição da tarefa de teste");

            TaskDTO savedTask = taskService.createTask(createTaskDTO);

            TaskDTO createAnotherTaskDTO = new TaskDTO();
            createAnotherTaskDTO.setNome("Tarefa de Teste222");
            createAnotherTaskDTO.setOwnerId(savedUser.getId()); // Associando o primeiro usuário como dono
            createAnotherTaskDTO.setPrioridade("Urgente");
            createAnotherTaskDTO.setDescricao("Descrição da tarefa de teste");

            TaskDTO saveddTask = taskService.createTask(createAnotherTaskDTO);

            System.out.println("Tarefa salva no banco de dados:");
            System.out.println("Nome: " + savedTask.getNome());
            System.out.println("ID: " + savedTask.getId());
            System.out.println("Usuário responsável: " + savedTask.getOwnerId());
            System.out.println("Prioridade: " + savedTask.getPrioridade());
            System.out.println("Descrição: " + savedTask.getDescricao());

            // Testando o método addMoreUsers
            System.out.println("\nTESTANDO O MÉTODO addMoreUsers");

            // IDs dos usuários que serão adicionados como colaboradores
            List<Long> ids = new ArrayList<>();
            ids.addAll(Arrays.asList(savedUser.getId(), savedUser2.getId())); // Adicionando os dois usuários

            // Chamando o método addMoreUsers e guardando o retorno em uma variável
            TaskDTO updatedTask = taskService.addMoreUsers(savedTask.getId(), ids);
            // Exibindo o objeto completo (se o toString estiver configurado adequadamente)
            System.out.println("Testando retorno do AddMoreUsers" + updatedTask);
            
            // Ou, se preferir, imprimindo cada campo individualmente
            System.out.println("Colaboradores adicionados à tarefa:");
            updatedTask.getColaboradores().forEach(colaborador -> {
                System.out.println("ID: " + colaborador.getId());
                System.out.println("Nome: " + colaborador.getNome());
                System.out.println("Email: " + colaborador.getEmail());
                System.out.println("-----------------------------");
            });

            //Listando tarefas de usuarios.
            List <TaskDTO> ownerTasksDTO = taskService.findAll(savedUser.getId());
            System.out.println("Testando retorno do método D");
            for (TaskDTO task : ownerTasksDTO) {
                System.out.println("Owner: " + task.getOwnerId());
                System.out.println("Nome:" + task.getNome());
                System.out.println("Descrição:" + task.getDescricao());
                System.out.println("Identificador da Tarefa:" + task.getId());
               
            }

        };
    }
}