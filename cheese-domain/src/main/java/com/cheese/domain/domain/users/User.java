package com.cheese.domain.domain.users;


import com.cheese.domain.domain.BaseTimeEntity;
import com.cheese.domain.domain.userRoles.UserRole;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
@Builder
@Entity
@Table(	name = "users")
public class User extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 128)
    private String name;

    @Column(nullable = false, length = 20)
    private String cp;

    @Column(length = 1)
    private String sex;

    @Column(insertable = false)
    private Integer cheeseMoneyTotal;

    @Column(insertable = false)
    private String birthYear;

    @Column(insertable = false)
    private String birthMonth;

    @Column(insertable = false)
    private String birthDay;

    @Column(length = 45)
    private String email;

    @Column(length = 200)
    private String deletedReason;

//    @ManyToMany(fetch = FetchType.LAZY)
//    @JoinTable(	name = "user_role_join",
//            joinColumns = @JoinColumn(name = "user_id"),
//            inverseJoinColumns = @JoinColumn(name = "role_id"))
//    private Set<UserRole> userRoles = new HashSet<>();

    @Column(nullable = false, insertable = false)
    private boolean isActive;

    @Column(nullable = false, insertable = false)
    private boolean isAccount;

    @Column(nullable = false, insertable = false)
    private boolean isReceivePush;

    @Column(nullable = false, insertable = false)
    private boolean isReceiveCheese;

    @Column(nullable = false, insertable = false)
    private boolean isReceivePay;

    @Column(nullable = false, insertable = false)
    private boolean isReceivePromotion;

    @LastModifiedDate
    @Column(insertable = false)
    private LocalDateTime deletedAt;

    @Column(insertable = false)
    private LocalDateTime lastLogin;


}
