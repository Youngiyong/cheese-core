package com.cheese.admin.controller.v1;

import com.cheese.admin.exception.CustomException;
import com.cheese.admin.model.response.PaginationResponse;
import com.cheese.admin.model.response.Response;
import com.cheese.admin.model.response.StoreDto;
import com.cheese.admin.model.response.UserDto;
import com.cheese.core.error.ErrorCode;
import com.cheese.domain.domain.store.Store;
import com.cheese.domain.domain.store.StoreRepository;
import com.cheese.domain.domain.storeUser.StoreUserRepository;
import com.cheese.domain.domain.users.User;
import com.cheese.domain.domain.users.UserRepository;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;

import static com.cheese.admin.helper.PaginationHelper.orderByConvert;

@CrossOrigin("*")
@RequestMapping("/v1/users")
@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_ADMIN_CUSTOM', 'STORE_DELETE_PRIVILEGE')")
    @GetMapping("/{id}")
    public ResponseEntity<Response<UserDto.UserResponse>> findStoreById(
            @ApiParam(value = "유저 시퀀스") @PathVariable Long id){

        Optional<User> user = userRepository.findById(id);

        if(!user.isPresent()){
            throw new CustomException(ErrorCode.NOT_FOUND_USER);
        }

        Response response = Response.builder()
                .code(ErrorCode.SUCCESS.getCode())
                .data(user.get()).build();

        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_ADMIN_CUSTOM', 'STORE_DELETE_PRIVILEGE')")
    @GetMapping
    public ResponseEntity<PaginationResponse<UserDto.UserListResponse>> findAllStores(
            @RequestParam(required = false) @ApiParam(value = "유저명") String name,
            @RequestParam(required = false) @ApiParam(value = "휴대폰번호") String cp,
            @RequestParam(defaultValue = "1") @ApiParam(value = "페이지 번호 디폴트:1") int page,
            @RequestParam(defaultValue = "10") @ApiParam(value = "페이지 뿌려지는 갯수 디폴트:10") int size,
            @RequestParam(defaultValue = "createdAt,desc") @ApiParam(value = "정렬 데이터 예)createdAt,desc ") String[] sort) {
        try {
            List<Sort.Order> orders = orderByConvert(sort);
            page = (page<1) ? 1 : page;
            Pageable pagingSort = PageRequest.of(page - 1, size, Sort.by(orders));

            Page<UserDto.UserListResponse> pageTuts;
            if (name == null && cp == null)
                pageTuts = userRepository.findAll(pagingSort).map(UserDto.UserListResponse::new);
            else if (name != null)
                pageTuts = userRepository.findByNameContaining(name, pagingSort).map(UserDto.UserListResponse::new);
            else
                pageTuts = userRepository.findByCpContaining(cp, pagingSort).map(UserDto.UserListResponse::new);

            PaginationResponse res = new PaginationResponse(pageTuts);

            return new ResponseEntity<PaginationResponse<UserDto.UserListResponse>>(res, HttpStatus.OK);

        } catch (Exception e) {
            throw new CustomException(ErrorCode.INTERNAL_SERVER_ERROR_USER_LIST);
        }
    }

//    @PostConstruct
//    public void initializing() {
//        for (int i = 0; i < 1000; i++) {
//            User storeUser = User.builder()
//                    .name("윤기돌"+String.valueOf(i))
//                    .cp("010888812"+String.valueOf(i))
//                    .email("eddy@amlabs.co.kr")
//                    .birthYear(String.valueOf(1900+i))
//                    .birthMonth("01")
//                    .birthDay("01")
//                    .build();
//            userRepository.save(storeUser);
//        }
//    }
}
