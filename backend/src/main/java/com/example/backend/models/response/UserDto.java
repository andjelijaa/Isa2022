package com.example.backend.models.response;

import com.example.backend.models.enums.Genter;
import com.example.backend.models.User;
import lombok.Data;

@Data
public class UserDto {
  private String email;
  private String ime;
  private String prezime;
  private String grad;
  private String adresa;
  private String drzava;
  private String phone;
  private String JMBG;
  private Genter genter;
  private String role;
  private int penali;

  public UserDto(User user) {
    this.email = user.getEmail();
    this.ime = user.getIme();
    this.prezime = user.getPrezime();
    this.grad = user.getGrad();
    this.adresa = user.getAdresa();
    this.drzava = user.getDrzava();
    this.phone = user.getPhone();
    this.JMBG = user.getJmbg();
    this.genter = user.getPol();
    this.role = user.getRole().name();
    this.penali = user.getPenali();
  }
}
