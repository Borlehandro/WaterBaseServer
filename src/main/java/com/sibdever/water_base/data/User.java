package com.sibdever.water_base.data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true, nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    private boolean enabled;
    private boolean accountNonExpired;
    private boolean credentialsNonExpired;
    private boolean accountNonLocked;

    @ElementCollection(targetClass = UserAuthority.class)
    @JoinTable(name = "users_authorities", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "authority", nullable = false)
    @Enumerated(EnumType.STRING)
    private List<UserAuthority> userAuthorities;

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
}
