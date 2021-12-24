package com.cheese.admin.model.response;

import com.cheese.domain.domain.users.User;
import lombok.Getter;

import java.time.LocalDateTime;

public class UserDto {

    @Getter
    public static class  UserResponse {
        private Long id;
        private String name;
        private String cp;
        private String sex;
        private Integer cheeseMoneyTotal;
        private String birthYear;
        private String birthMonth;
        private String birthDay;
        private String email;
        private boolean isActive;
        private boolean isAccount;
        private boolean isReceivePush;
        private boolean isReceiveCheese;
        private boolean isReceivePay;
        private boolean isReceivePromotion;
        private LocalDateTime deletedAt;
        private String deletedReason;
        private LocalDateTime lastLogin;
    }

    @Getter
    public static class UserListResponse {
        private Long id;
        private String name;
        private String cp;
        private String sex;
        private Integer cheeseMoneyTotal;
        private String birthYear;
        private String birthMonth;
        private String birthDay;
        private String email;
        private boolean isActive;
        private LocalDateTime lastLogin;

        public UserListResponse(User entity){
            this.id = entity.getId();
            this.name = entity.getName();
            this.cp = entity.getCp();
            this.sex = entity.getSex();
            this.email = entity.getEmail();
            this.cheeseMoneyTotal = entity.getCheeseMoneyTotal();
            this.birthYear = entity.getBirthYear();
            this.birthMonth = entity.getBirthMonth();
            this.birthDay = entity.getBirthDay();
            this.isActive = entity.isActive();
            this.lastLogin = entity.getLastLogin();
        }
    }
}
