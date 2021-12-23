package com.cheese.admin.controller.v1;

import com.cheese.admin.exception.CustomException;
import com.cheese.admin.model.response.Response;
import com.cheese.core.error.ErrorCode;
import com.cheese.domain.domain.category.Category;
import com.cheese.domain.domain.category.CategoryRepository;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RequestMapping("/v1/categories")
@RestController
public class CategoryController {

    @Autowired
    CategoryRepository categoryRepository;

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_ADMIN_CUSTOM')")
    @GetMapping
    public ResponseEntity<Response<List<Category>>> findAllCategory() {

        List<Category> categories = categoryRepository.findAll(Sort.by(Sort.Direction.DESC, "sort"));

        if (categories.isEmpty()) {
            throw new CustomException(ErrorCode.CATEGORY_LIST_NOT_FOUND);
        }
        Response response = Response.builder()
                .code(ErrorCode.SUCCESS.getCode())
                .data(categories)
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_ADMIN_CUSTOM')")
    @GetMapping("/{id}")
    public ResponseEntity<Response<Category>> findCategoryById(
            @ApiParam(value = "카테고리 아이디") @PathVariable Long id){

        Optional<Category> category = categoryRepository.findById(id);

        if (!category.isPresent()) {
            throw new CustomException(ErrorCode.CATEGORY_LIST_NOT_FOUND);
        }
        Response response = Response.builder()
                .code(ErrorCode.SUCCESS.getCode())
                .data(category.get())
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
