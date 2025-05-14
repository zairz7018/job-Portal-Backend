package com.jobportal.jwt;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class AuthentificationResponse {
    public AuthentificationResponse(String jwt){
        this.jwt = jwt;
    }
    private final String jwt;

}
