package com.example.login.service;

import com.example.login.config.TokenConfig;
import com.example.login.dto.request.UserLoginRequest;
import com.example.login.dto.request.UserRegisterRequest;
import com.example.login.dto.request.UserRequest;
import com.example.login.dto.response.UserLoginResponse;
import com.example.login.dto.response.UserRegisterResponse;
import com.example.login.dto.response.UserResponse;
import com.example.login.entity.User;
import com.example.login.exception.UserAlreadyExistException;
import com.example.login.exception.UserNotFoundException;
import com.example.login.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final PasswordEncoder encoder;
    private final AuthenticationManager authenticationManager;
    private final TokenConfig tokenConfig;


    //REGISTER
    @Transactional
    public UserRegisterResponse regiter(UserRegisterRequest request){
        if (repository.existsByEmail(request.email())){
            throw new UserAlreadyExistException(request.email());
        }
        User newUser = new User();
        newUser.setUsername(request.username());
        newUser.setEmail(request.email());
        newUser.setPassword(encoder.encode(request.password()));

        repository.save(newUser);
        return UserRegisterResponse.fromEntity(newUser);
    }

    //LOGIN
    @Transactional
    public UserLoginResponse login(UserLoginRequest request){
        UsernamePasswordAuthenticationToken userAndPass = new UsernamePasswordAuthenticationToken(request.email() , request.password());
        Authentication authentication = authenticationManager.authenticate(userAndPass);

        User user = (User) authentication.getPrincipal();
        String token = tokenConfig.generateToken(user);

        return new UserLoginResponse(token);
    }


    //READ
    @Transactional(readOnly = true)
    public Page<UserResponse> list(Pageable pageable){
        return repository.findAll(pageable).map(UserResponse::fromEntity);
    }

    //READ BY ID
    @Transactional(readOnly = true)
    public  UserResponse findById(Long id){
        User user = findEntityById(id);
        return UserResponse.fromEntity(user);
    }

    //UPDATE
    @Transactional
    public UserResponse update(Long id , UserRequest request){
        User user = findEntityById(id);
        Optional<User> userEmail = repository.findByEmail(request.email());
        if (userEmail.isPresent() && userEmail.get().equals(user)){
            throw new UserAlreadyExistException(request.email());
        }
        request.fillIn(user);
        user.setPassword(encoder.encode(request.password()));
        User updatedUser = repository.save(user);
        return UserResponse.fromEntity(updatedUser);
    }

    //DELETE
    @Transactional
    public void delete(Long id){
        User user = findEntityById(id);
        repository.delete(user);
    }




    private User findEntityById(Long id){
        return repository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }


}
