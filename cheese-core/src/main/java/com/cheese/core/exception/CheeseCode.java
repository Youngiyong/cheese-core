package com.cheese.core.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
public enum CheeseCode {
    // TODO: Need to refine
    SUCCESS(200, "C0000", "OK"),
    UNAUTHORIZED(401,"C0003","UNAUTHORIZED"),
    UNAUTHORIZED_INVALID_ACCESS_TOKEN(401,"C0003","UNAUTHORIZED_INVALID_ACCESS_TOKEN"),
    UNAUTHORIZED_INSUFFICIENT_PERMISSIONS(401,"C0003","UNAUTHORIZED_INSUFFICIENT_PERMISSIONS"),
    // Common
    INVALID_PARAMETER(400, "C0004", "Invalid Request Data"),
    INVALID_GRANT(400, "C0004", "Invalid Bad Credentials Username/Password"),
    INVALID_INPUT_VALUE(400, "C0001", " Invalid Input Value"),
    METHOD_NOT_ALLOWED(405, "C0002", " Invalid Input Value"),
    HANDLE_ACCESS_DENIED(403, "C0006", "Access is Denied"),
    INVALID_TYPE_VALUE(400, "C0001", "Invalid Input Type Value"),
    INTERNAL_SERVER_ERROR(500, "C0001", "Internal Server Error"),
    // Member
    EMAIL_DUPLICATION(400, "C0001", "Email is Duplication"),
    LOGIN_INPUT_INVALID(400, "C0002", "Login input is invalid"),

    //Store
    NOT_FOUND_STORE(404, "C8000", "존재하지 않는 가맹점입니다."),
    NOT_FOUND_STORE_USER(404, "C8000", "존재하지 않는 가맹점입니다."),

    INTERNAL_SERVER_ERROR_STORE_LIST(500, "C8002", "STORE LIST API INTERNAL SERVER ERROR"),
    INTERNAL_SERVER_ERROR_STORE_USER_LIST(500, "C8002", "STORE USER LIST API INTERNAL SERVER ERROR"),

    //User
    NOT_FOUND_USER(404, "C9000", "존재하지 않는 유저입니다.."),
    INTERNAL_SERVER_ERROR_USER_LIST(500, "C9002", "존재하지 않는 유저 리스트입니다."),

    INTERNAL_ORDER_CONVERT_SERVER_ERROR(500, "C8000", "Internal Server Error"),

    //Category
    CATEGORY_LIST_NOT_FOUND(404, "C2000", "카테고리 리스트 정보가 존재하지 않습니다."),
    CATEGORY_NOT_FOUND(404, "C2001", "카테고리 정보가 존재하지 않습니다.");

//    ATTESTATION_CERTIFICATE_ERROR(1),
//    U2F_ATTESTATION_KEY_NOT_ECC_TYPE(2),
//    U2F_ATTESTATION_USER_KEY_INVALID(3),
//    U2F_ATTESTATION_AAGUID_INVALID(4),
//    SIGNATURE_VERIFICATION_ERROR(4),
//    PACKED_ATTESTATION_ALGORITHM_NOT_MATCHED(5),
//    USER_PUBLIC_KEY_INVALID_KEY_SPEC(6),
//
//    INVALID_ATTESTATION_FORMAT(52),
//    INVALID_AUTHENTICATOR_DATA(53),
//    CREDENTIAL_NOT_INCLUDED(54),
//    CREDENTIAL_NOT_FOUND(55),
//    ASSERTION_SIGNATURE_VERIFICATION_FAIL(56),
//
//    INVALID_ORIGIN(80),
//
//    INTERNAL_SERVER_ERROR(999);


    @Getter private final int status;
    @Getter private final String code;
    @Getter private final String message;


}
