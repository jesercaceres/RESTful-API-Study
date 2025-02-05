package com.study.authentication.dtos;

import java.time.LocalDateTime;
import java.util.List;

import org.antlr.v4.runtime.misc.NotNull;

import com.study.authentication.model.User;

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
public class TaskDTO {
    private Long id;

    @SuppressWarnings("deprecation")
    @NotNull
    private String nome;

    private String prioridade;

    private String descricao;

    private LocalDateTime dataCriacao;

    private Long ownerId;

    private UserDTO ownerDTO;

    private List<UserDTO> colaboradores;

}
