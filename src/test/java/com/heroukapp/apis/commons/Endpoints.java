package com.heroukapp.apis.commons;

import java.util.function.Supplier;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
@RequiredArgsConstructor
public class Endpoints {
    @Value("${baseURI}")
    private String baseURI;

    @Value("${createTokenEndpoint}")
    private String createTokenEndpoint;
    @Value("${bookingEndpoint}")
    private String bookingEndpoint;

    public Supplier<String> createTokenURI = () -> baseURI + createTokenEndpoint;
    public Supplier<String> bookingURI = () -> baseURI + bookingEndpoint;

}
