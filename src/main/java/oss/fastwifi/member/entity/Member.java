package oss.fastwifi.member.entity;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    private String uid;

    private String password;

    private String name;

    private String email;

    Enumerated
    @Column(columnDefinition = "VARCHAR(10) DEFAULT 'REFUSAL'")
    private SchoolCertification schoolCertification;

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
    public Member(String uid, String password, String name, String email, SchoolCertification schoolCertification){
        this.uid = uid;
        this.password = password;
        this.name = name;
        this.email = email;
        this.schoolCertification = schoolCertification;
    }

    public void updatePassword(String password) {
        this.password = password;
    }


}
