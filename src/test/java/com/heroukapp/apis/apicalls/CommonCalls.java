package com.heroukapp.apis.apicalls;

import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.springframework.stereotype.Component;

@Component
public class CommonCalls extends BaseCall{

    public Response get(Headers headers , String endpoint){ return restUtil.get(headers,endpoint); }

    public Response post(String data, String endpoint, Headers headers){
        return restUtil.post(data,endpoint,headers);
    }

    public Response put(String data, String endpoint, Headers headers){
        return restUtil.put(data,endpoint,headers);
    }

    public Response patch(String data, String endpoint, Headers headers){
        return restUtil.patch(data,endpoint,headers);
    }

    public Response delete( String endpoint, Headers headers){
        return restUtil.delete(endpoint,headers);
    }
}
