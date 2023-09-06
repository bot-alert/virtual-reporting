package com.virtual.reporting.controller.rest;

import com.virtual.reporting.dto.ApiResponseDTO;
import com.virtual.reporting.dto.response.HospitalDTO;
import com.virtual.reporting.dto.response.UserDTO;
import com.virtual.reporting.service.HospitalService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/hospital")
@RequiredArgsConstructor
public class HospitalController {
    private final HospitalService hospitalService;

    @GetMapping
    public ApiResponseDTO<List<HospitalDTO>> findAllHospitals() {
        return hospitalService.findAll();
    }

    @GetMapping("/{id}")
    public ApiResponseDTO<HospitalDTO> findHospitalById(@PathVariable long id) {
        return hospitalService.findById(id);
    }

    @PostMapping
    public ApiResponseDTO<String> saveHospital(@RequestBody @Valid HospitalDTO hospitalDTO) {
        return hospitalService.save(hospitalDTO);
    }

    @DeleteMapping("/{id}")
    public ApiResponseDTO<String> deleteHospital(@PathVariable long id) {
        return hospitalService.delete(id);
    }

    @GetMapping("/{id}/users")
    public ApiResponseDTO<List<UserDTO>> findUsersByHospitalId(@PathVariable long id) {
        return hospitalService.findAllByHospitalId(id);
    }
}
