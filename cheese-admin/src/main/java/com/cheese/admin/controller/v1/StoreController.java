package com.cheese.admin.controller.v1;

import com.cheese.core.model.response.CheeseResponse;
import com.cheese.core.exception.CheeseCode;
import com.cheese.core.exception.CustomException;
import com.cheese.admin.model.response.PaginationResponse;
import com.cheese.admin.model.response.StoreDto;
import com.cheese.domain.domain.store.Store;
import com.cheese.domain.domain.store.StoreRepository;
import com.cheese.domain.domain.storeUser.StoreUser;
import com.cheese.domain.domain.storeUser.StoreUserRepository;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;

import static com.cheese.admin.helper.PaginationHelper.orderByConvert;

@RequestMapping("/v1/stores")
@RestController
public class StoreController {

    @Autowired
    StoreRepository storeRepository;

    @Autowired
    StoreUserRepository storeUserRepository;

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_ADMIN_CUSTOM', 'STORE_DELETE_PRIVILEGE')")
    @GetMapping("/{id}")
    public ResponseEntity<CheeseResponse<StoreDto.StoreResponse>> findStoreById(
            @ApiParam(value = "가맹점 시퀀스") @PathVariable Long id){

        Optional<Store> store = storeRepository.findById(id);

        if(!store.isPresent()){
            throw new CustomException(CheeseCode.NOT_FOUND_STORE);
        }

        CheeseResponse response = CheeseResponse.builder()
                            .code(CheeseCode.SUCCESS.getCode())
                            .data(store.get()).build();

        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_ADMIN_CUSTOM', 'STORE_DELETE_PRIVILEGE')")
    @GetMapping
    public ResponseEntity<PaginationResponse<StoreDto.StoreListResponse>> findAllStores(
            @RequestParam(required = false) @ApiParam(value = "스토어명") String name,
            @RequestParam(required = false) @ApiParam(value = "대표자명") String ceoName,
            @RequestParam(defaultValue = "1") @ApiParam(value = "페이지 번호 디폴트:1") int page,
            @RequestParam(defaultValue = "10") @ApiParam(value = "페이지 뿌려지는 갯수 디폴트:10") int size,
            @RequestParam(defaultValue = "createdAt,desc") @ApiParam(value = "정렬 데이터 예)createdAt,desc ") String[] sort) {
        try {
            List<Order> orders = orderByConvert(sort);
            page = (page<1) ? 1 : page;
            Pageable pagingSort = PageRequest.of(page - 1, size, Sort.by(orders));

            Page<StoreDto.StoreListResponse> pageTuts;
            if (name == null && ceoName == null)
                pageTuts = storeRepository.findAll(pagingSort).map(StoreDto.StoreListResponse::new);
            else if (name != null)
                pageTuts = storeRepository.findByNameContaining(name, pagingSort).map(StoreDto.StoreListResponse::new);
            else
                pageTuts = storeRepository.findByCeoNameContaining(ceoName, pagingSort).map(StoreDto.StoreListResponse::new);

            PaginationResponse res = new PaginationResponse(pageTuts);

            return new ResponseEntity<PaginationResponse<StoreDto.StoreListResponse>>(res, HttpStatus.OK);

        } catch (Exception e) {
            throw new CustomException(CheeseCode.INTERNAL_SERVER_ERROR_STORE_LIST);
        }
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_ADMIN_CUSTOM', 'STORE_DELETE_PRIVILEGE')")
    @GetMapping("/users")
    public ResponseEntity<PaginationResponse<StoreDto.StoreUserListResponse>> findAllStoreUsers(
            @RequestParam(required = false) @ApiParam(value = "이름") String name,
            @RequestParam(required = false) @ApiParam(value = "이메일") String email,
            @RequestParam(defaultValue = "1") @ApiParam(value = "페이지 번호 디폴트:1") int page,
            @RequestParam(defaultValue = "10") @ApiParam(value = "페이지 뿌려지는 갯수 디폴트:10") int size,
            @RequestParam(defaultValue = "createdAt,desc") @ApiParam(value = "정렬 데이터 예)createdAt,desc ") String[] sort) {
        try {
            List<Order> orders = orderByConvert(sort);
            page = (page<1) ? 1 : page;

            Pageable pagingSort = PageRequest.of(page - 1, size, Sort.by(orders));

            Page<StoreDto.StoreUserListResponse> pageTuts;
            if (name == null && email == null)
                pageTuts = storeUserRepository.findAll(pagingSort).map(StoreDto.StoreUserListResponse::new);
            else if (name != null)
                pageTuts = storeUserRepository.findByNameContaining(name, pagingSort).map(StoreDto.StoreUserListResponse::new);
            else
                pageTuts = storeUserRepository.findByEmailContaining(email, pagingSort).map(StoreDto.StoreUserListResponse::new);

            PaginationResponse res = new PaginationResponse(pageTuts);

            return new ResponseEntity<PaginationResponse<StoreDto.StoreUserListResponse>>(res, HttpStatus.OK);

        } catch (Exception e) {
            throw new CustomException(CheeseCode.INTERNAL_SERVER_ERROR_STORE_USER_LIST);
        }
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_ADMIN_CUSTOM', 'STORE_DELETE_PRIVILEGE')")
    @GetMapping("/users/{id}")
    public ResponseEntity<CheeseResponse<StoreDto.StoreUserResponse>> findStoreUserById(
            @ApiParam(value = "가맹점 직원 시퀀스") @PathVariable Long id){

        Optional<StoreUser> storeUser = storeUserRepository.findById(id);

        if(!storeUser.isPresent()){
            throw new CustomException(CheeseCode.NOT_FOUND_STORE_USER);
        }

        CheeseResponse response = CheeseResponse.builder()
                                .code(CheeseCode.SUCCESS.getCode())
                                .data(storeUser.get())
                                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);

    }

//    @PostConstruct
//    public void initializing() {
//        for (int i = 0; i < 1000; i++) {
//            StoreUser storeUser = StoreUser.builder()
//                    .name("윤기용"+String.valueOf(i))
//                    .cp("010920601"+String.valueOf(i))
//                    .email("eddy@amlabs.co.kr")
//                    .birthYear(String.valueOf(1900+i))
//                    .birthMonth("01")
//                    .birthDay("01")
//                    .build();
//            storeUserRepository.save(storeUser);
//        }
//    }
//
//    @PostConstruct
//    public void initializing2() {
//        for (int i = 0; i < 500; i++) {
//            Store store = Store.builder()
//                    .storeNumber("AL"+ String.valueOf(i))
//                    .storeGroupId(1)
//                    .categoryId(1)
//                    .name("기용스토어" + String.valueOf(i))
//                    .ceoName("에디"+ String.valueOf(i))
//                    .ceoPhone("01092069357")
//                    .businessLicenseNumber("123-456-12121"+String.valueOf(i))
//                    .build();
//            storeRepository.save(store);
//        }
//    }
}
