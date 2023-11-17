package oss.fastwifi.user.entity;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Collection;
import java.util.HashSet;

@Getter
@Setter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User implements UserDetails {
    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    private String uid;

    private String password;

    private String name;

    private String email;

    @ColumnDefault("False")
    private Boolean isAuth;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new HashSet<GrantedAuthority>();
    }

    @Override
    public String getUsername() {
        return id.toString();
    }

    @Override
    public String getPassword() {
        return password;
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
        return false;
    }

    @Builder
    public User(String uid, String password, String name, String email){
        this.uid = uid;
        this.password = password;
        this.name = name;
        this.email = email;
    }

    public void updatePassword(String password) {
        this.password = password;
    }


}
