package com.cheese.domain.domain.store;


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
@Entity
@Table(name = "stores")
public class Store extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;    // internal

    @Column(nullable = false, length = 20)
    private String storeNumber;

    @Column
    private Integer storeGroupId;

    @Column
    private Integer categoryId;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(length = 50, insertable = false)
    private String email;

    @Column(nullable = false, length = 300)
    private String businessLicenseNumber;

    @Column(nullable = false, length = 20)
    private String ceoName;

    @Column(nullable = false, length = 20)
    private String ceoPhone;

    @Column(length = 20, insertable = false)
    private String fax;

    @Column(length = 256, insertable = false)
    private String address;

    @Column(length = 256, insertable = false)
    private String addressExtra;

    @Column(length = 256, insertable = false)
    private String homepageUrl;

    @Column(insertable = false)
    private Boolean isApprove;

    @Column(insertable = false)
    private Boolean isActive;

    @Column(insertable = false)
    private Boolean isHoliday;

    @Column(insertable = false)
    private Boolean isContractBond;

    @Column(insertable = false)
    private Boolean isBlacklist;

    @Column(length = 32, insertable = false)
    private String bankName;

    @Column(length = 32, insertable = false)
    private String bankCode;

    @Column(length = 32, insertable = false)
    private String bankAccount;

    @Column(length = 100, insertable = false)
    private String bankAccountName;

    @Column(length = 20, insertable = false)
    private String lat;

    @Column(length = 20, insertable = false)
    private String lng;

    @Column(insertable = false)
    private LocalDateTime workStart;

    @Column(insertable = false)
    private LocalDateTime workEnd;

    @Column(insertable = false)
    private Float discountItemsReward;

    @Column(length = 200, insertable = false)
    private String deletedReason;

//    @OneToMany(cascade = CascadeType.ALL,
//            fetch = FetchType.LAZY,
//            mappedBy = "userKeyEntity")
//    private List<ItemEntity> item = new ArrayList<>();
    @Column(name = "deleted_at", insertable = false)
    private LocalDateTime deletedAt;

    @Builder
    public Store(String storeNumber,
                        Integer storeGroupId,
                        Integer categoryId,
                        String name,
                        String ceoName,
                        String ceoPhone,
                        String businessLicenseNumber) {
        this.storeNumber = storeNumber;
        this.storeGroupId = storeGroupId;
        this.categoryId = categoryId;
        this.name = name;
        this.ceoName = ceoName;
        this.ceoPhone = ceoPhone;
        this.businessLicenseNumber = businessLicenseNumber;
    }


}

