package com.heroukapp.apis.data;

import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.io.File;

import static com.heroukapp.apis.utils.JsonUtil.readJSON;

@Component
public class CreateTokenPayload {
    private JSONObject jsonObject;
    private static File createTokenFile = new File(System.getProperty("user.dir") +
        "/src/test/resources/payloads/createToken.json");

    public JSONObject getCreateTokenRequestData(LoginDetails loginDetails) {
        jsonObject = readJSON(createTokenFile);
        jsonObject.put("username", loginDetails.getUsername());
        jsonObject.put("password", loginDetails.getPassword());
        return jsonObject;
    }

}
