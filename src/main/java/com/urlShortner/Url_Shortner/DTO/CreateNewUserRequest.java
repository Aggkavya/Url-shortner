package com.urlShortner.Url_Shortner.DTO;

import lombok.Data;

@Data
public class CreateNewUserRequest {
    private String userName;
    private String password;
}
