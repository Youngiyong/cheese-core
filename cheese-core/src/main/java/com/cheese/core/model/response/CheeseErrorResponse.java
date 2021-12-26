package com.cheese.core.model.response;

import lombok.*;

@NoArgsConstructor
@Setter
@Getter
public class CheeseErrorResponse {
    private int status;
    private String code;
    private String message;

    @Builder
    public CheeseErrorResponse(int status, String code, String message){
        this.status = status;
        this.code = code;
        this.message = message;
    }
}
