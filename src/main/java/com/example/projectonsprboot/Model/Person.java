package com.example.projectonsprboot.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "updatepersondata")
@Getter
@Setter
@NoArgsConstructor
public class Person implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "first_name")
    @NotEmpty(message ="Name should not be empty")
    @Size(min=2, max=50, message = "Name out of bounds 2<name<50")
    private String name;
    @Column(name = "email")
    @NotEmpty(message ="Email should not be empty")
    @Email(message = "Email should be valid")
    private String email;
    @Column(name = "password")
    @NotEmpty(message ="Password should not be empty")
    private String password;
    @Column(name="coins")
    private Integer coins=0;
    @Column(name="progress")
    private Integer progress=0;

    public Person(Person person) {
        this.name = person.getName();
        this.email = person.getEmail();
        this.password = person.getPassword();
    }

    public Person(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person person)) return false;
        return id == person.id && Objects.equals(getName(), person.getName()) && Objects.equals(getEmail(), person.getEmail()) && Objects.equals(getPassword(), person.getPassword()) && Objects.equals(getCoins(), person.getCoins()) && Objects.equals(getProgress(), person.getProgress());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, getName(), getEmail(), getPassword(), getCoins(), getProgress());
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", coins=" + coins +
                ", progress=" + progress +
                '}';
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
