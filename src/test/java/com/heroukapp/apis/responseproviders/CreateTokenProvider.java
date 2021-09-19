package com.heroukapp.apis.responseproviders;

import com.heroukapp.apis.dto.createtoken.CreateTokenResponse;
import io.restassured.response.Response;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Getter
@Component
public class CreateTokenProvider {

    private CreateTokenResponse createTokenResponse;

    public void setCreateTokenResponse(Response response){
        createTokenResponse =response.getBody().as(CreateTokenResponse.class);
    }

    public String getToken(){
        return createTokenResponse.getToken();
    }
}
