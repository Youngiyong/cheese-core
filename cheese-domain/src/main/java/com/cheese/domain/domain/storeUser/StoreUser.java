package com.cheese.domain.domain.storeUser;


import com.cheese.domain.domain.BaseTimeEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "store_users")
public class StoreUser extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;    // internal

    @Column(nullable = false, length = 50)
    private String name;

    @Column(length = 50)
    private String email;

    @Column(nullable = false, length = 1)
    private String cp;

    @Column(length = 4)
    private String birthYear;

    @Column(length = 2)
    private String birthMonth;

    @Column(length = 2)
    private String birthDay;

    @Column(insertable = false)
    private Boolean isStaff;

    @Column(insertable = false)
    private Boolean isApprove;

    @Column(insertable = false)
    private Boolean isReceivePush;

    @Column(insertable = false)
    private Boolean isReceiveCheese;

    @Column(insertable = false)
    private Boolean isReceivePay;

    @Column(insertable = false)
    private Boolean isReceivePromotion;

    @Column(length = 200, insertable = false)
    private String deletedReason;

//    @OneToMany(cascade = CascadeType.ALL,
//            fetch = FetchType.LAZY,
//            mappedBy = "userKeyEntity")
//    private List<ItemEntity> item = new ArrayList<>();
    @Column(name = "deleted_at", insertable = false)
    private LocalDateTime deletedAt;


}

