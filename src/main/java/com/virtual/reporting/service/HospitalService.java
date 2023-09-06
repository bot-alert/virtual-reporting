package com.virtual.reporting.service;

import com.virtual.reporting.dto.ApiResponseDTO;
import com.virtual.reporting.dto.response.HospitalDTO;
import com.virtual.reporting.dto.response.UserDTO;

import java.util.List;

public interface HospitalService extends GenericService<HospitalDTO> {
    ApiResponseDTO<List<UserDTO>> findAllByHospitalId(long id);
}
