create table admin
(
    id         INT  not null
        primary key AUTO_INCREMENT,
    username       varchar(100) not null unique ,
    email      varchar(50) not null unique,
    password VARCHAR(255) NOT NULL,
    is_active TINYINT DEFAULT 1 NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NULL,
    deleted_at TIMESTAMP NULL
) ENGINE = InnoDb DEFAULT CHARSET=utf8;

create table admin_roles
(
    id         INT  not null
        primary key AUTO_INCREMENT,
    name       varchar(100) not null unique,
    description     varchar(50) null
) ENGINE = InnoDb DEFAULT CHARSET=utf8;


create table admin_role_privileges
(
    id         INT  not null
        primary key AUTO_INCREMENT,
    name       varchar(100) not null unique,
    description     varchar(50) null
) ENGINE = InnoDb DEFAULT CHARSET=utf8;


create table admin_role_privilege_join
(
    id         INT  not null
        primary key AUTO_INCREMENT,
    role_id int not null,
    privilege_id int not null
) ENGINE = InnoDb DEFAULT CHARSET=utf8;


create table admin_role_join
(
    id         INT  not null
        primary key AUTO_INCREMENT,
    admin_id int not null,
    role_id int not null
) ENGINE = InnoDb DEFAULT CHARSET=utf8;




INSERT INTO admin (id, username, email, password) VALUES (1, 'test1', 'test1@test.com', '$2a$10$3MUolsky4nfLXHOgP3EHS.zRN4OM/hbgvnihP.VJdnwpdBqpbv3mC');
INSERT INTO admin (id, username, email, password) VALUES (2, 'test2', 'test2@test.com', '$2a$10$3MUolsky4nfLXHOgP3EHS.zRN4OM/hbgvnihP.VJdnwpdBqpbv3mC');
INSERT INTO admin (id, username, email, password) VALUES (3, 'admin', 'admin@example.com', '$2a$10$3MUolsky4nfLXHOgP3EHS.zRN4OM/hbgvnihP.VJdnwpdBqpbv3mC');

# ROLE_ADMIN,
# ROLE_ADMIN_CUSTOM,
# ROLE_ADMIN_READ

INSERT INTO admin_roles(id, name, description) VALUES (1, 'ROLE_ADMIN', '시스템관리자');
INSERT INTO admin_roles(id, name, description) VALUES (2, 'ROLE_ADMIN_CUSTOM', '커스텀관리자');
INSERT INTO admin_roles(id, name, description) VALUES (3, 'ROLE_ADMIN_READ', '일반관리자(읽기)');


INSERT INTO admin_role_join (admin_id, role_id) VALUES (1, 2);
INSERT INTO admin_role_join (admin_id, role_id) VALUES (2, 3);
INSERT INTO admin_role_join (admin_id, role_id) VALUES (3, 1);

INSERT INTO admin_role_privileges(id, name) VALUES (1, 'STORE_PRIVILEGE'); -- 가맹점 관리 권한
INSERT INTO admin_role_privileges(id, name) VALUES (2, 'STORE_READ_PRIVILEGE'); -- 가맹점 관리 리스트 권한
INSERT INTO admin_role_privileges(id, name) VALUES (3, 'STORE_DETAIL_PRIVILEGE'); -- 가맹점 관리 상세 권한
INSERT INTO admin_role_privileges(id, name) VALUES (4, 'STORE_DELETE_PRIVILEGE'); -- 가맹점 관리 삭제 권한
INSERT INTO admin_role_privileges(id, name) VALUES (5, 'STORE_WRITE_PRIVILEGE'); -- 가맹점 관리 추가 권한

INSERT INTO admin_role_privileges(id, name) VALUES (6, 'USER_PRIVILEGE'); -- 사용자 관리 권한
INSERT INTO admin_role_privileges(id, name) VALUES (7, 'USER_READ_PRIVILEGE'); -- 사용자 관리 리스트 권한
INSERT INTO admin_role_privileges(id, name) VALUES (8, 'USER_DETAIL_PRIVILEGE'); -- 사용자 관리 상세 권한
INSERT INTO admin_role_privileges(id, name) VALUES (9, 'USER_DELETE_PRIVILEGE'); -- 사용자 관리 삭제 권한
INSERT INTO admin_role_privileges(id, name) VALUES (10, 'USER_WRITE_PRIVILEGE'); -- 사용자 관리 추가 권한

INSERT INTO admin_role_privilege_join (role_id, privilege_id) VALUES (2, 1);
INSERT INTO admin_role_privilege_join (role_id, privilege_id) VALUES (2, 6);

