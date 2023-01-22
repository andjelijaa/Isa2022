package com.example.backend.service;

import com.example.backend.models.User;
import com.example.backend.models.request.CreateQuestionDto;
import com.example.backend.models.response.UserDto;

import java.security.Principal;
import java.util.List;

public interface UserService {
    User checkActivationCode(String code);

    User findByEmail(String Email);

    void save(User user);

    User getActivatedUserFromPrincipal(Principal principal) throws Exception;

    UserDto updateUser(User user, UserDto userDto);

    void sendQuestions(User user, List<CreateQuestionDto> questions);

}