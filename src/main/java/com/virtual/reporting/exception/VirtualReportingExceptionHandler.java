package com.virtual.reporting.exception;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
@RequiredArgsConstructor
public class VirtualReportingExceptionHandler {
//    @ExceptionHandler({JwtValidationException.class})
//    public ApiResponseDTO<Object> jwtValidationHandler(JwtValidationException jwtValidationException) {
//        return ApiResponseDTO.failureBuilder()
//                .message(jwtValidationException.getErrors().stream().map(OAuth2Error::toString).toList())
//                .build();
//    }
}
