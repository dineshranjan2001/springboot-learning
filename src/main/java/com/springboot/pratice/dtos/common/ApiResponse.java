package com.springboot.pratice.dtos.common;

import java.time.Instant;

public record ApiResponse<T>(
        boolean success,
        int Status,
        String message,
        T data,
        Instant timestamp
) {

    public static <T> ApiResponse<T> success(int status,String message, T data){
        return new ApiResponse<>(true,status,message,data,Instant.now());
    }
}
