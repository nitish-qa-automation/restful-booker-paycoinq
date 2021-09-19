
package com.heroukapp.apis.utils;

import java.util.Optional;
import lombok.Getter;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Component
@Getter
public class QueryParamUtil {
    public MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();

    public QueryParamUtil addQueryParam(String key, String value) {
        this.queryParams.add(key, value);
        return this;
    }

    public void clearQueryParams() {
        queryParams.clear();
    }
}
