package com.sibdever.water_base.data;

import net.bytebuddy.implementation.bind.annotation.Default;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true, nullable = false)
    private final String username;
    @Column(nullable = false)
    private final String password;

    private boolean enabled = true;
    private boolean accountNonExpired = true;
    private boolean credentialsNonExpired = true;
    private boolean accountNonLocked = true;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    @ElementCollection(targetClass = UserAuthority.class)
    @JoinTable(name = "users_authorities", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "authority", nullable = false)
    @Enumerated(EnumType.STRING)
    private List<UserAuthority> userAuthorities;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    public List<UserAuthority> getUserAuthorities() {
        return userAuthorities;
    }

    public UserRole getRole() {
        return role;
    }
}
