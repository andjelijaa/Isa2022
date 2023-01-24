package com.example.backend.service.Impl;

import com.example.backend.exceptions.UnauthorizedException;
import com.example.backend.models.request.CreateQuestionDto;
import com.example.backend.models.response.UserDto;
import com.example.backend.repository.UserRepository;
import com.example.backend.service.UserService;
import com.example.backend.models.User;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    public final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;

    }

    @Override
    public User getActivatedUserFromPrincipal(Principal principal)throws Exception{
        if(principal == null){
            return null;
        }
        User u = userRepository.findByEmail(principal.getName());
        if(u != null && u.getActivation() == null){
            return u;
    }else {
        throw new UnauthorizedException();   }
    }

    @Override
    public UserDto updateUser(User user, UserDto userDto) {
        user.setEmail(userDto.getEmail());
        user.setIme(userDto.getIme());
        user.setPrezime(userDto.getPrezime());
        user.setAdresa(userDto.getAdresa());
        user.setGrad(userDto.getGrad());
        user.setDrzava(userDto.getDrzava());
        user.setPhone(userDto.getPhone());
        user.setJmbg(userDto.getJMBG());
        user.setPol(userDto.getGenter());
        userRepository.save(user);
        return new UserDto(user);
    }

    @Override
    public void sendQuestions(User user, List<CreateQuestionDto> questions) {
        user.setQuestions(questions);
        userRepository.save(user);
    }

    @Override
    public User findByActivation(String code) {
        return userRepository.findByActivation(code);
    }

    @Override
    public User checkActivationCode(String code) {
        User user = userRepository.findByActivation(code);
        if(user == null){
            return null;
        }
        return user;
    }

    @Override
    public User findByEmail(String username) {
        return userRepository.findByEmail(username);
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }
}
