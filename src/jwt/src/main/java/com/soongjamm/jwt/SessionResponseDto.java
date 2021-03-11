package com.soongjamm.jwt;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SessionResponseDto {
    private String accessToken;
}