INSERT INTO admin_role_privilege_join (role_id, privilege_id) VALUES (3, 2);
INSERT INTO admin_role_privilege_join (role_id, privilege_id) VALUES (3, 7);

-- INSERT INTO admin_role_privileges VALUES (3, 'RESERVE_PRIVILEGE'); -- 적립금 관리 권한
-- INSERT INTO admin_role_privileges VALUES (4, 'DEPOSIT_PRIVILEGE'); -- 예치금 관리 권한
-- INSERT INTO admin_role_privileges VALUES (5, 'PAYMENT_PRIVILEGE'); -- 결제 관리 권한
-- INSERT INTO admin_role_privileges VALUES (5, 'PAYMENT_DETAIL_PRIVILEGE'); -- 결제 관리 상세 정보 보기 권한
--
-- INSERT INTO admin_role_privileges VALUES (6, 'SETTLE_PRIVILEGE'); -- 결제 관리 권한
-- INSERT INTO admin_role_privileges VALUES (7, 'MARKETING_PRIVILEGE'); -- 결제 관리 권한
-- INSERT INTO admin_role_privileges VALUES (8, 'OPERATION_PRIVILEGE'); -- 운영 관리 권한
-- INSERT INTO admin_role_privileges VALUES (9, 'DASHBOARD_PRIVILEGE'); -- 결제 관리 권한


