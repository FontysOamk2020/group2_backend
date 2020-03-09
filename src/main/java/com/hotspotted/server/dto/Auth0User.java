package com.hotspotted.server.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class Auth0User {
    @NotBlank(message = "Please provide: sub")
    String sub;

    @NotBlank(message = "Please provide: nickname")
    String nickname;

    @NotBlank(message = "Please provide: name")
    String name;

    @NotBlank(message = "Please provide: picture")
    String picture;

    @NotBlank(message = "Please provide: email")
    String email;
}