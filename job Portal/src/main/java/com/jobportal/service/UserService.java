package com.jobportal.service;

import com.jobportal.dto.UserDTO;
import com.jobportal.exception.JobPortalException;

public interface UserService {
    public UserDTO registerUser(UserDTO userDTO) throws JobPortalException;
}
