package com.study.authentication.service.impl;

import org.springframework.stereotype.Service;

import com.study.authentication.dtos.UserDTO;
import com.study.authentication.service.UserService;
import com.study.authentication.model.User;

import jakarta.transaction.Transactional;

import com.study.authentication.repository.UsuarioRepository;

@Service
public class UserServiceImpl implements UserService {

    private final UsuarioRepository usuarioRepository;

    // Injeção via construtor
    public UserServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    @Transactional
    public UserDTO registerUser(UserDTO userRegisterDTO) {

        User usuario = new User();

        usuario.setNome(userRegisterDTO.getNome());
        usuario.setEmail(userRegisterDTO.getEmail());
        usuario.setSenha(userRegisterDTO.getSenha());

        // salvar o "User" no banco de dados.
        User savedUser = usuarioRepository.save(usuario);

        UserDTO userDTO = new UserDTO();
        userDTO.setId(savedUser.getId());
        userDTO.setNome(savedUser.getNome());
        userDTO.setEmail(savedUser.getEmail());

        return userDTO;
    }

}
