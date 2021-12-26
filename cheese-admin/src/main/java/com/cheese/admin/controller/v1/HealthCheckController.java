package com.cheese.admin.controller.v1;

import com.cheese.core.exception.CheeseCode;
import com.cheese.admin.exception.InvalidParameterException;
import com.cheese.admin.model.request.Member;
import com.cheese.core.exception.CustomException;
import com.cheese.domain.dto.request.LoginRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@CrossOrigin("*")
@RequestMapping("/health")
@RestController
public class HealthCheckController {

    @Operation(summary = "health check", description = "health api example")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK !!"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST !!"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND !!"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR !!")
    })
    @GetMapping(path = "/check")
    public ResponseEntity<String> health(@Parameter(description = "이름", required = true, example = "Park") @RequestParam String name) {
        return ResponseEntity.ok("hello " + name);
    }


    @PostMapping("/member")
    public String memberException(@Valid @RequestBody Member dto, BindingResult result) {
        System.out.println(dto);
        if (result.hasErrors()) {
            throw new InvalidParameterException(result);
        }

        return "page/home";
    }

    @GetMapping("/exception")
    public String exceptionTest(String code) {
        switch (code) {
            case "1":
                System.out.println("hi");
//                break;
                throw new CustomException(CheeseCode.EMAIL_DUPLICATION);
            case "3":
                int a = 3 / 0;
                break;
        }
        return "page/home";
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'STORE_PRIVILEGE', 'STORE_READ_PRIVILEGE')")
    @GetMapping("/stores")
    public String findAllStores() {
        return "findAllStores";
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'STORE_PRIVILEGE', 'STORE_DETAIL_PRIVILEGE')")
    @GetMapping("/stores/{id}")
    public String getStoreById(@PathVariable String id) {
        return "getStoreById";
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'STORE_PRIVILEGE', 'STORE_DELETE_PRIVILEGE')")
    @DeleteMapping("/stores/{id}")
    public String deleteStoreById(@PathVariable String id) {
        return "deleteStoreById";
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'STORE_PRIVILEGE', 'STORE_WRITE_PRIVILEGE')")
    @PostMapping("/stores/{id}")
    public String postStore(@RequestBody LoginRequest payload) {
        return "postStore";
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'USER_PRIVILEGE', 'USER_READ_PRIVILEGE')")
    @GetMapping("/users")
    public String findAllUsers() {
        return "findAllUsers";
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'USER_PRIVILEGE', 'USER_DETAIL_PRIVILEGE')")
    @GetMapping("/users/{id}")
    public String getUserById(@PathVariable String id) {
        return "getUserById";
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'USER_PRIVILEGE', 'USER_DELETE_PRIVILEGE')")
    @DeleteMapping("/users/{id}")
    public String deleteUserById(@PathVariable String id) {
        return "deleteUserById";
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'USER_PRIVILEGE', 'USER_WRITE_PRIVILEGE')")
    @PostMapping("/users/{id}")
    public String postUsers(@RequestBody LoginRequest payload) {
        return "postUsers";
    }

}
