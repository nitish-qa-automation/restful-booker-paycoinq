package com.heroukapp.apis.utils;

import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class URIBuilder {

    public String buildURI(String endpoint, MultiValueMap<String, String> queryParams) {
        UriComponents uriComponents = UriComponentsBuilder.newInstance()
            .path(endpoint)
            .queryParams(queryParams)
            .build();
        return uriComponents.toUri().toString().replace(":/","://");
    }
}

