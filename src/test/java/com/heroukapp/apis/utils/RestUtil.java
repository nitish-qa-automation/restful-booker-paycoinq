package com.heroukapp.apis.utils;

import static io.restassured.RestAssured.given;

import io.restassured.RestAssured;
import io.restassured.config.EncoderConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.http.Headers;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RestUtil {

    @Autowired
    private HeaderUtil headerUtil;
    private EncoderConfig encoderconfig = new EncoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false);
    private RestAssuredConfig restAssuredConfig = RestAssured.config().encoderConfig(encoderconfig);

    public Response get(Headers headers, String uri) {
        return given()
            .config(restAssuredConfig)
            .headers(headers)
            .log()
            .all()
            .get(uri);
    }

    public Response post(String jsonData, String endPointURL, Headers headers) {
        RestAssured.defaultParser = Parser.JSON;
        return given()
            .config(restAssuredConfig)
            .headers(headers)
            .body(jsonData)
            .log()
            .all()
            .post(endPointURL);
    }

    public Response put(String jsonData, String endPointURL, Headers headers) {
        RestAssured.defaultParser = Parser.JSON;
        return given()
                .config(restAssuredConfig)
                .headers(headers)
                .body(jsonData)
                .log()
                .all()
                .put(endPointURL);
    }

    public Response patch(String jsonData, String endPointURL, Headers headers) {
        RestAssured.defaultParser = Parser.JSON;
        return given()
                .config(restAssuredConfig)
                .headers(headers)
                .body(jsonData)
                .log()
                .all()
                .patch(endPointURL);
    }

    public Response delete(String endPointURL, Headers headers) {
        RestAssured.defaultParser = Parser.JSON;
        return given()
                .config(restAssuredConfig)
                .headers(headers)
                .log()
                .all()
                .delete(endPointURL);
    }

}
