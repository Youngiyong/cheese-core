package com.cheese.domain.domain.users;


import com.cheese.domain.domain.BaseTimeEntity;
import com.cheese.domain.domain.userRoles.UserRole;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table(	name = "users")
public class User extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", nullable = false, length = 100)
    private String username;

    @Column(name = "cp", nullable = false, length = 20)
    private String phoneNumber;

    @Column(name = "sex", nullable = true, length = 1)
    private String sex;

    @Column(name = "birth_year", nullable = true, length = 4)
    private String birthYear;

    @Column(name = "birth_month", nullable = true, length = 2)
    private String birthMonth;

    @Column(name = "birth_day", nullable = true, length = 2)
    private String birthDay;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(	name = "user_role_join",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<UserRole> userRoles = new HashSet<>();

    @Column(name ="is_active", nullable = false, insertable = false)
    private boolean isActive;

    @Column(name ="is_account", nullable = false, insertable = false)
    private boolean isAccount;

    @Column(name ="is_qr", nullable = false, insertable = false)
    private boolean isQr;

    @LastModifiedDate
    @Column(name = "deleted_at", nullable = true, insertable = false)
    private LocalDateTime deletedAt;

    @Builder
    public User(String username, String phoneNumber) {
        this.username = username;
        this.phoneNumber = phoneNumber;
    }

}
