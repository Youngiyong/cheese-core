package com.cheese.admin.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class CustomErrorResponse {

    private LocalDateTime timestamp = LocalDateTime.now();

    private String code; // 예외를 세분화하기 위한 사용자 지정 코드,

    private int status; // HTTP 상태 값 저장 400, 404, 500 등..

    //@Valid의 Parameter 검증을 통과하지 못한 필드가 담긴다.
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("messages")
    private List<CustomFieldError> customFieldErrors;

    static public CustomErrorResponse builder() {
        return new CustomErrorResponse();
    }

    public CustomErrorResponse code(String code) {
        this.code = code;
        return this;
    }

    public CustomErrorResponse status(int status) {
        this.status = status;
        return this;
    }


    //BindingResult.getFieldErrors() 메소드를 통해 전달받은 fieldErrors
    public void setCustomFieldErrors(List<FieldError> fieldErrors) {

        customFieldErrors = new ArrayList<>();

        fieldErrors.forEach(error -> {
            customFieldErrors.add(new CustomFieldError(
                    error.getCodes()[0],
                    error.getRejectedValue(),
                    error.getDefaultMessage()
            ));
        });
    }

    public CustomErrorResponse errors(Errors errors) {
        setCustomFieldErrors(errors.getFieldErrors());
        return this;
    }


    //parameter 검증에 통과하지 못한 필드가 담긴 클래스이다.
    public static class CustomFieldError {

        private String field;
        private Object value;
        private String reason;

        public CustomFieldError(String field, Object value, String reason) {
            this.field = field;
            this.value = value;
            this.reason = reason;
        }

        public String getField() {
            return field;
        }

        public Object getValue() {
            return value;
        }

        public String getReason() {
            return reason;
        }
    }
}