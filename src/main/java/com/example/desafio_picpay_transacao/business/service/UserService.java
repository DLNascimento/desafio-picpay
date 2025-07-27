package com.example.desafio_picpay_transacao.business.service;

import com.example.desafio_picpay_transacao.infrastructure.dto.UserDTO;
import com.example.desafio_picpay_transacao.infrastructure.entity.UserEntity;
import com.example.desafio_picpay_transacao.infrastructure.exception.UserNotFoundException;
import com.example.desafio_picpay_transacao.infrastructure.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserEntity findUserById(Long id){
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("Usuário não encontrado"));
    }

    public UserDTO saveUser(UserDTO userDTO){

        UserEntity newUser = new UserEntity();
        newUser.setFullName(userDTO.fullname());
        newUser.setDocument(userDTO.document());
        newUser.setEmail(userDTO.email());
        newUser.setBalance(userDTO.balance());
        newUser.setUserType(userDTO.userType());
        newUser.setPassword(userDTO.password());

        UserEntity userSaved = userRepository.save(newUser);

        return new UserDTO(userSaved.getFullName(), userSaved.getDocument(), userSaved.getEmail(),
                userSaved.getPassword(), userSaved.getBalance(), userSaved.getUserType());

    }
}
