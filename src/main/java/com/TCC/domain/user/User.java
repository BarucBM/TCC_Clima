package com.TCC.domain.user;

import com.TCC.domain.notification.Notification;
import com.TCC.domain.preferences.Preference;
import jakarta.persistence.*;
import lombok.*;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String email;

    private String googleApiToken;

    private String password;

    private UserRole role;

    @CreationTimestamp
    private LocalDateTime createdAt;

    // @OneToMany(mappedBy = "user")
    // private List<Preference> preference;

    // @ManyToMany
    // @JoinTable(
    //         name = "user_notification",
    //         joinColumns = @JoinColumn(name = "user_id"),
    //         inverseJoinColumns = @JoinColumn(name = "notification_id")
    // )
    // private List<Notification> notifications;

    // @OneToMany
    // private UserNotification userNotification;

    public User(String email, String password, UserRole role) {
        this.email = email;
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + role.name()));
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }
}
