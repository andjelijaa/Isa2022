package com.example.backend.utils;

import com.example.backend.models.User;
import com.example.backend.repository.UserRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ScheduleService {
  private final UserRepository userRepository;

  public ScheduleService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Scheduled(cron = "0 0 1 * * *")
  public void deletePenali() {
    List<User> users = userRepository.findAll();
    for(User u : users) {
      u.setPenali(0);
      userRepository.save(u);
    }
  }
}
