package com.study.authentication.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.study.authentication.model.Task;

public interface TaskRepository extends JpaRepository <Task, Long> {
    
}