# CREATE TABLE oauth_client_details (
#                                       client_id VARCHAR(256) PRIMARY KEY,
#                                       resource_ids VARCHAR(256),
#                                       client_secret VARCHAR(256),
#                                       scope VARCHAR(256),
#                                       authorized_grant_types VARCHAR(256),
#                                       web_server_redirect_uri VARCHAR(256),
#                                       authorities VARCHAR(256),
#                                       access_token_validity INTEGER,
#                                       refresh_token_validity INTEGER,
#                                       additional_information VARCHAR(4096),
#                                       autoapprove VARCHAR(256)
# )
#     ENGINE = InnoDb DEFAULT CHARSET=utf8;
#
CREATE TABLE `cheese`.`users` (
                                  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
                                  `name` VARCHAR(128) NOT NULL DEFAULT '' COMMENT '이름',
                                  `cp` VARCHAR(20) NOT NULL COMMENT '휴대폰 번호',
                                  `password` VARCHAR(128) NULL DEFAULT NULL COMMENT '패스워드',
                                  `sex` CHAR(1) NULL DEFAULT 'M' COMMENT '성별',
                                  `cheese_money_total` INT(10) UNSIGNED NULL DEFAULT '0' COMMENT '치즈 머니 토탈 금액',
                                  `birth_year` VARCHAR(4) NULL COMMENT '년',
                                  `birth_month` VARCHAR(2) NULL COMMENT '월',
                                  `birth_day` VARCHAR(2) NULL COMMENT '일',
                                  `email` VARCHAR(45) NULL DEFAULT '' COMMENT '이메일',
                                  `deleted_reason` VARCHAR(200) NULL DEFAULT NULL COMMENT '탈퇴 사유',
                                  `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성 일자',
                                  `updated_at` TIMESTAMP NULL DEFAULT NULL,
                                  `deleted_at` TIMESTAMP NULL COMMENT '탈퇴 일자',
                                  `last_login` TIMESTAMP NULL DEFAULT NULL COMMENT '마지막 로그인 일자',
                                  `is_active` TINYINT(1) unsigned NOT NULL DEFAULT 1 COMMENT '활성 여부',
                                  `is_account` TINYINT(1) unsigned NOT NULL DEFAULT 0 COMMENT '계좌 여부',
                                  `is_receive_push` TINYINT(1) unsigned NOT NULL DEFAULT 1 COMMENT '푸시 알림 여부',
                                  `is_receive_cheese` TINYINT(1) unsigned NOT NULL DEFAULT 1 COMMENT '적립금 알림 여부 ',
                                  `is_receive_pay` TINYINT(1) unsigned NOT NULL DEFAULT 1 COMMENT '결제 알림 여부',
                                  `is_receive_promotion` TINYINT(1) unsigned NOT NULL DEFAULT 1 COMMENT '프로모션/이벤트 알림 여부',
                                  PRIMARY KEY (`id`))
    COMMENT = '사용자 목록';
#
# CREATE TABLE IF NOT EXISTS authorities (
#     username VARCHAR(256) NOT NULL,
#     authority VARCHAR(256) NOT NULL,
#     PRIMARY KEY(username, authority)
#     )
#     ENGINE = InnoDb DEFAULT CHARSET=utf8;

create table oauth_client_details (
                                      client_id varchar(256) primary key,
                                      resource_ids varchar(256),
                                      client_secret varchar(256),
                                      scope varchar(256),
                                      authorized_grant_types varchar(256),
                                      web_server_redirect_uri varchar(256),
                                      authorities varchar(256),
                                      access_token_validity integer,
                                      refresh_token_validity integer,
                                      additional_information varchar(4096),
                                      autoapprove varchar(256)
) ENGINE = InnoDb DEFAULT CHARSET=utf8;

create table oauth_access_token (
                                    token_id VARCHAR(256),
                                    token    BLOB,
                                    authentication_id VARCHAR(256) PRIMARY KEY,
                                    user_name VARCHAR(256),
                                    client_id VARCHAR(256),
                                    authentication    varbinary(5000),
                                    refresh_token VARCHAR(256)
) ENGINE = InnoDb DEFAULT CHARSET=utf8;

create table oauth_refresh_token (
                                     token_id VARCHAR(256),
                                     token BLOB,
                                     authentication BLOB
) ENGINE = InnoDb DEFAULT CHARSET=utf8;


# password secret
INSERT INTO oauth_client_details
   (client_id, client_secret, scope, authorized_grant_types,
   web_server_redirect_uri, authorities, access_token_validity,
   refresh_token_validity, additional_information, autoapprove)
VALUES ('testclient', '$2a$10$4R/rWflN2RDiGZ3TvGplN.Z7fpILYAop9kJKqk7FgZnHCGhwFSGYS', 'all', 'password,refresh_token', null, null, 300, 36000, null, true);

CREATE TABLE `cheese`.`categories` (
                                       `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
                                       `name` VARCHAR(32) NOT NULL UNIQUE  COMMENT '카테고리명',
                                       `sort` TINYINT(1) UNSIGNED NOT NULL DEFAULT '1' COMMENT '정렬 순서',
                                       `is_use` TINYINT(1) UNSIGNED NOT NULL DEFAULT '1' COMMENT '사용 여부(1: 사용, 0: 미사용)',
                                       `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                       `updated_at` TIMESTAMP NULL DEFAULT NULL,
                                       `deleted_at` timestamp NULL DEFAULT NULL,
                                       PRIMARY KEY (`id`)
) COMMENT = '카테고리'
ENGINE = InnoDb DEFAULT CHARSET=utf8;

INSERT INTO categories (name) VALUES ('편의점/마트');
INSERT INTO categories (name) VALUES ('음식점/요식업');
INSERT INTO categories (name) VALUES ('카페/디저트');
INSERT INTO categories (name) VALUES ('헤어/네일');
INSERT INTO categories (name) VALUES ('의류/악세서리/렌즈');
INSERT INTO categories (name) VALUES ('여행/숙박');
INSERT INTO categories (name) VALUES ('오락시설');
INSERT INTO categories (name) VALUES ('병원/약국');
INSERT INTO categories (name) VALUES ('애견/애견용품');
INSERT INTO categories (name) VALUES ('학원/아카데미');
INSERT INTO categories (name) VALUES ('헬스/요가');
INSERT INTO categories (name) VALUES ('주류/주점');
INSERT INTO categories (name) VALUES ('식자재/유통');
INSERT INTO categories (name) VALUES ('가구/인테리어');
INSERT INTO categories (name) VALUES ('가전/통신');
INSERT INTO categories (name) VALUES ('부동산/임대');
INSERT INTO categories (name) VALUES ('기업/기관');
INSERT INTO categories (name) VALUES ('기타');

CREATE TABLE `cheese`.`store_groups` (
                                       `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
                                       `name` VARCHAR(32) NOT NULL UNIQUE  COMMENT '그룹명',
                                       `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                       `updated_at` TIMESTAMP NULL DEFAULT NULL,
                                       PRIMARY KEY (`id`)
) COMMENT = '스토어 그룹'
    ENGINE = InnoDb DEFAULT CHARSET=utf8;

INSERT INTO `cheese`.`store_groups`(name) VALUES ('기용스토어그룹');
INSERT INTO `cheese`.`store_groups`(name) VALUES ('에디스토어그룹');



CREATE TABLE `cheese`.`stores` (
                                         `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
                                         `store_number` VARCHAR(20) NULL DEFAULT NULL COMMENT '스토어 고유 아이디',
                                         `store_group_id` INT UNSIGNED NOT NULL COMMENT '스토어 그룹 아이디',
                                         `category_id` INT UNSIGNED NOT NULL COMMENT '업종 카테고리 아이디',
                                         `name` VARCHAR(50) NOT NULL COMMENT '가맹점명',
                                         `email` VARCHAR(50) DEFAULT NULL COMMENT '이메일',
                                         `business_license_number` VARCHAR(300) NOT NULL COMMENT '사업자 등록번호',
                                         `ceo_name` VARCHAR(20) NOT NULL COMMENT '대표자명',
                                         `ceo_phone` VARCHAR(20) NOT NULL COMMENT '대표자 번호',
                                         `fax` VARCHAR(20) DEFAULT  NULL COMMENT '팩스 번호',
                                         `address` VARCHAR(256) DEFAULT NULL COMMENT '주소',
                                         `address_extra` VARCHAR(256) DEFAULT NULL COMMENT '상세주소',
                                         `homepage_url` VARCHAR(256) DEFAULT NULL COMMENT '홈페이지 주소',
                                         `is_approve` TINYINT(1) NOT NULL DEFAULT '0' COMMENT '승인 여부',
                                         `is_active` TINYINT(1) NOT NULL DEFAULT '1' COMMENT '활성 여부',
                                         `is_holiday` TINYINT(1) NOT NULL DEFAULT '0',
                                         `is_contract_bond` TINYINT(1) unsigned NOT NULL DEFAULT 0 COMMENT '계약 이행 보증서 제출 여부',
                                         `is_blacklist` TINYINT(1) unsigned NOT NULL DEFAULT 0 COMMENT '블랙 리스트 여부 (0: 정상, 1: 블랙)',
                                         `bank_name` VARCHAR(32) NULL DEFAULT NULL COMMENT '은행명',
                                         `bank_code` VARCHAR(32) NULL DEFAULT NULL COMMENT '은행코드',
                                         `bank_account` VARCHAR(32) NULL DEFAULT NULL COMMENT '은행계좌',
                                         `bank_account_name` VARCHAR(100) NULL DEFAULT NULL COMMENT '계좌명',
                                         `lat` VARCHAR(20) NULL DEFAULT NULL COMMENT '위도',
                                         `lng` VARCHAR(20) NULL DEFAULT NULL COMMENT '경도',
                                         `work_start` TIME NULL DEFAULT NULL COMMENT '매장운영시작시간',
                                         `work_end` TIME NULL DEFAULT NULL COMMENT '매장운영종료시간',
                                         `discount_items_reward` DECIMAL(5,2) NULL DEFAULT '0.00' COMMENT '적립율',
                                         `deleted_reason` VARCHAR(200) NULL DEFAULT NULL COMMENT '삭제 이유',
                                         `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성 일자',
                                         `updated_at` TIMESTAMP NULL DEFAULT NULL,
                                         `deleted_at` TIMESTAMP NULL DEFAULT NULL COMMENT '삭제일자',
                                         PRIMARY KEY (`id`)
) COMMENT = '스토어명'
    ENGINE = InnoDb DEFAULT CHARSET=utf8;


# INSERT INTO `cheese`.`stores` (store_number, store_group_id, category_id, name, ceo_name, ceo_phone, business_license_number)
# VALUES('AL1', 1, 1, '기용스토어', '에디', '01092069357', '1123-21132-12312');
#
#
# INSERT INTO `cheese`.`stores` (store_number, store_group_id, category_id, name, ceo_name, ceo_phone, business_license_number)
# VALUES('AL2', 1, 1, '기용스토어2', '에디', '01092069357', '1123-21132-12412');
#
# INSERT INTO `cheese`.`stores` (store_number, store_group_id, category_id, name, ceo_name, ceo_phone, business_license_number)
# VALUES('AL3', 1, 1, '기용스토어3', '에디', '01092069357', '1123-21132-12512');
#
#
# INSERT INTO `cheese`.`stores` (store_number, store_group_id, category_id, name, ceo_name, ceo_phone, business_license_number)
# VALUES('AL4', 1, 1, '기용스토어4', '에디', '01092069357', '1323-21132-12419');
#
# INSERT INTO `cheese`.`stores` (store_number, store_group_id, category_id, name, ceo_name, ceo_phone, business_license_number)
# VALUES('AL5', 1, 1, '기용스토어5', '에디', '01092069357', '1423-21132-12510');

CREATE TABLE `cheese`.`holidays` (
                                           `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
                                           `store_id` INT UNSIGNED NOT NULL COMMENT '스토어 아이디',
                                           `type` CHAR(1) NOT NULL DEFAULT 'M' COMMENT '수동입력 0\\n스케줄러입력 1',
                                           `date` DATE NOT NULL COMMENT 'Y-m-d',
                                           `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성일',
                                           PRIMARY KEY (`id`)
) COMMENT = '스토어 지정 휴일 테이블 ';

CREATE TABLE `cheese`.`store_images` (
                                         `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
                                         `store_id` INT UNSIGNED NOT NULL,
                                         `type` VARCHAR(16) NOT NULL COMMENT '이미지 타입 (사업자 등록증, 계약 이행 보증서 등)\\ncodes.group = \'SHOP_IMAGE\'',
                                         `original` VARCHAR(1024) NOT NULL COMMENT 'host 제외한 URL',
                                         `sort` TINYINT(4) NULL DEFAULT NULL COMMENT '순서',
                                         `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                         `updated_at` TIMESTAMP NULL DEFAULT NULL,
                                         `deleted_at` TIMESTAMP NULL DEFAULT NULL,
                                         PRIMARY KEY (`id`)
) COMMENT = '상점관련 이미지 (사업자등록증, 계약 이행 보증서,  상점 이미지 등)'
    ENGINE = InnoDb DEFAULT CHARSET=utf8;


CREATE TABLE `cheese`.`items` (
                                  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
                                  `item_number` VARCHAR(20) NULL DEFAULT NULL,
                                  `store_id` INT UNSIGNED NOT NULL COMMENT '스토어 아이디',
                                  `name` VARCHAR(100) NOT NULL COMMENT '상품명',
                                  `description` VARCHAR(200) NULL DEFAULT NULL COMMENT '상품 설명',
                                  `price` INT UNSIGNED NOT NULL DEFAULT '0' COMMENT '가격',
                                  `cook_time` TINYINT(3) UNSIGNED NULL DEFAULT '0' COMMENT '0 - 180분',
                                  `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '????',
                                  `updated_at` TIMESTAMP NULL DEFAULT NULL,
                                  `deleted_at` TIMESTAMP NULL DEFAULT NULL COMMENT '삭제일',
                                  PRIMARY KEY (`id`)
) COMMENT = '상품'
    ENGINE = InnoDb DEFAULT CHARSET=utf8;

