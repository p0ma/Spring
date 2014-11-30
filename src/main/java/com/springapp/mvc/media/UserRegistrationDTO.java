package com.springapp.mvc.media;

import entities.auth.FieldMatch;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;

@FieldMatch(fields = {"password", "email"}, verifyFields = {"confirmPassword", "confirmEmail"})
public class UserRegistrationDTO {

    public UserRegistrationDTO() {
    }

    public UserRegistrationDTO(String name, String password, String email, String confirmPassword, String confirmEmail) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.confirmPassword = confirmPassword;
        this.confirmEmail = confirmEmail;
    }

    @NotEmpty(message = "May not be empty")
    @Size(max = 32, min = 4, message = "Username should be 4-32 symbols")
    private String name;

    @NotEmpty(message = "May not be empty")
    @Size(max = 32, min = 4, message = "Password should be 4-32 symbols")
    private String password;

    @NotEmpty(message = "May not be empty")
    @Email(message = "Not a well-formed email address")
    private String email;

    @NotEmpty(message = "May not be empty")
    @Size(max = 32, min = 4, message = "Password confirmation should be 4-32 symbols")
    private String confirmPassword;

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    @NotEmpty(message = "May not be empty")
    @Size(max = 32, min = 4, message = "Email confirmation should be 4-32 symbols")
    private String confirmEmail;

    public String getConfirmEmail() {
        return confirmEmail;
    }

    public void setConfirmEmail(String confirmEmail) {
        this.confirmEmail = confirmEmail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
