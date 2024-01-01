package dev.careeropz.portal.backend.cvmanager.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ResponseEnum {
    SUCCESS("SUCCESS"),
    FAILURE("FAILURE");

    private final String value;
}
