package com.gymfitness.backend.payload.request;

import javax.validation.constraints.*;

import org.hibernate.validator.constraints.Range;
 
public class SignUpRequest {
    @NotBlank
    @Size(min = 3, max = 40)
    private String firstname;

    @NotBlank
    @Size(min = 3, max = 40)
    private String surname;
 
    @NotBlank
    @Size(max = 50)
    @Email
    private String email;
    
    private String level;
    
    @NotBlank
    @Size(min = 6, max = 40)
    private String password;

    @NotNull(message= "Bank Number must be provided")
    @Range(min = 1)
    private Integer bankno;

    @NotNull(message= "Clearing Number must be provided")
    @Range(min = 1)
    private Integer clearingno;
  
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
 
    public String getEmail() {
        return email;
    }
 
    public void setEmail(String email) {
        this.email = email;
    }
 
    public String getPassword() {
        return password;
    }
 
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getLevel() {
      return this.level;
    }
    
    public void setUserLevel(String level) {
      this.level = level;
    }

    public Integer getBankno() {
        return bankno;
    }
    public void setBankno(Integer bankno) {
        this.bankno = bankno;
    }

    public Integer getClearingno() {
        return clearingno;
    }
    public void setClearingno(Integer clearingno) {
        this.clearingno = clearingno;
    }
}