package com.study.authentication.model;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@ToString
@Data
@Entity
@Table(name = "tbl_tarefa")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nome;

    @Column
    private String prioridade;

    @Column
    private String descricao;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime dataCriacao;

    //anotando a tabela intermediaria com JoinTable "USER_TASK"
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "USER_TASK", joinColumns = @JoinColumn(name = "TASK_ID"), inverseJoinColumns = @JoinColumn(name = "USER_ID"))
    private List<User> usuarios;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;

}