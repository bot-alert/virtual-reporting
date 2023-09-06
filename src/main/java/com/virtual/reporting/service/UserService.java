package com.virtual.reporting.service;

import com.virtual.reporting.dto.response.UserDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends GenericService<UserDTO>, UserDetailsService {
}
