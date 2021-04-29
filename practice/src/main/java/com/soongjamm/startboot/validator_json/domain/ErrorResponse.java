package com.soongjamm.startboot.validator_json.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ErrorResponse {
    private String message;
    private Object rejectedValue;
    private Object field;
}
