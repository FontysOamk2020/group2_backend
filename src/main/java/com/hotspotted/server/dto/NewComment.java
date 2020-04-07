package com.hotspotted.server.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class NewComment {
    @NotBlank
    String text;
}
