package com.virtual.reporting.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.virtual.reporting.dto.ApiResponseDTO;
import com.virtual.reporting.dto.helper.MyPrinciple;
import com.virtual.reporting.dto.response.UserDTO;
import com.virtual.reporting.entity.User;
import com.virtual.reporting.repository.UserRepository;
import com.virtual.reporting.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.virtual.reporting.constant.ResponseConstant.*;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ObjectMapper objectMapper;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new MyPrinciple(userRepository.findByEmail(username).orElseThrow(() ->
                new AuthenticationServiceException("Unauthorized")));
    }

    @Override
    public ApiResponseDTO<List<UserDTO>> findAll() {
        List<UserDTO> userDTOList = userRepository.findAll()
                .stream()
                .map(user -> objectMapper.convertValue(user, UserDTO.class))
                .toList();
        return ApiResponseDTO.successBuilder(userDTOList)
                .message(DATA_FETCHED_SUCCESS)
                .build();
    }

    @Override
    public ApiResponseDTO<UserDTO> findById(long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException(CANNOT_FIND_RECORD));
        return ApiResponseDTO.successBuilder(objectMapper.convertValue(user, UserDTO.class))
                .message(DATA_FETCHED_SUCCESS)
                .build();
    }

    @Override
    public ApiResponseDTO<String> save(UserDTO dto) {
        User user = objectMapper.convertValue(dto, User.class);
        userRepository.save(user);
        return ApiResponseDTO.<String>successBuilderNoData()
                .message(DATA_SAVED)
                .build();
    }

    @Override
    public ApiResponseDTO<String> delete(long id) {
        userRepository.findById(id).ifPresent(userRepository::delete);
        return ApiResponseDTO.<String>successBuilderNoData()
                .message(DATA_DELETED)
                .build();
    }

}
