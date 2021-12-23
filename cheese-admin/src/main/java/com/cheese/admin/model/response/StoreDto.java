package com.cheese.admin.model.response;

import com.cheese.domain.domain.store.Store;
import com.cheese.domain.domain.storeUser.StoreUser;
import lombok.Getter;

import javax.persistence.Column;
import java.time.LocalDateTime;

public class StoreDto {

    @Getter
    public static class  StoreUserResponse {
        private Long id;    // internal
        private String name;
        private String email;
        private String cp;
        private String birthYear;
        private String birthMonth;
        private String birthDay;
        private Boolean isStaff;
        private Boolean isApprove;
        private Boolean isReceivePush;
        private Boolean isReceiveCheese;
        private Boolean isReceivePay;
        private Boolean isReceivePromotion;
        private String deletedReason;
        private LocalDateTime deletedAt;
    }

    @Getter
    public static class StoreUserListResponse {
        private Long id;
        private String name;
        private String email;
        private String cp;
        private String birthYear;
        private String birthMonth;
        private String birthDay;
        private boolean isApprove;
        private boolean isStaff;

        public StoreUserListResponse(StoreUser entity){
            this.id = entity.getId();
            this.name = entity.getName();
            this.email = entity.getEmail();
            this.cp = entity.getCp();
            this.birthYear = entity.getBirthYear();
            this.birthMonth = entity.getBirthMonth();
            this.birthDay = entity.getBirthDay();
            this.isApprove = entity.getIsApprove();
            this.isStaff = entity.getIsStaff();
        }
    }

    @Getter
    public static class StoreResponse {
        private Long id;
        private String storeNumber;
        private Integer storeGroup;
        private Integer categoryId;
        private String name;
        private String email;
        private String businessLicenseNumber;
        private String ceoName;
        private String ceoPhone;
        private String fax;
        private String address;
        private String addressExtra;
        private String homepageUrl;
        private Boolean isApprove;
        private Boolean isActive;
        private Boolean isHoliday;
        private Boolean isContractBond;
        private Boolean isBlacklist;
        private String bankName;
        private String bankCode;
        private String bankAccount;
        private String bankAccountName;
        private String lat;
        private String lng;
        private LocalDateTime workStart;
        private LocalDateTime workEnd;
        private Float discountItemsReward;
        private String deletedReason;
    }

    @Getter
    public static class StoreListResponse {
        private Long id;
        private String storeNumber;
        private String name;
        private String ceoName;
        private String fax;
        private String address;
        private boolean isActive;
        private String bankCode;

        public StoreListResponse(Store entity){
            this.id = entity.getId();
            this.storeNumber = entity.getStoreNumber();
            this.name = entity.getName();
            this.ceoName = entity.getCeoName();
            this.fax = entity.getFax();
            this.address = entity.getAddress();
            this.isActive = entity.getIsActive();
            this.bankCode = entity.getBankCode();
        }
    }

}
