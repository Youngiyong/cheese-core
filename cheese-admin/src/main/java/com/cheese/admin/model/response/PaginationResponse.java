package com.cheese.admin.model.response;

import com.cheese.core.exception.CheeseCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class PaginationResponse<T> {

    private String code;
    private LocalDateTime timestamp = LocalDateTime.now();
    private List<T> data;

    private int size;
    private int currentPages;
    private int currentElements;
    private int totalPages;
    private Long totalElements;
    private Boolean last;
    private Boolean first;
    private Boolean empty;

    public PaginationResponse(){}

    public PaginationResponse(Page<T> page){
        this.code = CheeseCode.SUCCESS.getCode();
        this.data = page.getContent();
        this.size = page.getSize();
        this.currentPages = page.getNumber()+1;
        this.currentElements = page.getNumberOfElements();
        this.totalPages = page.getTotalPages();
        this.totalElements = page.getTotalElements();
        this.last = page.isLast();
        this.first = page.isFirst();
        this.empty = page.isEmpty();
    }

    public PaginationResponse(int status, String code, String message, List<T> data){
        this.code = code;
        this.data = data;
    }



}
