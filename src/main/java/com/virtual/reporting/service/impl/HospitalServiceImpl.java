package com.virtual.reporting.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.virtual.reporting.dto.ApiResponseDTO;
import com.virtual.reporting.dto.response.HospitalDTO;
import com.virtual.reporting.dto.response.UserDTO;
import com.virtual.reporting.entity.Hospital;
import com.virtual.reporting.repository.HospitalRepository;
import com.virtual.reporting.service.HospitalService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.virtual.reporting.constant.ResponseConstant.*;

@Service
@RequiredArgsConstructor
public class HospitalServiceImpl implements HospitalService {
    private final HospitalRepository hospitalRepository;
    private final ObjectMapper objectMapper;

    @Override
    public ApiResponseDTO<List<HospitalDTO>> findAll() {
        List<HospitalDTO> userDTOList = hospitalRepository.findAll()
                .stream()
                .map(hospital -> objectMapper.convertValue(hospital, HospitalDTO.class))
                .toList();
        return ApiResponseDTO.successBuilder(userDTOList)
                .message(DATA_FETCHED_SUCCESS)
                .build();
    }

    @Override
    public ApiResponseDTO<HospitalDTO> findById(long id) {
        Hospital hospital = hospitalRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException(CANNOT_FIND_RECORD));
        return ApiResponseDTO.successBuilder(objectMapper.convertValue(hospital, HospitalDTO.class))
                .message(DATA_FETCHED_SUCCESS)
                .build();
    }

    @Override
    public ApiResponseDTO<String> save(HospitalDTO dto) {
        Hospital hospital = objectMapper.convertValue(dto, Hospital.class);
        hospitalRepository.save(hospital);
        return ApiResponseDTO.<String>successBuilderNoData()
                .message(DATA_SAVED)
                .build();
    }

    @Override
    public ApiResponseDTO<String> delete(long id) {
        hospitalRepository.findById(id).ifPresent(hospitalRepository::delete);
        return ApiResponseDTO.<String>successBuilderNoData()
                .message(DATA_DELETED)
                .build();
    }

    @Override
    public ApiResponseDTO<List<UserDTO>> findAllByHospitalId(long id) {
        List<UserDTO> userDTOList = hospitalRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException(CANNOT_FIND_RECORD))
                .getUsers().stream()
                .map(user -> objectMapper.convertValue(user, UserDTO.class))
                .toList();
        return ApiResponseDTO.successBuilder(userDTOList)
                .message(DATA_FETCHED_SUCCESS)
                .build();
    }
}
