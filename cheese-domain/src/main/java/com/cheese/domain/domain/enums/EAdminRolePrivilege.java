package com.cheese.domain.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum EAdminRolePrivilege {
    STORE_PRIVILEGE,
    STORE_READ_PRIVILEGE,
    STORE_WRITE_PRIVILEGE,
    STORE_DELETE_PRIVILEGE,
    STORE_DETAIL_PRIVILEGE,
    USER_PRIVILEGE,
    USER_READ_PRIVILEGE,
    USER_WRITE_PRIVILEGE,
    USER_DELETE_PRIVILEGE,
    USER_DETAIL_PRIVILEGE
}
