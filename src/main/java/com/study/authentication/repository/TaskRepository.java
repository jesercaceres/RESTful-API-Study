package com.study.authentication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.study.authentication.model.Task;
import com.study.authentication.model.User;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    
}