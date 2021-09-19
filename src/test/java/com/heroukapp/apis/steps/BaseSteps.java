package com.heroukapp.apis.steps;


import com.heroukapp.apis.apicalls.CommonCalls;
import com.heroukapp.apis.commons.Endpoints;
import com.heroukapp.apis.utils.*;
import io.qameta.allure.Allure;
import io.restassured.response.Response;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BaseSteps<T extends BaseSteps<T>> {


    @Autowired
    Assert assertThat;
    @Autowired
    Endpoints endPoints;
    @Autowired
    HeaderUtil headerUtil;
    @Autowired
    QueryParamUtil queryParamUtil;
    @Autowired
    URIBuilder uriBuilder;
    @Autowired
    TestUtil testUtil;
    @Autowired
    CommonCalls commonCalls;


    public void addAllureLogs(String attachmentName , String content){
        Allure.addAttachment(attachmentName , content);
    }
}