CREATE TABLE `cheese`.`item_images` (
                                        `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
                                        `item_id` INT UNSIGNED NOT NULL,
                                        `type` VARCHAR(16) NOT NULL COMMENT '이미지 타입',
                                        `original` VARCHAR(1024) NOT NULL COMMENT 'URL',
                                        `sort` TINYINT(1) NULL DEFAULT NULL COMMENT '순서',
                                        `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                        `updated_at` TIMESTAMP NULL DEFAULT NULL,
                                        `deleted_at` TIMESTAMP NULL DEFAULT NULL,
                                        PRIMARY KEY (`id`)
) COMMENT = '상품관련 이미지 (목록 썸네일, 상단, 중단, 하단 등)'
    ENGINE = InnoDb DEFAULT CHARSET=utf8;

CREATE TABLE `cheese`.`store_users` (
                                        `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
                                        `name` VARCHAR(50) NOT NULL COMMENT '이름',
                                        `email` VARCHAR(50) NULL COMMENT '이메일',
                                        `cp` VARCHAR(20) NOT NULL COMMENT '휴대폰번호',
                                        `sex` CHAR(1) NULL DEFAULT 'M' COMMENT '성별',
                                        `birth_year` VARCHAR(4) NULL COMMENT '년',
                                        `birth_month` VARCHAR(2) NULL COMMENT '월',
                                        `birth_day` VARCHAR(2) NULL COMMENT '일',
                                        `is_staff` TINYINT(1) unsigned NOT NULL DEFAULT 0 COMMENT '직원 여부 (1: 직원, 0: 사장)',
                                        `is_approve` TINYINT(1) unsigned NOT NULL DEFAULT 0 COMMENT '승인 여부 (1: 승인, 0: 대기)',
                                        `is_receive_push` TINYINT(1) unsigned NOT NULL DEFAULT 1 COMMENT '푸시 알림 여부',
                                        `is_receive_cheese` TINYINT(1) unsigned NOT NULL DEFAULT 1 COMMENT '적립금 알림 여부 ',
                                        `is_receive_pay` TINYINT(1) unsigned NOT NULL DEFAULT 1 COMMENT '결제 알림 여부',
                                        `is_receive_promotion` TINYINT(1) unsigned NOT NULL DEFAULT 1 COMMENT '프로모션/이벤트 알림 여부',
                                        `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성 일자',
                                        `updated_at` TIMESTAMP NULL DEFAULT NULL,
                                        `deleted_reason` VARCHAR(200) NULL DEFAULT NULL COMMENT '탈퇴 사유',
                                        `deleted_at` TIMESTAMP NULL COMMENT '탈퇴 일자',
                                        PRIMARY KEY (`id`))
    COMMENT = '가맹점주 목록';