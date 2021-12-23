package com.cheese.core.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class CheeseResponse<T> {

    private String code;
    private LocalDateTime timestamp = LocalDateTime.now();
    private T data;

}
