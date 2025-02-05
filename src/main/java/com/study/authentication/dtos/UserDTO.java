package com.study.authentication.dtos;

import java.util.List;

import org.antlr.v4.runtime.misc.NotNull;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Data
@Setter
@EqualsAndHashCode
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private Long id;

    @SuppressWarnings("deprecation")
    @NotNull
    @Size(min = 2, max = 50, message = "O nome deve ter entre 2 e 30 caracteres.")
    private String nome;

    @Email
    private String email;

    private List<TaskDTO> tarefas;

    private String senha;
}
