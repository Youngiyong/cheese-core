package com.cheese.admin.model.response;

import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
public class Response<T> {

    private String code;
    private LocalDateTime timestamp = LocalDateTime.now();
    private T data;

    @Builder
    public Response(String code, T data){
        this.code = code;
        this.data = data;
    }


}
