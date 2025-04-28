package com.jobportal.api;

import com.jobportal.dto.LoginDTO;
import com.jobportal.dto.ResponseDto;
import com.jobportal.dto.UserDTO;
import com.jobportal.exception.JobPortalException;
import com.jobportal.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@Validated
@RequestMapping("/users")
public class UserAPI {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserDTO>registerUser(@RequestBody @Valid UserDTO userDTO) throws JobPortalException {
        userDTO=userService.registerUser(userDTO);
        return new ResponseEntity<>(userDTO , HttpStatus.CREATED);
    }
    @PostMapping("/login")
    public ResponseEntity<UserDTO>loginUser(@RequestBody @Valid LoginDTO loginDTO) throws JobPortalException {

        return new ResponseEntity<>(userService.loginUser(loginDTO) , HttpStatus.OK);
    }
    @PostMapping("/sendOtp/{email}")
    public ResponseEntity<ResponseDto>sendOtp(@PathVariable String email ) throws Exception {
        userService.sendOtp(email);
        return new ResponseEntity<>(new ResponseDto("OTP sent Successfully ."), HttpStatus.OK);
    }
}
