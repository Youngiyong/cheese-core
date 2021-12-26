package com.cheese.core.model.response;

import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class CheeseResponse<T> {

    private String code;
    private LocalDateTime timestamp = LocalDateTime.now();
    private T data;

    @Builder
    public CheeseResponse(String code, T data){
        this.code = code;
        this.data = data;
    }


}
