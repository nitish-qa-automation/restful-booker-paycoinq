package com.heroukapp.apis.expectations;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum GatewayErrorExpectations implements ErrorExpectations {

    FORBIDDEN(
        403,
        "",
        "");

    private final int errorCode;
    private final String titleMessage;
    private final String detailMessage;


}