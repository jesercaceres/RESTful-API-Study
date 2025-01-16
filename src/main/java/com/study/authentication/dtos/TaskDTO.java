package com.study.authentication.dtos;

import java.time.LocalDateTime;

import org.antlr.v4.runtime.misc.NotNull;

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

    private String proioridade;

    private String descricao;

    private LocalDateTime dataCriacao;

    private Long userId;
}
