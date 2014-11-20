package entities.auth;

import entities.drilling.model.WorkingDataSet;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Collection;

@Entity
public class User implements UserDetails {

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private WorkingDataSet workingDataSet;

    public WorkingDataSet getWorkingDataSet() {
        return workingDataSet;
    }

    public void setWorkingDataSet(WorkingDataSet workingDataSet) {
        this.workingDataSet = workingDataSet;
    }

    @NotEmpty(message = "May not be empty")
    @Size(max = 32, min = 4, message = "Username should be 4-32 symbols")
    private String name;

    @NotEmpty(message = "May not be empty")
    @Size(max = 32, min = 4, message = "Password should be 4-32 symbols")
    private String password;

    @Email(message = "Not a well-formed email address")
    private String email;

    private String role;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return getName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    public User() {
    }

    public User(String name, String password, String email, String role, WorkingDataSet workingDataSet, Long id) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.role = role;
        this.workingDataSet = workingDataSet;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
