package com.heroukapp.apis.utils;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import io.restassured.response.Response;
import org.springframework.stereotype.Component;

@Component
public class Assert {

    public Assert responseStatusIs(Response response, int statusCode) {
        org.junit.Assert.assertEquals("Checking for a service response code.", statusCode, response.statusCode());
        return this;
    }


    public Assert fieldDataIsNotEmpty(String fieldData) {
        org.junit.Assert.assertNotNull("Field Data should not be empty!",fieldData);
        return this;
    }

    public Assert fieldDataIsNotEmpty(int fieldData) {
        org.junit.Assert.assertNotNull("Field Data should not be empty!",fieldData);
        return this;
    }
    public Assert fieldIsEqual(String expectedStr, String actualStr) {
        org.junit.Assert.assertEquals("Checking a String field that equals to expected value.", expectedStr, actualStr);
        return this;
    }
    public Assert fieldIsEqual(Boolean value1, Boolean value2) {
        org.junit.Assert.assertEquals("Checking a String field that equals to expected value.", value1, value2);
        return this;
    }

    public Assert fieldIsEqual(int actualInt, int expectedInt) {
        assertThat("Checking an integer field that equals to expected value.", actualInt, equalTo(expectedInt));
        return this;
    }

}

