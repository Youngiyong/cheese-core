package com.cheese.admin.model.response;

import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class Response<T> {

    private String code;
    private LocalDateTime timestamp = LocalDateTime.now();
    private T data;

}
