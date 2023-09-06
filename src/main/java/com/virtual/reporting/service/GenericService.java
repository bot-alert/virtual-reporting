package com.virtual.reporting.service;

import com.virtual.reporting.dto.ApiResponseDTO;

import java.util.List;

public interface GenericService<T> {
    ApiResponseDTO<List<T>> findAll();

    ApiResponseDTO<T> findById(long id);

    ApiResponseDTO<String> save(T dto);

    ApiResponseDTO<String> delete(long id);
}
