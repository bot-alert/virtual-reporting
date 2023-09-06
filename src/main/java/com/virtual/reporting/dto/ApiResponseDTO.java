package com.virtual.reporting.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponseDTO<T> {
    T data;
    List<String> message;
    String type;

    ApiResponseDTO(T data, List<String> message, String type) {
        this.data = data;
        this.message = message;
        this.type = type;
    }

    public static <T> ApiResponseDTOBuilder<T> successBuilder(T data) {
        return new ApiResponseDTOBuilder<>(data, "success");
    }

    public static <T> ApiResponseDTOBuilder<T> successBuilderNoData() {
        return new ApiResponseDTOBuilder<>("success");
    }

    public static <T> ApiResponseDTOBuilder<T> failureBuilder() {
        return new ApiResponseDTOBuilder<>("failure");
    }

    public static class ApiResponseDTOBuilder<T> {
        private T data;
        private List<String> message;
        private final String type;

        public ApiResponseDTOBuilder(T data, String type) {
            this.data = data;
            this.type = type;
        }

        public ApiResponseDTOBuilder(String type) {
            this.type = type;
        }

        public ApiResponseDTOBuilder<T> data(T data) {
            this.data = data;
            return this;
        }

        public ApiResponseDTOBuilder<T> message(List<String> message) {
            this.message = message;
            return this;
        }
        public ApiResponseDTOBuilder<T> message(String message) {
            this.message = List.of(message);
            return this;
        }
        public ApiResponseDTO<T> build() {
            return new ApiResponseDTO<>(this.data, this.message, this.type);
        }

        public String toString() {
            return "ApiResponseDTO.ApiResponseDTOBuilder(data=" + this.data + ", message=" + this.message + ", type=" + this.type + ")";
        }
    }
}
